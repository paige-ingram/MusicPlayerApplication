package ui;

import Exceptions.InvalidPositionException;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// UI Functionality and methods are inspired by Teller App and JsonSerializationDemo. Links below:
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class GUI extends JFrame {

// Represents the main window in which the to-do list exists

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private static final String JSON_STORE = "./data/MusicPlayer.json";
    private JList list;
    private DefaultListModel listModel;
    private MusicPlayer mp;
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);


    private JFrame frame;
    private JPanel topPanel;
    private JPanel btmPanel;
    private JScrollPane scrollPane;

    private JButton newPlystBtn;
    private JButton addSongBtn;
    private JButton removeSongBtn;
    private JButton viewPlystBtn;
    private JButton saveMscPlyrBtn;
    private JButton loadMscPlyrBtn;

    // Constructs main window
    // MODIFIES: this
    // EFFECTS: sets up window in which music player will be used
    public GUI() { // does GUI take a table param
        super("Music player");
        this.mp = new MusicPlayer("My Music Player");
        initializeGraphics();

        setPreferredSize(new Dimension(1200, 1000));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(20, 100, 20, 100));
        setLayout(new GridLayout(15, 1, 1, 7));

        initializeScrollPane();
        initializeGraphics();
        initializeButtons();

//        setSize(300, 200);
//        setBackground(Color.blue);

//        frame = new JFrame();
//        topPanel = new JPanel();
//        btmPanel = new JPanel();
//
//        topPanel.setLayout(new BorderLayout());
//        getContentPane().add(topPanel, BorderLayout.CENTER);
//        getContentPane().add(btmPanel, BorderLayout.SOUTH);
//        scrollPane = new JScrollPane();
//        topPanel.add(scrollPane, BorderLayout.CENTER);
//
//        JPanel panel = new JPanel();
//        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
//        panel.setLayout(new GridLayout(1, 1));
//
//        frame.add(topPanel, BorderLayout.CENTER);
//        frame.add(btmPanel, BorderLayout.CENTER);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setTitle("Music Player");
//        frame.pack();
//        frame.setVisible(true);
//
//        initializeButtons();
    }

    // MODIFIES: this
    // EFFECTS: creates scroll pane and inserts list component
    private void initializeScrollPane() {

        listModel = new DefaultListModel();
        list = new JList(listModel);
        JScrollPane listScrollPane = new JScrollPane(list);
        add(listScrollPane);
        setVisible(true);

    }
    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing

    private void initializeGraphics() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);

