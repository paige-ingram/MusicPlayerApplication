package ui;

import exceptions.InvalidPositionException;
import model.Event;
import model.EventLog;
import model.MusicPlayer;
import model.Playlist;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// UI Functionality and methods are inspired by Teller App and JsonSerializationDemo. Links below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// user interface for the music player application
public class GUI extends JFrame implements LogPrinter {

    private static final String JSON_STORE = "./data/MusicPlayer.json";
    private JList list;
    private DefaultListModel listModel;
    private MusicPlayer mp;
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    private JButton newPlystBtn;
    private JButton addSongBtn;
    private JButton removeSongBtn;
    private JButton viewPlystBtn;
    private JButton saveMscPlyrBtn;
    private JButton loadMscPlyrBtn;

    private JFrame frame;

    // Constructs main window
    // MODIFIES: this
    // EFFECTS: sets up window in which music player will be used
    public GUI() {
        super("Music player");
        this.mp = new MusicPlayer("My Music Player");
        initializeGraphics();

        setPreferredSize(new Dimension(1200, 1000));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(20, 100, 20, 100));
        setLayout(new GridLayout(15, 1, 1, 7));

        initializeScrollPane();
        initializeGraphics();
        initializeButtons();
    }

    // Code modelled on DrawingPlayer application:
    // https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
    // MODIFIES: this
    // EFFECTS: creates scroll pane and inserts list component
    private void initializeScrollPane() {
        listModel = new DefaultListModel();
        list = new JList(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);
        add(listScrollPane);
        setVisible(true);
    }


    // Code modelled on DrawingPlayer application:
    // https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
    // MODIFIES: this
    // EFFECTS:  initializes settings for JFrame
    private void initializeGraphics() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    // Code modeled on SpaceInvaders application: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centered on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // Code modeled on stack exchange:
    // https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
    // MODIFIES: this
    // EFFECTS: initializes buttons
