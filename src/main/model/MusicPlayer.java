package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a collection of playlists with a name
public class MusicPlayer implements Writable {

    private String mpName;
    private ArrayList<Playlist> listOfPlaylists;

    // MODIFIES: this
    // EFFECTS: constructs an empty music player with name mpName
    public MusicPlayer(String name) {
        this.mpName = name;
        listOfPlaylists = new ArrayList<Playlist>();
    }

    // EFFECTS: returns list of songs in playlist
    public ArrayList<Playlist> getListOfPlaylists() {
        return listOfPlaylists;
    }

    // EFFECTS: returns name of playlist
    public String getMpName() {
        return mpName;
    }

//    // MODIFIES: this
//    // EFFECTS: adds given song to end of list of songs in playlist, if song not already in that playlist
//    public ArrayList<Playlist> addPlaylist(Playlist myPlaylist) {
////        if (listOfSongs.contains(mySong)) {
////            return listOfSongs;
////        } else {
////            listOfSongs.add(mySong);
//            return listOfPlaylists.add(myPlaylist);
//        }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("music player name", mpName);
        json.put("playlists", playlistsToJson());
        return json;
    }

    // EFFECTS: returns songs in this playlist as a JSON array
    private JSONArray playlistsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Playlist p : listOfPlaylists) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
