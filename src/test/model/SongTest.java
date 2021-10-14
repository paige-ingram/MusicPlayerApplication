package model;

import model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongTest {
    private Song song;

    @BeforeEach
    public void setup() {
        song = new Song("Song name", "Song artist");
    }

    @Test
    void testGetName() {
        assertEquals("Song name", song.getName());
    }

    @Test
    void testGetArtist() {
        assertEquals("Song artist", song.getArtist());
    }
}
