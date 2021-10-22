package model;

import java.util.ArrayList;
import java.util.List;

// Represents a playlist with pname and a list of songs in order which they are added to playlist
public class Playlist {

    private String pname;
    private ArrayList<Song> listOfSongs;

    // REQUIRES: pname has non-zero length
    // MODIFIES: this
    // EFFECTS: constructs an empty playlist with name pname
    public Playlist(String pname) {
        this.pname = pname;
        listOfSongs = new ArrayList<Song>();
    }

    // EFFECTS: returns list of songs in playlist
    public ArrayList<Song> getListOfSongs() {
        return listOfSongs;
    }

    // EFFECTS: returns name of playlist
    public String getPlaylistName() {
        return pname;
    }

    // MODIFIES: this
    // EFFECTS: adds given song to end of list of songs in playlist, if song not already in that playlist
    public List<Song> addSong(Song mySong) {
        if (listOfSongs.contains(mySong)) {
            return listOfSongs;
        } else {
            listOfSongs.add(mySong);
            return listOfSongs;
        }
    }

    // MODIFIES: this
    // EFFECTS: if song is present, removes given song from list of songs in playlist, else do nothing
    public List<Song> removeSong(Song mySong) {
        if (!listOfSongs.contains(mySong)) {
            return listOfSongs;
        } else {
            listOfSongs.remove(mySong);
            return listOfSongs;
        }
    }
}


//    // EFFECTS: returns song with given name
//    public Song getSong(String songName) {
//        for (Song s : listOfSongs) {
//            if (s.getName().equals(songName)) {
//                return s;
//            }
//            return null;
//        }
//        return null;
//    }


//    // MODIFIES: this
//    // EFFECTS: if song in playlist then removes given song from playlist, else does nothing
//    public void removeSong(Song mySong) {
//        if (listOfSongs.contains(mySong)) {
//            listOfSongs.remove(mySong);
//        }
//    }

