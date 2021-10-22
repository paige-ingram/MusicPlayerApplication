package model;

import model.Playlist;
import model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistTest {
    private Playlist playlist;
    private Playlist playlistwithsong;
    private Song testsong;


    @BeforeEach
    void setup() {
        playlist = new Playlist("Test playlist");
        testsong = new Song("Test song", "Test artist");
        playlistwithsong = new Playlist("Playlist with Song");
        playlistwithsong.addSong(testsong);
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
        Song newSong = new Song("New song", "New artist");
        playlist.addSong(newSong);
        assertEquals(2, playlist.getListOfSongs().size());
        assertEquals(newSong, playlist.getListOfSongs().get(1));
    }

    @Test
    void testSongAlreadyThere() {
        playlistwithsong.addSong(testsong);
        assertEquals(1, playlistwithsong.getListOfSongs().size());
        assertEquals(testsong, playlistwithsong.getListOfSongs());
        assertEquals(testsong, playlistwithsong.getListOfSongs().get(0));
    }
}
