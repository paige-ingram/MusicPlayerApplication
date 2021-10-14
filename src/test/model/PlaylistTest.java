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
    void testAddSong() {
        playlist.addSong(testsong);
        assertEquals(testsong, playlist.getListOfSongs());
    }
}
