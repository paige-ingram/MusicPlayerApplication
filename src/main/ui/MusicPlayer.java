package ui;

import model.Playlist;
import model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Character.getName;

// a Music Player application with a collection of user-generated playlists
public class MusicPlayer {
    private Scanner input;
    private ArrayList<Song> myPlaylist;
    private ArrayList<Playlist> myMusicPlayer;

    // EFFECTS: runs the music player
    public MusicPlayer() {
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



    // MODIFIES: this
    // EFFECTS: initializes playlists
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
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
            userRemoveSong();
        } else if (command.equals("view")) {
            userViewPlaylist();
        } else {
            System.out.println("Sorry! We can't understand what you are trying to do!");
        }
    }

    // EFFECTS: displays menu of user-created playlists to user
    private Playlist userSelectPlaylist() {
        String selection = "";  // force entry into loop
        for (int i = 0; i < myMusicPlayer.size(); i++) {
            String pname = myMusicPlayer.get(i).getPlaylistName();
            System.out.println(i + " " + pname);
        }
        int playlistChoice = Integer.parseInt(input.nextLine());
        return myMusicPlayer.get(playlistChoice);
    }


    // REQUIRES: no playlist already exists with given name
    // MODIFIES: this
    // EFFECTS: creates a new playlist with given name
    public void userNewPlaylist() {
        System.out.print("Enter playlist name: ");
        String createPlaylistName = input.nextLine();
        Playlist playlist = new Playlist(createPlaylistName);
        myMusicPlayer.add(playlist);
        System.out.println("Your playlist, " + createPlaylistName + ", was added to your Music Player!");
    }

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
    public Playlist userRemoveSong() {
        System.out.print("Enter song name: ");
        String removeSongName = input.nextLine();
        String pname = input.nextLine();
        Playlist userPlaylist = new Playlist(pname);
        Song song = userPlaylist.getSong(removeSongName);
        userPlaylist.removeSong(song);
        return userPlaylist;
    }

    public void userViewPlaylist() {
        System.out.print("What playlist would you like to view?\n");
        Playlist selectedPlaylist = userSelectPlaylist();
        String viewPlaylistName = input.nextLine();
        selectedPlaylist = getPlaylist(viewPlaylistName);
        if (selectedPlaylist.getListOfSongs().size() == 0) {
            System.out.println("This playlist is empty!");
        } else {
            viewPlaylist(selectedPlaylist);
        }
    }

    private void viewPlaylist(Playlist p) {
        System.out.println(p.getListOfSongs());
        System.out.println(p.getListOfSongs().size());
        int numsongs = p.getListOfSongs().size();
        String selection = "";  // force entry into loop
        System.out.println(p.getPlaylistName());
        for (int s = 0; s < numsongs; s++) {
            Song song = myPlaylist.get(s);
            String sname = song.getName();
            System.out.println(s + " " + sname);
        }
    }


//    // REQUIRES: user inputs an integer < # items in myMusicPlayer
//    // EFFECTS: prompts user to select a playlist and returns it
//    private Playlist selectPlaylist() {
//        String selection = "";  // force entry into loop
//
//        for (int i = 0; i < myMusicPlayer.size(); i++) {
//            System.out.println(i + " " + myMusicPlayer.get(i));
//        }
//
//        int playlistChoice = Integer.parseInt(input.nextLine());
//
//        return myMusicPlayer.get(playlistChoice);
//
//    }

    // EFFECTS: returns playlist with given name
    public Playlist getPlaylist(String playlistName) {
        for (Playlist p : myMusicPlayer) {
            if (p.getPlaylistName().equals(playlistName)) {
                return p;
            } else {
                return new Playlist("Empty Playlist");
            }

        }
        return null;
    }

    // REQUIRES: Song must be in playlist
    // EFFECTS: returns song with given name
    public Song getSong(String songName) {
        for (Song s : myPlaylist) {
            if (s.getName().equals(songName)) {
                return s;
            }
        }
        return null;
    }

    public String getName(Song s) {
        return s.getName();
    }
}



