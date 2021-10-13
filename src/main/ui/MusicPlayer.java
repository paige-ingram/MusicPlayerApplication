package ui;

import model.Playlist;
import model.Song;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

// a Music Player application with a collection of user-generated playlists
public class MusicPlayer {
    private Scanner input;
    private ArrayList<Song> myPlaylist;
    private ArrayList<Playlist> myMusicPlayer;
    private ArrayList<Song> songLibrary; // a collection of all songs in the music player

    // EFFECTS: runs the music player
    public MusicPlayer() {
        myPlaylist = new ArrayList<Song>();
        myMusicPlayer = new ArrayList<Playlist>();
        runMusicPlayer();
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit and view their playlists
    private void runMusicPlayer() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks for jamming with us!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("new")) {
            userNewPlaylist();
        } else if (command.equals("add")) {
            userAddSong();
        } else if (command.equals("remove")) {
            userRemoveSong();
        } else if (command.equals("view")) {
            userViewPlaylist();
        } else {
            System.out.println("Sorry! We can't understand what you are trying to do!");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes playlists
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tnew -> New Playlist");
        System.out.println("\tadd -> Add Song");
        System.out.println("\tremove -> Remove Song");
        System.out.println("\tview -> View Playlist");
    }

    // REQUIRES: no playlist already exists with given name
    // MODIFIES: this
    // EFFECTS: creates a new playlist with given name
    private void userNewPlaylist() {
        System.out.print("Enter playlist name: ");
        String createPlaylistName = input.nextLine();
        Playlist playlist = new Playlist(createPlaylistName);
        myMusicPlayer.add(playlist);
    }

    private void userAddSong() {
        System.out.print("Enter song name: ");
        String addSongName = input.nextLine();
        System.out.println("Enter artist name: ");
        String addSongArtist = input.nextLine();
        Song addSong = new Song(addSongName, addSongArtist);
        selectPlaylist().addSong(addSong);
    }

    // REQUIRES: song is currently in playlist
    private void userRemoveSong() {
        System.out.print("Enter song name: ");
        String removeSongName = input.nextLine();
        Song song = getSong(removeSongName);
        selectPlaylist().removeSong(song);
    }

    private void userViewPlaylist() {
        System.out.print("What playlist would you like to view?\n");
        String viewPlaylistName = input.nextLine();
        selectPlaylist().viewPlaylist();
    }
    // viewPlaylist(viewPlaylistName);

    // REQUIRES: user inputs an integer < # items in myMusicPlayer
    // EFFECTS: prompts user to select a playlist and returns it
    private Playlist selectPlaylist() {
        String selection = "";  // force entry into loop

        for (int i = 0; i < myMusicPlayer.size(); i++) {
            System.out.println(i + " " + myMusicPlayer.get(i));
        }

        int playlistChoice = Integer.parseInt(input.nextLine());

        return myMusicPlayer.get(playlistChoice);

    }

    // EFFECTS: returns playlist with given name
//    public Playlist getPlaylist(String playlistName) {
//        for (Playlist p : MusicPlayer) {
//            if (p.pname = playlistName) {
    //               return p;
//            }
//        }
//    }

    // EFFECTS: returns song with given name
    public Song getSong(String songName) {
        for (Song s : myPlaylist) {
            if (s.getName().equals(songName)) {
                return s;
            }
        }
        return null;
    }
}



