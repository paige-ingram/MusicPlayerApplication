package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//public class PlaylistTest {
//    private Playlist playlist;
//    private Playlist playlistWithSong;
//    private Song testSong;
//
//
//    @BeforeEach
//    void setup() {
//        playlist = new Playlist("Test playlist");
//        testSong = new Song("Test song", "Test artist");
//        playlistWithSong = new Playlist("Playlist with Song");
//        playlistWithSong.addSong(testSong);
//    }
//
////    @Test
////    void testGetListOfSongsEmpty() {
////        assertEquals(null, playlist.getListOfSongs());
////    }
////
////    @Test
////    void testGetListOfSongs() {
////        playlist.addSong(testSong);
////        assertEquals(testSong, playlist.getListOfSongs());
////    }
//
//    @Test
//    void testGetPlaylistName() {
//        assertEquals("Test playlist", playlist.getPlaylistName());
//    }
//
////    @Test
////    void testAddSongNotThere() {
////        playlist.addSong(testSong);
////        assertEquals(testSong, playlist.getListOfSongs());
////        assertEquals(1, playlist.getListOfSongs().size());
////    }
//
////    @Test
////    void testAddSongThere() {
////        playlist.addSong(testSong);
////        assertEquals(testSong, playlist.getListOfSongs());
////        playlist.addSong(testSong);
////        assertEquals(testSong, playlist.getListOfSongs());
////        assertEquals(1, playlist.getListOfSongs().size());
////    }
//    @Test
//    void testAddTwoSongsNotThere() {
//        playlist.addSong(testSong);
//        Song newSong = new Song("New song", "New artist");
//        playlist.addSong(newSong);
//        assertEquals(2, playlist.getListOfSongs().size());
//        assertEquals(newSong, playlist.getListOfSongs().get(1));
//    }
//
////    @Test
////    void testSongAlreadyThere() {
////        playlistWithSong.addSong(testSong);
////        assertEquals(1, playlistWithSong.getListOfSongs().size());
////        assertEquals(testSong, playlistWithSong.getListOfSongs());
////        assertEquals(testSong, playlistWithSong.getListOfSongs().get(0));
////    }
////
////    @Test
////    void testPlaylistConstructor() {
////        assertEquals(null, playlist.getListOfSongs());
////        assertEquals(0, playlist.getListOfSongs().size());
////    }
////
////    @Test
////    void testPlaylistRemoveSongNotThere() {
////        playlist.removeSong(testSong);
////        assertEquals(null, playlist.getListOfSongs());
////        assertEquals(0, playlist.getListOfSongs().size());
////    }
////
////    @Test
////    void testPlaylistRemoveSongThere() {
////        playlistWithSong.removeSong(testSong);
////        assertEquals(null, playlistWithSong.getListOfSongs());
////        assertEquals(0, playlistWithSong.getListOfSongs().size());
////    }
////
////    @Test
////    void testPlaylistRemoveTwoSongsThere() {
////        Song newSong = new Song("New song", "New artist");
////        playlistWithSong.addSong(newSong);
////        playlistWithSong.removeSong(testSong);
////        assertEquals(newSong, playlistWithSong.getListOfSongs());
////        assertEquals(1, playlistWithSong.getListOfSongs().size());
////        playlistWithSong.removeSong(newSong);
////        assertEquals(null, playlistWithSong.getListOfSongs());
////        assertEquals(0, playlistWithSong.getListOfSongs().size());
////    }
////
////    @Test
////    void testPlaylistRemoveSameSongTwice() {
////        Song newSong = new Song("New song", "New artist");
////        playlistWithSong.addSong(newSong);
////        playlistWithSong.removeSong(testSong);
////        assertEquals(newSong, playlistWithSong.getListOfSongs());
////        assertEquals(1, playlistWithSong.getListOfSongs().size());
////        playlistWithSong.removeSong(testSong);
////        assertEquals(newSong, playlistWithSong.getListOfSongs());
////        assertEquals(1, playlistWithSong.getListOfSongs().size());
////    }
////
//}
