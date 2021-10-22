package model;

import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(List.of(testSong), playlist.getListOfSongs());
    }

    @Test
    void testGetPlaylistName() {
        assertEquals("Test playlist", playlist.getPlaylistName());
    }

    @Test
    void testRemoveSongNotThere() {
        playlist.removeSong(testSong);
        assertEquals(0, playlist.getListOfSongs().size());
    }

    @Test
    void testRemoveSongThere() {
        playlist.addSong(testSong);
        assertEquals(testSong, playlist.getListOfSongs().get(0));
        playlist.removeSong(testSong);
        assertEquals(0, playlist.getListOfSongs().size());
    }
    @Test
    void testAddAndRemoveTwoSongs() {
        playlist.addSong(testSong);
        Song newSong = new Song("New song", "New artist");
        playlist.addSong(newSong);
        assertEquals(2, playlist.getListOfSongs().size());
        assertEquals(newSong, playlist.getListOfSongs().get(1));
        playlist.removeSong(testSong);
        assertEquals(List.of(newSong), playlist.getListOfSongs());
        assertEquals(1, playlist.getListOfSongs().size());
        playlist.removeSong(newSong);
        assertEquals(0, playlist.getListOfSongs().size());
    }

    @Test
    void testAddSongNotThere() {
        playlist.addSong(testSong);
        assertEquals(List.of(testSong), playlist.getListOfSongs());
        assertEquals(1, playlist.getListOfSongs().size());
    }

    @Test
    void testAddSongThere() {
        playlist.addSong(testSong);
        assertEquals(List.of(testSong), playlist.getListOfSongs());
        playlist.addSong(testSong);
        assertEquals(List.of(testSong), playlist.getListOfSongs());
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
        assertEquals(1, playlistWithSong.getListOfSongs().size());
        assertEquals(List.of(testSong), playlistWithSong.getListOfSongs());
        assertEquals(testSong, playlistWithSong.getListOfSongs().get(0));
    }

    @Test
    void testPlaylistConstructor() {
        assertEquals(0, playlist.getListOfSongs().size());
    }
}