//        setLayout(new BorderLayout());
//        setMinimumSize(new Dimension(WIDTH, HEIGHT));
//        initializeButtons();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setVisible(true);
    }

    // Code modeled on space invaders application: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git

    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centered on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons
    public void initializeButtons() {
        newPlystBtn = new JButton("New Playlist");
        //      newPlystBtn.setBounds(0, 300, 130, 30);
        newPlystBtn.addActionListener(this::newPlaylist);
        add(newPlystBtn);


        addSongBtn = new JButton("Add Song");
        //  addSongBtn.setBounds(125, 300, 100, 30);
        addSongBtn.addActionListener(this::addSong);
        add(addSongBtn);

        removeSongBtn = new JButton("Remove Song");
        // removeSongBtn.setBounds(225, 300, 130, 30);
        removeSongBtn.addActionListener(this::removeSong);
        add(removeSongBtn);

        viewPlystBtn = new JButton("View Playlist");
//        viewPlystBtn.setBounds(225, 300, 130, 30);
        viewPlystBtn.addActionListener(this::viewPlaylist);
        add(viewPlystBtn);

        saveMscPlyrBtn = new JButton("Save Music Player");
//        saveMscPlyrBtn.setBounds(225, 300, 130, 30);
        saveMscPlyrBtn.addActionListener(this::saveMusicPlayer);
        add(saveMscPlyrBtn);

        loadMscPlyrBtn = new JButton("Load Music Player");
        //      loadMscPlyrBtn.setBounds(225, 300, 130, 30);
        loadMscPlyrBtn.addActionListener(this::loadMusicPlayer);
        add(loadMscPlyrBtn);

        pack();
        setVisible(true);

        setSize(1250, 1000);
        setLayout(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: pops up JOptionPanes for user to input details about a task
    //          adds task to the to-do list
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

    // EFFECTS: displays specified playlist in new JFrame
    public void viewPlaylist(ActionEvent e) {
        JFrame viewPlaylistPosition = new JFrame();
        JFrame showPlaylistPanel = new JFrame();
        JFrame playlistImage = new JFrame();

        String playlistPosAsString = JOptionPane.showInputDialog(viewPlaylistPosition,
                "Which playlist would you like to view? "
                        + "Please enter the playlist's position in the list (0 indexing).");
//        Playlist playlistToView = getPlaylist(playlistPosAsString);
        if (playlistPosAsString != null) {
            try {
                if (getPlaylist(playlistPosAsString).getListOfSongs().size() == 0) {
                    JOptionPane.showMessageDialog(showPlaylistPanel,
                            "This playlist is empty!");
                } else {
                    Playlist playlistToView = getPlaylist(playlistPosAsString);
                    int numSongs = playlistToView.getListOfSongs().size();
                    JOptionPane.showMessageDialog(showPlaylistPanel, "Playlist: "
                            + playlistToView.getPlaylistName() + "\n"
                            + showPlaylist(playlistToView));
                    showImage();
//                    ImageIcon icon = new ImageIcon("/data/MusicPlayerImage.png");
//                    JOptionPane.showMessageDialog(playlistImage, icon);
//                    List<Song> listOfSongs = playlistToView.getListOfSongs();
//                    for (int s = 0; s < numSongs; s++) {
//                        Song song = listOfSongs.get(s);
//                        String songName = song.getName();
//                        String artistName = song.getArtist();
//                        JOptionPane.showMessageDialog(showPlaylistPanel, "'" + songName + "' by " + artistName);
//                    }
                }
            } catch (InvalidPositionException exception) {
                JOptionPane.showMessageDialog(showPlaylistPanel,
                        "That is not a valid position.");
            }
        }
    }

    private String showPlaylist(Playlist playlist) {
        int numSongs = playlist.getListOfSongs().size();
        List<Song> listOfSongs = playlist.getListOfSongs();
        for (int s = 0; s < numSongs; s++) {
            Song song = listOfSongs.get(s);
            String songName = song.getName();
            String artistName = song.getArtist();
            return "'" + songName + "' by " + artistName;
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

    // The following code is modeled on ListDemo.java example from:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing
    // /examples/components/ListDemoProject/src/components/ListDemo.java


//    private void initializeInteraction() {
//        MusicPlayerListener mpl = new MouseMotionListener();
//        addMouseListener(mpl);
//        addMouseMotionListener(mpl);
//    }


    // EFFECTS: saves list to file
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
    // EFFECTS: loads to-do list from file
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

    public void addSong(ActionEvent e) {
        Playlist selectedPlaylist = selectPlaylist();

        //      JFrame viewSelectedPlaylist = new JFrame();
        JFrame addNamePanel = new JFrame();
        JFrame addArtistPanel = new JFrame();
        JFrame successfullyAddedPanel = new JFrame();
        JFrame failureToAddPanel = new JFrame();

        //viewPlaylistToAddSong(selectedPlaylist);

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

    public Playlist selectPlaylist() {
        JFrame selectPlaylistPanel = new JFrame();
        String playlistPosAsString = JOptionPane.showInputDialog(selectPlaylistPanel,
                "Select a playlist to add your song to. "
                        + "Please enter the position of the playlist in the list (0 indexing).");

        int playlistChoice = Integer.parseInt(playlistPosAsString);
        return mp.getListOfPlaylists().get(playlistChoice);
    }

    public void viewPlaylistToAddSong(Playlist playlist) {
        JFrame playlistAddSongPanel = new JFrame();

    }

    public void removeSong(ActionEvent e) {

        JFrame insertPlaylistPosition = new JFrame();
        JFrame showPlaylist = new JFrame();

        JFrame removeSongPanel = new JFrame();
        JFrame successfullyAddedPanel = new JFrame();

        String removeSongIndex;


        String playlistPosAsString = JOptionPane.showInputDialog(removeSongPanel,
                "Which playlist would you like to remove a song from? "
                        + "Please enter the playlist's position in the list (0 indexing).");
//        Playlist playlistToView = getPlaylist(playlistPosAsString);
        if (playlistPosAsString != null) {
            try {
                viewPlaylistToRemoveSong((getPlaylist(playlistPosAsString)));
            } catch (InvalidPositionException exception) {
                JOptionPane.showMessageDialog(insertPlaylistPosition,
                        "That is not a valid position.");

            }
        }
    }

    public void viewPlaylistToRemoveSong(Playlist playlist) {
        JFrame selectSongPanel = new JFrame();

        String songPosAsString = JOptionPane.showInputDialog(selectSongPanel,
                "Which song would you like to remove? "
                        + "Please enter the song's position in the list (0 indexing).");

        if (songPosAsString != null) {
            Integer songPosAsInt = Integer.parseInt(songPosAsString);
        }
        try {
            Integer songPosAsInt = Integer.parseInt(songPosAsString);
            Song songToRemove = getSong(playlist, songPosAsString);
        } catch (InvalidPositionException exception) {
            JOptionPane.showMessageDialog(selectSongPanel,
                    "That is not a valid position.");

        }
    }

    // EFFECTS: returns task of position passed as argument
    //          throws an InvalidPositionException if position doesn't exist
    private Song getSong(Playlist playlist, String songPosAsString) throws InvalidPositionException {
        Integer songPosAsInt = Integer.parseInt(songPosAsString);

        Song songToRemove = playlist.getListOfSongs().get(songPosAsInt);
        return songToRemove;
    }

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
}