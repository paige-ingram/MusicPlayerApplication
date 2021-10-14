package ui;

import model.Playlist;
import model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// a Music Player application with a collection of user-generated playlists
public class MusicPlayer {
    private Scanner input;
    private ArrayList<Playlist> myMusicPlayer;

    // EFFECTS: runs the music player
    public MusicPlayer() {
        myMusicPlayer = new ArrayList<Playlist>();
        runMusicPlayer();
    }

    // MODIFIES: this
    // EFFECTS: initializes playlists
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // MODIFIES: this
    // EFFECTS: allows user to edit and view their playlists, and create new playlists
    private void runMusicPlayer() {
        boolean keepGoing = true;
        String command = null;
        init();
        while (keepGoing) {
            displayStartMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("bye")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks for jamming with us!");
    }


    // EFFECTS: displays menu of options to user
    private void displayStartMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tnew -> New Playlist");
        System.out.println("\tadd -> Add Song");
        System.out.println("\tremove -> Remove Song");
        System.out.println("\tview -> View Playlist");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("new")) {
            userNewPlaylist();
        } else if (command.equals("add")) {
            System.out.println("Select a playlist to add your song to!");
            Playlist selectedPlaylist = userSelectPlaylist();
            userAddSong(selectedPlaylist);
        } else if (command.equals("remove")) {
            System.out.println("What playlist would you like to remove a song from?");
            Playlist selectedPlaylist = userSelectPlaylist();
            userRemoveSong(selectedPlaylist);
        } else if (command.equals("view")) {
            System.out.println("What playlist would you like to view?");
            Playlist selectedPlaylist = userSelectPlaylist();
            userViewPlaylist(selectedPlaylist);
        } else {
            System.out.println("Sorry! We can't understand what you are trying to do!");
        }
    }

    // EFFECTS: displays menu of user-created playlists to user and returns user selected playlist
    private Playlist userSelectPlaylist() {
        String selection = "";  // force entry into loop
        for (int i = 0; i < myMusicPlayer.size(); i++) {
            String pname = myMusicPlayer.get(i).getPlaylistName();
            System.out.println(i + " " + pname);
        }
        int playlistChoice = Integer.parseInt(input.nextLine());
        return myMusicPlayer.get(playlistChoice);
    }


    // REQUIRES: no playlist already exists with given name, playlist name is not null
    // MODIFIES: this
    // EFFECTS: creates a new playlist with given name
    public void userNewPlaylist() {
        System.out.print("Enter playlist name: ");
        String createPlaylistName = input.nextLine();
        Playlist playlist = new Playlist(createPlaylistName);
        myMusicPlayer.add(playlist); // change this to .addPlaylist
//        myMusicPlayer.add(playlist);
        System.out.println("Your playlist, " + createPlaylistName + ", was added to your Music Player!");
    }

    // REQUIRES: song name and artist name are not null
    // MODIFIES: this
    // EFFECTS: adds new song with song name and artist to end of given playlist
    public void userAddSong(Playlist selectedPlaylist) {
        System.out.print("Enter song name: \n");
        String addSongName = input.nextLine();
        System.out.println("Enter artist name: ");
        String addSongArtist = input.nextLine();
        Song addedSong = new Song(addSongName, addSongArtist);
        selectedPlaylist.addSong(addedSong);
        System.out.println("A new song, " + getName(addedSong) + ", was added to your playlist "
                + selectedPlaylist.getPlaylistName() + "!");
    }

    // REQUIRES: song is currently in playlist
    // MODIFIES: this
    // EFFECTS: removes identified song from current playlist
    public void userRemoveSong(Playlist playlist) {
        System.out.println("Which song would you like to remove? \n");
        viewRemoveSongFromPlaylist(playlist);
        int songRemoveChoice = Integer.parseInt(input.nextLine());
        List<Song> listofsongs = playlist.getListOfSongs();
        listofsongs.remove(songRemoveChoice); // change this to .removeSong
        System.out.println("Your song has been removed from your playlist " + playlist.getPlaylistName() + "!");
    }

    // EFFECTS: displays indexed list of songs
    private void viewRemoveSongFromPlaylist(Playlist p) {
        String selection = "";  // force entry into loop
        int numsongs = p.getListOfSongs().size();
        System.out.println("Playlist: " + p.getPlaylistName());
        List<Song> listOfSongs = p.getListOfSongs();
        for (int s = 0; s < numsongs; s++) {
            Song song = listOfSongs.get(s);
            String sname = song.getName();
            String aname = song.getArtist();
            System.out.println(s + "'" + sname + "' by " + aname);
        }
    }

    // EFFECTS: warns if playlist is empty, otherwise display playlist
    public void userViewPlaylist(Playlist playlist) {
        if (playlist.getListOfSongs().size() == 0) {
            System.out.println("This playlist is empty!");
        } else {
            viewPlaylist(playlist);
        }
    }

    // EFFECTS: displays playlist to user including playlist name and each song currently in the
    // playlist and its artist, in order which songs were added
    private void viewPlaylist(Playlist p) {
        String selection = "";  // force entry into loop
        int numsongs = p.getListOfSongs().size();
        System.out.println("Playlist: " + p.getPlaylistName());
        List<Song> listOfSongs = p.getListOfSongs();
        for (int s = 0; s < numsongs; s++) {
            Song song = listOfSongs.get(s);
            String sname = song.getName();
            String aname = song.getArtist();
            System.out.println("'" + sname + "' by " + aname);
        }
    }

    // EFFECTS: returns name of given song
    public String getName(Song s) {
        return s.getName();
    }

}



