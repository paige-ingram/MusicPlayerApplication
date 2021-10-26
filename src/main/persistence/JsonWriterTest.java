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
            Playlist playlist = new Playlist("Dance Yourself Clean from COVID-19");
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
            MusicPlayer musicPlayer = new MusicPlayer();
//            Playlist playlist = new Playlist("Dance Yourself Clean from COVID-19");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlaylist.json");
            writer.open();
            writer.write(musicPlayer); // should this write a music player?
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlaylist.json");
            musicPlayer = reader.read();
            assertEquals("Dance Yourself Clean from COVID-19", playlist.getPlaylistName());
            assertEquals(0, musicPlayer.getListOfSongs().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterRegularPlaylist() {
        try {
            MusicPlayer musicPlayer = new MusicPlayer();
            Playlist playlist = new Playlist("Dance Yourself Clean from COVID-19");
            playlist.addSong(new Song("Like a G6", "Far East Movement"));
            playlist.addSong(new Song("Motley Crew", "Post Malone"));
            JsonWriter writer = new JsonWriter("./data/testWriterRegularPlaylist.json");
            writer.open();
            writer.write(musicPlayer);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterRegularPlaylist.json");
            playlist = reader.read();
            assertEquals("Dance Yourself Clean from COVID-19", playlist.getPlaylistName());
            List<Song> listOfSongs = playlist.getListOfSongs();
            assertEquals(2, listOfSongs.size());
            checkSong("Like a G6", "Far East Movement", listOfSongs.get(0));
            checkSong("Motley Crew", "Post Malone", listOfSongs.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
