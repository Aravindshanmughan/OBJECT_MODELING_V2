package com.crio.jukebox.entities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.repositories.SongRepository;

public class Playlist{
    private String playlistID;
    private  String playListName;
    private  List<Song> songs;
     private  User creator;
   
   

   public Playlist(Playlist playlist){
    this(playlist.playlistID,playlist.playListName,playlist.songs,playlist.creator);
   }

    public Playlist(String playlistID, String playListName, List<Song> songs, User creator) {
    this.playlistID = playlistID;
    this.playListName = playListName;
    this.songs = songs;
    this.creator = creator;
}



    public Playlist(String playlistID, String playListName, User creator) {
        this.playlistID = playlistID;
        this.playListName = playListName;
        this.creator = creator;
    }
    


    public Playlist(String playListName, User creator) {
        this.playListName = playListName;
        this.creator = creator;
    }
    

    public Playlist(String playListName, List<Song> songs, User creator) {
        this.playListName = playListName;
        this.songs = songs;
        this.creator = creator;
    }
    

   

    public String getPlaylistID() {
        return playlistID;
    }
    public String getPlayListName() {
        return playListName;
    }

        public User getCreator() {
        return creator;
    }

    public List<Song> getSongs() {
        if(songs!=null){
        return songs.stream().collect(Collectors.toList());
        }else return Collections.emptyList();

    }

    public void addNewSongs(List<Song> newSongs) {
        this.songs.addAll(newSongs);
    }
    public void deleteSongs(List<Song> songsToDelete) {
        this.songs.removeAll(songsToDelete);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Playlist other = (Playlist) obj;
        if (playlistID == null) {
            if (other.playlistID != null)
                return false;
        } else if (!playlistID.equals(other.playlistID))
            return false;
        return true;
    }

    // @Override
    // public String toString() {
    //     return "Playlist [playlistID=" + playlistID + "]";
    // }

    @Override
    public String toString() {
        return "Playlist ID - " + playlistID;
    }

   
  
    
   
}
