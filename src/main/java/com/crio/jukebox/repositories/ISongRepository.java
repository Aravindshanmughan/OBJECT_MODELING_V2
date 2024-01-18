package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.Song;

public interface ISongRepository extends CRUDRepository<Song,String> {
    
    public List<Song> findByListofId(List<String> id);
    public Song findSongbyId(String songId);
    
    
}
