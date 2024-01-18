package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Song;

public interface ISongService {
    
    Song createSong(String songID, String songName, String album, String genre, String mainArtist,
    List<String> otherArtist);
    
    void loadSongsFromCSV(String file);
}
