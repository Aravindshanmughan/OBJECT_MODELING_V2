package com.crio.jukebox.services;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;



public class PlayListService implements IPlayListService {
  
    


    private  IPlayListRepository playListRepository;
    private ISongRepository songRepository;
    private  IUserRepository userRepository;
    /* Creates a Map for storing the indices of Song in a Playlist*/
    Map<Integer, Integer> playlistIndices = new HashMap<>();
  
  public PlayListService(IPlayListRepository playListRepository, ISongRepository songRepository,
  IUserRepository userRepository) {
this.playListRepository = playListRepository;
this.songRepository = songRepository;
this.userRepository = userRepository;
}
  
    @Override
    public Playlist createPlaylist(String playListName, List<String> songIds,String id) {
        // TODO Auto-generated method stub
        final User user=userRepository.findbyId(id).orElseThrow(()->new UserNotFoundException("User Not Found"));
       
         List<Song> songs=songRepository.findByListofId(songIds);
         if(songs.isEmpty()){
            return  playListRepository.save(new Playlist(playListName, Collections.emptyList(), user));
           
         }else{
            return playListRepository.save(new Playlist( playListName, songs, user));
        
         }
           
        }
        
       
    
        
    @Override
    public void deletePlaylist( String userId,String playListId) {
        // TODO Auto-generated method stub
      
        userRepository.findUser(userId).orElseThrow(()->new UserNotFoundException("User not found"));
      
        playListRepository.deleteById(playListId);
       
       
    }
    
    

    @Override
    public Song playPlayList( String playListId,String songId) {
        // TODO Auto-generated method stub
      Playlist playlist= playListRepository.findbyId(playListId).orElseThrow(()->new PlayListNotFoundException("playList not Found"));
   
      List<Song> songs=playlist.getSongs();
     Song song=songs.stream()
            .filter(a->a.getSongID().equals(songId))
            .findFirst().orElseThrow(()->new SongNotFoundException("Song not Found"));
    return song;
            
        
     }
    

     @Override
     /*Play Songs and Change songs using NEXT and BACK commands */
    public Song playSong(String playlistId, String songId) {

        // TODO Auto-generated method stub
    playListRepository.findbyId(playlistId).orElseThrow(()->new PlayListNotFoundException("playList not Found"));
     List<Song> songs=playListRepository.getSongsFromPlaylist(playlistId);


    
    for (int i = 0; i < songs.size(); i++) {
        if (songs.get(i).getSongID().equals(songId)) {
            
            playlistIndices.put(1, i);
            break;
        }
    }
        Integer currentIndex=playlistIndices.get(1);
        if (currentIndex==null){
            currentIndex=0;
        }
         switch (songId) {
            case "NEXT":
         
           return  playNextSong(songs, currentIndex);
             
            case "BACK":
            
           return playPreviousSong(songs, currentIndex);
    
           default:
           List<Song> songsInPlaylist = playListRepository.getSongsFromPlaylist(playlistId);
    boolean songExistsInPlaylist = songsInPlaylist.stream().anyMatch(song -> song.getSongID().equals(songId));
    
    if (songExistsInPlaylist) {
        return songRepository.findSongbyId(songId);
    } else {
        return null; 
    }
                 
        }
    
    }
        
    


     public Song playNextSong(List<Song> songs, int currentIndex) {
        int nextIndex = (currentIndex + 1) % songs.size();
        Song nextSong = songs.get(nextIndex);
        playlistIndices.put(1, nextIndex);
      return nextSong;
        
    }
    public Song playPreviousSong(List<Song> songs, int currentIndex) {
        int previousIndex = (currentIndex - 1 + songs.size()) % songs.size();
        Song previousSong = songs.get(previousIndex);
        playlistIndices.put(1, previousIndex);
       return previousSong;
    }

    @Override
    /*We can Modify PlayList using ADD-SONG and DELETE-SONG COMMANDS */
    public Playlist modifyPlayList(String userId, String playListId, List<String> songId,
            String command) {
        // TODO Auto-generated method stub
        if(userRepository.findUser(userId).isPresent()){
           playListRepository.findbyId(playListId).orElseThrow(()->new PlayListNotFoundException("PlaylistNotFound"));
          
           
                        switch(command){
                            case "ADD-SONG":
                           return addSongtoPlayList(playListId, songId);
                           
                             
                            case "DELETE-SONG":
                            return deleteSongfromPlayList(playListId, songId);
                            
                            
                            default:
                            throw new IllegalArgumentException("Unsupported command: " + command); 
        
                        }
                }else {
                    throw new UserNotFoundException("User not found: " + userId);
                }
        
    }

    @Override
    public Playlist addSongtoPlayList(String playListId, List<String> SongId) {
        // TODO Auto-generated method stub
        Playlist playlist= playListRepository.findbyId(playListId).orElseThrow(()->new PlayListNotFoundException("playList not Found"));
        List<Song> songsToAdd = songRepository.findByListofId(SongId);
        playlist.addNewSongs(songsToAdd);
       return playlist;
        
    }

    @Override
    public Playlist deleteSongfromPlayList(String playListId, List<String> SongId) {
        // TODO Auto-generated method stub
        Playlist playlist=playListRepository.findbyId(playListId).orElseThrow(()->new PlayListNotFoundException("playList not Found"));
        List<Song> songsToDelete = songRepository.findByListofId(SongId);
       playlist.deleteSongs(songsToDelete);
       return playlist;
       
    }
}




     
    