//    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void initializeButtons() {

        newPlystBtn = new JButton("New Playlist");
        newPlystBtn.addActionListener(this::newPlaylist);
        add(newPlystBtn);

        addSongBtn = new JButton("Add Song");
        addSongBtn.addActionListener(this::addSong);
        add(addSongBtn);

        removeSongBtn = new JButton("Remove Song");
        removeSongBtn.addActionListener(this::removeSong);
        add(removeSongBtn);

        viewPlystBtn = new JButton("View Playlist");
        viewPlystBtn.addActionListener(this::viewPlaylist);
        add(viewPlystBtn);

        saveMscPlyrBtn = new JButton("Save Music Player");
        saveMscPlyrBtn.addActionListener(this::saveMusicPlayer);
        add(saveMscPlyrBtn);

        loadMscPlyrBtn = new JButton("Load Music Player");
        loadMscPlyrBtn.addActionListener(this::loadMusicPlayer);
        add(loadMscPlyrBtn);

        initializeOtherGraphics();
    }

    public void initializeOtherGraphics() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(1250, 1000);
        setLayout(null);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    printLog(EventLog.getInstance());
                    System.exit(0);
                } // make sure it doesnt close if no is chosen
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: pops up JOptionPanes for user to input details about a playlist
    //          adds playlist to the music player
    public void newPlaylist(ActionEvent e) {
        JFrame addNamePanel = new JFrame();
        JFrame successfullyAddedPanel = new JFrame();

        String createPlaylistName = JOptionPane.showInputDialog(addNamePanel,
                "Please enter the name of the playlist");

        if (createPlaylistName != null) {
            Playlist playlist = new Playlist(createPlaylistName);
            mp.addPlaylist(playlist);
            listModel.addElement(playlist.getPlaylistName());
            JOptionPane.showMessageDialog(successfullyAddedPanel, "Your playlist, " + createPlaylistName
                    + ", was successfully added to your music player.");
        }
    }

    // EFFECTS: displays specified playlist in JOptionPanes
    public void viewPlaylist(ActionEvent e) {
        JFrame viewPlaylistPosition = new JFrame();
        JFrame showPlaylistPanel = new JFrame();

        String playlistPosAsString = JOptionPane.showInputDialog(viewPlaylistPosition,
                "Which playlist would you like to view? "
                        + "Please enter the playlist's position in the list (0 indexing).");
        if (playlistPosAsString != null) {
            try {
                if (getPlaylist(playlistPosAsString).getListOfSongs().size() == 0) {
                    JOptionPane.showMessageDialog(showPlaylistPanel,
                            "This playlist is empty!");
                } else {
                    Playlist playlistToView = getPlaylist(playlistPosAsString);
                    JOptionPane.showMessageDialog(showPlaylistPanel, "Playlist: "
                            + playlistToView.getPlaylistName());
                    showPlaylist(playlistToView);
                    showImage();
                }
            } catch (InvalidPositionException exception) {
                JOptionPane.showMessageDialog(showPlaylistPanel,
                        "That is not a valid position.");
            }
        }
    }

    // EFFECTS: iterates through list of songs in given playlist and shows each song name and artist
    private String showPlaylist(Playlist playlist) {
        JFrame showSongsPanel = new JFrame();
        int numSongs = playlist.getListOfSongs().size();
        List<Song> listOfSongs = playlist.getListOfSongs();
        for (int s = 0; s < numSongs; s++) {
            Song song = listOfSongs.get(s);
            String songName = song.getName();
            String artistName = song.getArtist();
            JOptionPane.showMessageDialog(showSongsPanel, "Song " + s + ": '" + songName + "' by " + artistName);
            // return "Song" + s + ": '" + songName + "' by " + artistName;
        }
        return null;
    }

    // EFFECTS: returns playlist of position passed as argument
    //          throws an InvalidPositionException if position doesn't exist
    private Playlist getPlaylist(String playlistPosAsString) throws InvalidPositionException {
        JFrame invalidPosPanel = new JFrame();
        Integer playlistPosAsInt = Integer.parseInt(playlistPosAsString);
        Playlist playlistToGet = mp.getListOfPlaylists().get(playlistPosAsInt);
        return playlistToGet;
    }


    // EFFECTS: saves music player to file
    public void saveMusicPlayer(ActionEvent e) {
        JFrame writeToFilePanel = new JFrame();
        try {
            jsonWriter.open();
            jsonWriter.write(mp);
            jsonWriter.close();
            JOptionPane.showMessageDialog(writeToFilePanel, "Saved your playlists to " + JSON_STORE);
        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(writeToFilePanel, "Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads music player from file
    public void loadMusicPlayer(ActionEvent e) {
        JFrame readFromFilePanel = new JFrame();
        try {
            mp = jsonReader.read();
            JOptionPane.showMessageDialog(readFromFilePanel, "Loaded your playlists from " + JSON_STORE);
            listModel.removeAllElements();
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(readFromFilePanel, "Unable to read from file: " + JSON_STORE);
        }
        for (int p = 0; p < mp.getListOfPlaylists().size(); p++) {
            listModel.addElement(mp.getListOfPlaylists().get(p).getPlaylistName());
        }
    }

    // MODIFIES: this
    // EFFECTS: pops up JOptionPanes for user to input details about a song
    // adds song to a playlist in the music player
    public void addSong(ActionEvent e) {
        Playlist selectedPlaylist = selectPlaylist();

        JFrame addNamePanel = new JFrame();
        JFrame addArtistPanel = new JFrame();
        JFrame successfullyAddedPanel = new JFrame();
        JFrame failureToAddPanel = new JFrame();

        String songName = JOptionPane.showInputDialog(addNamePanel,
                "Please enter the name of the song to add to " + selectedPlaylist.getPlaylistName() + ".");
        if (songName != null) {
            String artistName = JOptionPane.showInputDialog(addArtistPanel,
                    "Please enter the artist's name.");
            Song addedSong = new Song(songName, artistName);
            selectedPlaylist.addSong(addedSong);
            JOptionPane.showMessageDialog(successfullyAddedPanel,
                    "A new song, '" + songName + "', was added to your playlist, "
                            + selectedPlaylist.getPlaylistName() + "!");
        } else {
            JOptionPane.showMessageDialog(failureToAddPanel, "Sorry, you did not type a valid "
                    + "song name so we are unable to add it to your playlist.");
        }
    }

    // EFFECTS: pops up JOptionPane for user to input details which playlist they will add their song to
    public Playlist selectPlaylist() {
        JFrame selectPlaylistPanel = new JFrame();
        String playlistPosAsString = JOptionPane.showInputDialog(selectPlaylistPanel,
                "Select a playlist to add your song to. "
                        + "Please enter the position of the playlist in the list (0 indexing).");

        int playlistChoice = Integer.parseInt(playlistPosAsString);
        return mp.getListOfPlaylists().get(playlistChoice);
    }

    // EFFECTS: pops up JOptionPane for user to input details which playlist they would like to remove a song from
    public void removeSong(ActionEvent e) {
        JFrame insertPlaylistPosition = new JFrame();
        JFrame removeSongPanel = new JFrame();
        String playlistPosAsString = JOptionPane.showInputDialog(removeSongPanel,
                "Which playlist would you like to remove a song from? "
                        + "Please enter the playlist's position in the list (0 indexing).");
        if (playlistPosAsString != null) {
            try {
                viewPlaylistToRemoveSong((getPlaylist(playlistPosAsString)));
            } catch (InvalidPositionException exception) {
                JOptionPane.showMessageDialog(insertPlaylistPosition,
                        "That is not a valid position.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: pops up JOptionPane for user to input details which song they would like to remove
    public void viewPlaylistToRemoveSong(Playlist playlist) {
        JFrame selectSongPanel = new JFrame();
        JFrame successfullyRemovedPanel = new JFrame();

        String songPosAsString = JOptionPane.showInputDialog(selectSongPanel,
                "Which song would you like to remove? "
                        + "Please enter the song's position in the list (0 indexing).");
        if (songPosAsString != null) {
            Integer songPosAsInt = Integer.parseInt(songPosAsString);
        }
        try {
            // Integer songPosAsInt = Integer.parseInt(songPosAsString);
            Song songToRemove = getSong(playlist, songPosAsString);
            playlist.removeSong(songToRemove);
            JOptionPane.showMessageDialog(successfullyRemovedPanel, "Your song, '" + songToRemove.getName()
                    + "', was successfully removed from your playlist, " + playlist.getPlaylistName() + ".");
        } catch (InvalidPositionException exception) {
            JOptionPane.showMessageDialog(selectSongPanel,
                    "That is not a valid position.");
        }
    }

    // EFFECTS: returns song of position passed as argument
    //          throws an InvalidPositionException if position doesn't exist
    private Song getSong(Playlist playlist, String songPosAsString) throws InvalidPositionException {
        Integer songPosAsInt = Integer.parseInt(songPosAsString);

        Song song = playlist.getListOfSongs().get(songPosAsInt);
        return song;
    }

    // MODIFIES: this
    // EFFECTS: displays "yay!" image after playlist is viewed
    private void showImage() {
        JDialog yayDialog = new JDialog();
        yayDialog.setUndecorated(true);
        JLabel label = new JLabel(new ImageIcon("data/yay.png"));
        yayDialog.add(label);
        centreOnScreen();
        yayDialog.setVisible(true);
        JDialog playlistDialog = new JDialog();
        playlistDialog.setUndecorated(true);
        JOptionPane.showMessageDialog(yayDialog, label,
                "Yay! What an amazing playlist!",
                JOptionPane.PLAIN_MESSAGE, null);
        playlistDialog.setVisible(false);
    }

//    // code modelled from:
//    // https://examples.javacodegeeks.com/desktop-java/swing/jframe/create-jframe-window-with-window-close-event/
//    public class CustomWindowAdapter extends WindowAdapter {
//        CreateJFrameWindowWithWindowCloseEvent window = null;
//
//        CustomWindowAdapter(CreateJFrameWindowWithWindowCloseEvent window) {
//            this.window = window;
//        }
//
//        // implement windowClosing method
//        public void windowClosing(WindowEvent e) {
//            // exit the application when window's close button is clicked
//            System.exit(0);
//        }
//    }


    @Override
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }
        repaint();
    }
}