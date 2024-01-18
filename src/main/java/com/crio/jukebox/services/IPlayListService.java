package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public interface IPlayListService {

    Playlist createPlaylist(String playListName, List<String> songIds,String id);

    void deletePlaylist(String userId,String playListId);

   Song playPlayList(String playListId,String songId);

   Playlist addSongtoPlayList(String playListId,List<String> SongId);

   Playlist deleteSongfromPlayList(String playListId,List<String> SongId);

  Playlist modifyPlayList(String userId,String playListId,List<String> songId,String command);
  
   public Song playSong(String playlistId, String songId);
  
   
 
    
}
