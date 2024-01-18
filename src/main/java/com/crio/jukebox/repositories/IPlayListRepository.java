package com.crio.jukebox.repositories;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;



public interface IPlayListRepository extends CRUDRepository<Playlist,String>{
    public List<Song> getSongsFromPlaylist(String playlistId);
    
}
