package model;

import ui.MusicPlayer;

import java.util.ArrayList;

// Represents a collection of songs in order which they are added
public class Playlist {

    private String pname;
    private ArrayList<Song> myPlaylist;

    // REQUIRES: pname has non-zero length
    // MODIFIES: this
    // EFFECTS: constructs an empty playlist with name pName
    public Playlist(String pname) {
        this.pname = pname;
        myPlaylist = new ArrayList<Song>();
    }

    public String getPname() {
        return pname;
    }

    // MODIFIES: this
    // EFFECTS: adds given song to end of playlist if song not already in that playlist
    public void addSong(Song mySong) {
        if (!myPlaylist.contains(mySong)) {
            myPlaylist.add(mySong);
        }
    }

    // MODIFIES: this
    // EFFECTS: if song in playlist then removes given song from playlist, else does nothing
    public void removeSong(Song mySong) {
        if (myPlaylist.contains(mySong)) {
            myPlaylist.remove(mySong);
        }
    }

    // EFFECTS: prints the names of all songs currently in the playlist in order they were added
    public void viewPlaylist() {
        for (Song s : myPlaylist) {
            System.out.println(s.getName() + " by " + s.getArtist());
        }
    }

    // EFFECTS: returns song with given name
    public Song getSong(String songName) {
        for (Song s : myPlaylist) {
            if (s.getName().equals(songName)) {
                return s;
            }
            return null;
        }
        return null;
    }

    public String toString() {
        return pname;
    }






}
