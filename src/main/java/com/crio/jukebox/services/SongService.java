package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;


public class SongService implements ISongService {

    IUserRepository userRepository;
    ISongRepository songRepository;
    IPlayListRepository playListRepository;

    


    public SongService(IUserRepository userRepository, ISongRepository songRepository,
            IPlayListRepository playListRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.playListRepository = playListRepository;
    }


     /*Load songs from a CSC File */
    public void loadSongsFromCSV(String file){
       
        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(file))){
           String row;
           while((row = bufferedReader.readLine()) != null){
            String[] data = row.split(",");
            if (data.length >= 6) {
            String songID = data[0];
                String songName = data[1];
                String genre = data[2];
                String album = data[3];
                String mainArtist=data[4];
                List<String> otherArtists = Arrays.asList(data[5].split("#"));
            

           Song song =new Song(songID, songName, album, genre, mainArtist, otherArtists);
           songRepository.save(song);
           } 
        }
        }catch(SongNotFoundException| IOException e ){
          System.out.println("Songs Cannot be loaded");
    }
}

    @Override
    public Song createSong(String songID, String songName, String album, String genre,
            String mainArtist, List<String> otherArtist) {
        // TODO Auto-generated method stub
        final Song song=new Song(songID, songName, album, genre, mainArtist, otherArtist);
        return songRepository.save(song);
        
    }

   
       
        
    
}
