package persistence;

import model.MusicPlayer;
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
            MusicPlayer musicPlayer = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMusicPlayer() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMusicPlayer.json");
        try {
            MusicPlayer musicPlayer = reader.read();
            assertEquals("My music player", musicPlayer.getMpName());
            assertEquals(0, musicPlayer.getListOfPlaylists().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMusicPlayer() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMusicPlayer.json");
        try {
            MusicPlayer musicPlayer = reader.read();
            assertEquals("My music player", musicPlayer.getMpName());
            List<Playlist> listOfPlaylists = musicPlayer.getListOfPlaylists();
            assertEquals(2, listOfPlaylists.size());
            checkPlaylist("Playlist 1", listOfPlaylists.get(0).getListOfSongs(), listOfPlaylists.get(0));
            checkPlaylist("Playlist 2", listOfPlaylists.get(1).getListOfSongs(), listOfPlaylists.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}