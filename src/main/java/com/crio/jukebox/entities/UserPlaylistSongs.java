package com.crio.jukebox.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPlaylistSongs {

    private final Map<Playlist,List<Song>> playlistSongsMap;

    public UserPlaylistSongs(){

        playlistSongsMap=new HashMap<Playlist,List<Song>>();
    }

    public UserPlaylistSongs(Map<Playlist, List<Song>> playlistSongsMap) {
        this.playlistSongsMap = playlistSongsMap;
    }

    public void addPlaylistSongs(Playlist playlist, List<Song> qList){
        playlistSongsMap.putIfAbsent(playlist, qList);

    }
    

    public List<Song> getSongsbyPlaylist(Playlist playlist){
       
        return playlistSongsMap.get(playlist);
    }

    @Override
    public String toString() {
        return "UserPlaylistSongs [playlistSongsMap=" + playlistSongsMap + "]";
    }
    
    


    
}
