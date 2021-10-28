package persistence;

import model.Playlist;
import model.Song;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Citation: JsonSerializationDemo - JsonTest.java
public class JsonTest {

    protected void checkPlaylist(String playlistName, ArrayList<Song> listOfSongs, Playlist playlist) {
        assertEquals(playlistName, playlist.getPlaylistName());
        assertEquals(listOfSongs.size(), playlist.getListOfSongs().size());
    }

//    protected void checkSong(String songName, String artistName, Song song) {
//        assertEquals(songName, song.getName());
//        assertEquals(artistName, song.getArtist());
//    }
}
