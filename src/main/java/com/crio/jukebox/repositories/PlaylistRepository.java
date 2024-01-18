package com.crio.jukebox.repositories;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;


public class PlaylistRepository implements IPlayListRepository {


    private final Map<String,Playlist> playlistMap;
    private Integer autoIncrement = 0;
   


    public PlaylistRepository(){

        playlistMap=new HashMap<String,Playlist>();
    }

    public PlaylistRepository(Map<String,Playlist> playlist){
        this.playlistMap=playlist;
        this.autoIncrement=playlist.size();

    }

    @Override
    public Playlist save(Playlist entity) {
        // TODO Auto-generated method stub

        if(entity.getPlaylistID()==null){
            autoIncrement++;
            Playlist p=new Playlist(Integer.toString(autoIncrement), entity.getPlayListName(), entity.getSongs(),entity.getCreator());
            playlistMap.put(p.getPlaylistID(), p);
            return p;
        }
        playlistMap.put(entity.getPlaylistID(), entity);
        return entity;
    }

    @Override
    public List<Playlist> findAll() {
        // TODO Auto-generated method stub
        return playlistMap.values().stream()
                          .collect(Collectors.toList());
    }

    @Override
    public Optional<Playlist> findbyId(String id) {
        // TODO Auto-generated method stub

        return playlistMap.values().stream()
                    .filter(a->a.getPlaylistID().equals(id))
                    .findFirst();
       
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub    
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void count() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Song> getSongsFromPlaylist(String playlistId) {
        // TODO Auto-generated method stub
        Playlist playlist = playlistMap.get(playlistId);
        if (playlist != null) {
            return playlist.getSongs(); 
        } else {
            return Collections.emptyList(); 
        }
    }

  

   
    }


   

     
    


