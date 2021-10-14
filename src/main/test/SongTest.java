package test;

import model.Song;
import org.junit.jupiter.api.BeforeEach;

public class SongTest {
    Song song;

    @BeforeEach
    public void setup() {
        song = new Song("Song name", "Song artist");
    }


    public void testGetName() {

    }




}
