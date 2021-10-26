package persistence;

import model.Song;
import model.Playlist;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Citation: JsonSerializationDemo - JsonReaderTest.java
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Playlist playlist = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlaylist.json");
        try {
            Playlist playlist = reader.read();
            assertEquals("Dance Yourself Clean from COVID-19", playlist.getPlaylistName());
            assertEquals(0, playlist.getListOfSongs().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlaylist.json");
        try {
            Playlist playlist = reader.read();
            assertEquals("Dance Yourself Clean from COVID-19", playlist.getPlaylistName());
            List<Song> listOfSongs = playlist.getListOfSongs();
            assertEquals(2, listOfSongs.size());
            checkSong("Like a G6", "Far East Movement", listOfSongs.get(0));
            checkSong("Motley Crew", "Post Malone", listOfSongs.get(1));
            checkSong("Low", "Flo Rida", listOfSongs.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}