package model;

// Represents a Song with a song name, artist name, and song length (in seconds)
public class Song {
    private String name; //Song name
    private String artist; //Artist name
    private int length; //Length of song in seconds

    // REQUIRES: song name and artist both have non-zero length
    // MODIFIES: this
    // EFFECTS: constructs a song with given name, artist, and song length in seconds
    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getArtist() { return artist; }

    public String toString() {
        return name;
    }


}
