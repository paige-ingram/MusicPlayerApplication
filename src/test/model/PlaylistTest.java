package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaylistTest {
    private Playlist playlist;
    private Playlist playlistWithSong;
    private Song testSong;


    @BeforeEach
    void setup() {
        playlist = new Playlist("Test playlist");
        testSong = new Song("Test song", "Test artist");
        playlistWithSong = new Playlist("Playlist with Song");
        playlistWithSong.addSong(testSong);
    }

    @Test
    void testGetListOfSongsEmpty() {
        assertEquals(0, playlist.getListOfSongs().size());
    }

    @Test
    void testGetListOfSongs() {
        playlist.addSong(testSong);
        assertTrue(playlist.getListOfSongs().contains(testSong));
        assertEquals(1, playlist.getListOfSongs().size());
    }

    @Test
    void testGetPlaylistName() {
        assertEquals("Test playlist", playlist.getPlaylistName());
    }

    @Test
    void testAddSongNotThere() {
        playlist.addSong(testSong);
        assertTrue(playlist.getListOfSongs().contains(testSong));
        assertEquals(1, playlist.getListOfSongs().size());
    }

    @Test
    void testAddSongThere() {
        playlist.addSong(testSong);
        assertTrue(playlist.getListOfSongs().contains(testSong));
        assertEquals(1, playlist.getListOfSongs().size());
        playlist.addSong(testSong);
        assertTrue(playlist.getListOfSongs().contains(testSong));
        assertEquals(1, playlist.getListOfSongs().size());
    }

    @Test
    void testAddTwoSongsNotThere() {
        playlist.addSong(testSong);
        Song newSong = new Song("New song", "New artist");
        playlist.addSong(newSong);
        assertEquals(2, playlist.getListOfSongs().size());
        assertEquals(newSong, playlist.getListOfSongs().get(1));
    }

    @Test
    void testSongAlreadyThere() {
        playlistWithSong.addSong(testSong);
        assertTrue(playlistWithSong.getListOfSongs().contains(testSong));
        assertEquals(1, playlistWithSong.getListOfSongs().size());
        assertTrue(playlistWithSong.getListOfSongs().contains(testSong));
        assertEquals(1, playlistWithSong.getListOfSongs().size());
    }

    @Test
    void testPlaylistConstructor() {
        assertEquals(0, playlist.getListOfSongs().size());
    }

    @Test
    void testPlaylistRemoveSongNotThere() {
        playlist.removeSong(testSong);
        assertEquals(0, playlist.getListOfSongs().size());
    }

    @Test
    void testPlaylistRemoveSongThere() {
        assertTrue(playlistWithSong.getListOfSongs().contains(testSong));
        playlistWithSong.removeSong(testSong);
        assertEquals(0, playlistWithSong.getListOfSongs().size());
    }

    @Test
    void testPlaylistRemoveTwoSongsThere() {
        Song newSong = new Song("New song", "New artist");
        playlistWithSong.addSong(newSong);
        assertEquals(2, playlistWithSong.getListOfSongs().size());
        playlistWithSong.removeSong(testSong);
        assertTrue(playlistWithSong.getListOfSongs().contains(newSong));
        assertEquals(1, playlistWithSong.getListOfSongs().size());
        playlistWithSong.removeSong(newSong);
        assertEquals(0, playlistWithSong.getListOfSongs().size());
    }

    @Test
    void testPlaylistRemoveSameSongTwice() {
        Song newSong = new Song("New song", "New artist");
        playlistWithSong.addSong(newSong);
        assertEquals(2, playlistWithSong.getListOfSongs().size());
        playlistWithSong.removeSong(testSong);
        assertTrue(playlistWithSong.getListOfSongs().contains(newSong));
        assertEquals(1, playlistWithSong.getListOfSongs().size());
        playlistWithSong.removeSong(testSong);
        assertTrue(playlistWithSong.getListOfSongs().contains(newSong));
        assertEquals(1, playlistWithSong.getListOfSongs().size());
    }

}
