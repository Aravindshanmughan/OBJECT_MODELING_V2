package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepository {

    private final Map<String,Song> songMap;
    private Integer autoIncrement = 0;


    public SongRepository() {
    songMap=new HashMap<String,Song>();
    }
    

    public SongRepository(Map<String, Song> songMap) {
        this.songMap = songMap;
        this.autoIncrement=songMap.size();
    }


    @Override
    public Song save(Song entity) {
        // TODO Auto-generated method stub
        if(entity.getSongID()==null){
            autoIncrement++;
            Song s=new Song(Integer.toString(autoIncrement), entity.getSongName(), entity.getAlbum(), entity.getGenre(), entity.getMainArtist(), entity.getOtherArtist());
            songMap.put(s.getSongID(), s);
            return s;
        }
        songMap.put(entity.getSongID(), entity);
        return entity;
    }

    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return songMap.values().stream()
        .collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findbyId(String id) {
        // TODO Auto-generated method stub
       return Optional.ofNullable(songMap.get(id));
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
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void count() {
        // TODO Auto-generated method stub
        
    }

  


    @Override
    public Song findSongbyId(String songId) {
        // TODO Auto-generated method stub
        return songMap.values().stream()
                .filter(a->a.getSongID().equals(songId))
                .findFirst().orElse(null);

                }


    @Override
    public List<Song> findByListofId(List<String> id) {
        // TODO Auto-generated method stub
        List<Song> songs=new ArrayList<>();
        for(String result:id){
            Song song=songMap.get(result);
            songs.add(song);

        }
        return songs;
    }


    }
