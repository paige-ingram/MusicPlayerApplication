package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a playlist with pname and a list of songs in order which they are added to playlist
public class Playlist implements Writable {

    private String playlistName;
    private ArrayList<Song> listOfSongs;

    // MODIFIES: this
    // EFFECTS: constructs an empty playlist with name pname
    public Playlist(String pname) {
        this.playlistName = pname;
        listOfSongs = new ArrayList<Song>();
        EventLog.getInstance().logEvent(new Event("New playlist, " + pname + ", created."));
    }

    // EFFECTS: returns list of songs in playlist
    public ArrayList<Song> getListOfSongs() {
        return listOfSongs;
    }

    // EFFECTS: returns name of playlist
    public String getPlaylistName() {
        return playlistName;
    }

    // MODIFIES: this
    // EFFECTS: adds given song to end of list of songs in playlist, if song not already in that playlist
    public List<Song> addSong(Song mySong) {
        if (listOfSongs.contains(mySong)) {
            return listOfSongs;
        } else {
            listOfSongs.add(mySong);
            EventLog.getInstance().logEvent(new Event("New song '" + mySong.getName()
                    + "' added to a playlist."));
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
            EventLog.getInstance().logEvent(new Event("Removed song '" + mySong.getName()
                    + "' from a playlist."));
            return listOfSongs;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("playlist name", playlistName);
        json.put("playlist songs", songsToJson());
        return json;
    }

    // EFFECTS: returns songs in this playlist as a JSON array
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : listOfSongs) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}
