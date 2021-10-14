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

    public String getPname() {
        return pname;
    }

    public ArrayList<Song> getListOfSongs() {
        return listOfSongs;
    }

//    // EFFECTS: prints the names of all songs currently in the playlist in order they were added
//    protected void viewPlaylist(Playlist p) {
//        System.out.println(pname);
//        for (Song s : p) {
//            System.out.println(s.getName() + " by " + s.getArtist());
//        }
//    }

    // EFFECTS: returns song with given name
    public Song getSong(String songName) {
        for (Song s : listOfSongs) {
            if (s.getName().equals(songName)) {
                return s;
            }
            return null;
        }
        return null;
    }

    public String getPlaylistName() {
        return pname;
    }

    // MODIFIES: this
    // EFFECTS: adds given song to end of playlist if song not already in that playlist
    public List<Song> addSong(Song mySong) {
        if (listOfSongs.contains(mySong)) {
            return listOfSongs;
        } else {
            listOfSongs.add(mySong);
            return listOfSongs;
        }
    }

    // MODIFIES: this
    // EFFECTS: if song in playlist then removes given song from playlist, else does nothing
    public void removeSong(Song mySong) {
        if (listOfSongs.contains(mySong)) {
            listOfSongs.remove(mySong);
        }
    }





}
