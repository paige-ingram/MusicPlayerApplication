package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.sun.tools.javac.util.List;
import model.MusicPlayer;
import model.Playlist;
import model.Song;
import org.json.*;

// Citation: JsonSerializationDemo - JsonReader.java
// Represents a reader that reads playlist from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

//    // EFFECTS: reads music player from file and returns it;   // help! Do I read both playlist and musc player?
//    // throws IOException if an error occurs reading data from file
//    public Playlist read() throws IOException {
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parsePlaylist(jsonObject);
//    }

    // EFFECTS: reads music player from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MusicPlayer read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMusicPlayer(jsonObject);
    }



    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses playlists from JSON object and returns it
    private MusicPlayer parseMusicPlayer(JSONObject jsonObject) {
        String name = jsonObject.getString("music player name");
        MusicPlayer musicPlayer = new MusicPlayer(name);
        addPlaylists(musicPlayer, jsonObject);
        return musicPlayer;
    }

    // EFFECTS: parses playlists from JSON object and returns it
    private Playlist parsePlaylist(JSONObject jsonObject) {
        String name = jsonObject.getString("playlist name");
        Playlist playlist = new Playlist(name);
        addSongs(playlist, jsonObject);
        return playlist;
    }

    // MODIFIES: music player
    // EFFECTS: parses playlists from JSON object and adds them to music player
    private void addPlaylists(MusicPlayer musicPlayer, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("playlists");
        for (Object json : jsonArray) {
            JSONObject nextPlaylist = (JSONObject) json;
            addPlaylist(musicPlayer, nextPlaylist);
        }
    }

    // MODIFIES: music player
    // EFFECTS: parses playlist from JSON object and adds it to music player
    private void addPlaylist(MusicPlayer musicPlayer, JSONObject jsonObject) {
        String name = jsonObject.getString("playlist name");
        // jsonObject.getPlaylistName("playlist name");
       // ArrayList<Song> listOfSongs = JSONArray.getListOfSongs();  // where can i put this list of songs
        //ArrayList<Playlist> listOfPlaylists = jsonObject.getString(musicPlayer.getMpName()); // what to put here
       // Playlist playlist = new Playlist(name);
        //musicPlayer.addPlaylist(playlist); // does Playlist() need to have another parameter?
        musicPlayer.addPlaylist(parsePlaylist(jsonObject));
    }

    // MODIFIES: playlist
    // EFFECTS: parses songs from JSON object and adds them to playlist
    private void addSongs(Playlist playlist, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("playlist songs");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(playlist, nextSong);
        }
    }

    // MODIFIES: playlist
    // EFFECTS: parses song from JSON object and adds it to playlist
    private void addSong(Playlist playlist, JSONObject jsonObject) {
        String name = jsonObject.getString("song name");
        String artist = jsonObject.getString("artist");
        Song song = new Song(name, artist);
        playlist.addSong(song);
    }
}