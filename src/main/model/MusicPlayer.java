package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a music player with a collection of playlists and a name
public class MusicPlayer implements Writable {

    private String mpName;
    private ArrayList<Playlist> listOfPlaylists;

    // MODIFIES: this
    // EFFECTS: constructs an empty music player with name mpName
    public MusicPlayer(String name) {
        this.mpName = name;
        listOfPlaylists = new ArrayList<Playlist>();
    }

    // EFFECTS: returns the list of playlists in music player
    public ArrayList<Playlist> getListOfPlaylists() {
        return listOfPlaylists;
    }

    // EFFECTS: returns name of music player
    public String getMpName() {
        return mpName;
    }

    // MODIFIES: this
    // EFFECTS: adds given playlist to end of list of playlists in music player, if playlist not already there
    public ArrayList<Playlist> addPlaylist(Playlist myPlaylist) {
        if (!listOfPlaylists.contains(myPlaylist)) {
            listOfPlaylists.add(myPlaylist);
            EventLog.getInstance().logEvent(new Event("New playlist, " + myPlaylist.getPlaylistName()
                    + ", added to music player."));
        }
        return listOfPlaylists;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("music player name", mpName);
        json.put("playlists", playlistsToJson());
        return json;
    }

    // EFFECTS: returns playlists in this music player as a JSON array
    private JSONArray playlistsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Playlist p : listOfPlaylists) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
