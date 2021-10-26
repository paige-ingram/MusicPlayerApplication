package persistence;

import model.Song;
import model.Playlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Citation: JsonSerializationDemo - JsonTest.java
public class JsonTest {
    protected void checkSong(String songName, String artistName, Song song) {
        assertEquals(songName, song.getName());
        assertEquals(artistName, song.getArtist());
    }
}
