package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Song with a song name, artist name, and song length (in seconds)
public class Song implements Writable {
    private String name; //Song name
    private String artist; //Artist name
//    private int length; //Song length

    // REQUIRES: song name and artist both have non-zero lengths
    // MODIFIES: this
    // EFFECTS: constructs a song with given name and artist
    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("song name", name); // changed this to integer.parseint
        json.put("artist", artist);
        return json;
    }

}
