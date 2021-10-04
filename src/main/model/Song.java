package model;

public class Song {
    private String name; //Song name
    private String artist; //Artist name
    private int length; //Length of song in seconds

    public Song(String name, String artist, Integer length) {
        this.name = name;
        this.artist = artist;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public Integer getLength() {
        return length;
    }
}
