package model;

import model.Playlist;
import model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistTest {
    private Playlist playlist;
    private Song testsong;

    @BeforeEach
    void setup() {
        playlist = new Playlist("Test playlist");
        testsong = new Song("Test song", "Test artist");
    }

    @Test
    void testGetListOfSongsEmpty() {
        assertEquals(null, playlist.getListOfSongs());
    }

    @Test
    void testGetListOfSongs() {
        playlist.addSong(testsong);
        assertEquals(testsong, playlist.getListOfSongs());
    }

    @Test
    void testGetPlaylistName() {
        assertEquals("Test playlist", playlist.getPlaylistName());
    }

    @Test
    void testAddSongNotThere() {
        playlist.addSong(testsong);
        assertEquals(testsong, playlist.getListOfSongs());
        assertEquals(1, playlist.getListOfSongs().size());
    }

    @Test
    void testAddSongThere() {
        playlist.addSong(testsong);
        assertEquals(testsong, playlist.getListOfSongs());
        playlist.addSong(testsong);
        assertEquals(testsong, playlist.getListOfSongs());
        assertEquals(1, playlist.getListOfSongs().size());
    }
    @Test
    void testAddTwoSongsNotThere() {
        playlist.addSong(testsong);
        Song newsong = new Song("New song")
        playlist.addSong(new Song(newsong);
        assertEquals(2, playlist.getListOfSongs().size());
        assertEquals(newsong, playlist.getListOfSongs().get(1));
    }
}
