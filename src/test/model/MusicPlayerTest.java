package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicPlayerTest {
    private MusicPlayer musicPlayer;
    private MusicPlayer musicPlayerWithPlaylist;
    private Playlist testPlaylist;


    @BeforeEach
    void setup() {
        musicPlayer = new MusicPlayer("Test music player");
        testPlaylist = new Playlist("Test Playlist");
        musicPlayerWithPlaylist = new MusicPlayer("Music player with playlist");
        musicPlayerWithPlaylist.addPlaylist(testPlaylist);
    }

    @Test
    void testGetListOfPlaylistsEmpty() {
        assertEquals(0, musicPlayer.getListOfPlaylists().size());
    }

    @Test
    void testGetListOfPlaylists() {
        musicPlayer.addPlaylist(testPlaylist);
        assertTrue(musicPlayer.getListOfPlaylists().contains(testPlaylist));
        assertEquals(1, musicPlayer.getListOfPlaylists().size());
    }

    @Test
    void testGetMusicPlayerName() {
        assertEquals("Test music player", musicPlayer.getMpName());
    }

    @Test
    void testAddPlaylistNotThere() {
        musicPlayer.addPlaylist(testPlaylist);
        assertTrue(musicPlayer.getListOfPlaylists().contains(testPlaylist));
        assertEquals(1, musicPlayer.getListOfPlaylists().size());
    }

    @Test
    void testAddPlaylistThere() {
        musicPlayer.addPlaylist(testPlaylist);
        assertTrue(musicPlayer.getListOfPlaylists().contains(testPlaylist));
        assertEquals(1, musicPlayer.getListOfPlaylists().size());
        musicPlayer.addPlaylist(testPlaylist);
        assertTrue(musicPlayer.getListOfPlaylists().contains(testPlaylist));
        assertEquals(1, musicPlayer.getListOfPlaylists().size());
    }

    @Test
    void testAddTwoPlaylistsNotThere() {
        musicPlayer.addPlaylist(testPlaylist);
        Playlist newPlaylist = new Playlist("New playlist");
        musicPlayer.addPlaylist(newPlaylist);
        assertEquals(2, musicPlayer.getListOfPlaylists().size());
        assertEquals(newPlaylist, musicPlayer.getListOfPlaylists().get(1));
    }

    @Test
    void testPlaylistAlreadyThere() {
        musicPlayerWithPlaylist.addPlaylist(testPlaylist);
        assertTrue(musicPlayerWithPlaylist.getListOfPlaylists().contains(testPlaylist));
        assertEquals(1, musicPlayerWithPlaylist.getListOfPlaylists().size());
        assertTrue(musicPlayerWithPlaylist.getListOfPlaylists().contains(testPlaylist));
        assertEquals(1, musicPlayerWithPlaylist.getListOfPlaylists().size());
    }

    @Test
    void testMusicPlayerConstructor() {
        assertEquals(0, musicPlayer.getListOfPlaylists().size());
    }
}
