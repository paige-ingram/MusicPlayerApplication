package persistence;

import model.MusicPlayer;
import model.Song;
import model.Playlist;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Citation: JsonSerializationDemo - JsonWriterTest.java
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            MusicPlayer musicPlayer = new MusicPlayer("My music player");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMusicPlayer() {
        try {
            MusicPlayer musicPlayer = new MusicPlayer("My music player");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMusicPlayer.json");
            writer.open();
            writer.write(musicPlayer);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMusicPlayer.json");
            musicPlayer = reader.read();
            assertEquals("My music player", musicPlayer.getMpName());
            assertEquals(0, musicPlayer.getListOfPlaylists().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterRegularMusicPlayer() {
        try {
            MusicPlayer musicPlayer = new MusicPlayer("My music player");
            Playlist playlist1 = new Playlist("Playlist 1");
            Song song1 = new Song("Song 1", "Artist 1");
            playlist1.addSong(song1);
            musicPlayer.addPlaylist(playlist1);
            Playlist playlist2 = new Playlist("Playlist 2");
            musicPlayer.addPlaylist(playlist2);

            JsonWriter writer = new JsonWriter("./data/testWriterRegularMusicPlayer.json");
            writer.open();
            writer.write(musicPlayer);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterRegularMusicPlayer.json");
            musicPlayer = reader.read();
            assertEquals("My music player", musicPlayer.getMpName());
            List<Playlist> listOfPlaylists = musicPlayer.getListOfPlaylists();
            assertEquals(2, listOfPlaylists.size());
            checkPlaylist("Playlist 1", playlist1.getListOfSongs(), playlist1);
            checkPlaylist("Playlist 2", playlist2.getListOfSongs(), playlist2);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
