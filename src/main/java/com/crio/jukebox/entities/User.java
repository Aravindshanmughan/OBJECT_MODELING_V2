package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class User{

    private String userID;
    private String userName;
    private List<Playlist> playlists;
    UserPlaylistSongs userPlaylistSongs;

    public User(User user){
        this(user.userID,user.userName);
        userPlaylistSongs=user.userPlaylistSongs;
    }
    public User(String id, String name,List<Playlist> playlists) {
        this(id,name);
        this.playlists = playlists;
    }

    
        

    public User(String userName) {
        
        this.userName = userName;
    }
    public User(String userID, String userName) {
        this.userID = userID;
        this.userName = userName;
        this.userPlaylistSongs=new UserPlaylistSongs();
        this.playlists=new ArrayList<Playlist>();
    }
    
    public String getUserID() {
        return userID;
    }
    public String getUserName() {
        return userName;
    }


    public List<Playlist> getPlaylists() {
        return playlists.stream().collect(Collectors.toList());
    }

    public boolean playListExists(Playlist playlist){
        if(playlists.contains(playlist)){
            return true;
        }else
        return false;
    }

    public void addPlaylist(Playlist playlist){
        playlists.add(playlist);
        
    }

    public void deletePlayList(Playlist playlist){
        playlists.removeIf(c->c.getPlaylistID().equals(playlist.getPlaylistID()));
    }

    public void addSongsToPlayList(Playlist playlist, List<Song> qList){
        userPlaylistSongs.addPlaylistSongs(playlist, qList);
    }

    public List<Song> getSongsByPlayList(Playlist playlist){
        return userPlaylistSongs.getSongsbyPlaylist(playlist);
    }

    public boolean checkPlayListExists(Playlist playlist){

        if(playlists.contains(playlist)){
            return true;
        }else
        return false;

    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(userName, other.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
  
//   @Override
//     public String toString() {
//         return "User [userID=" + userID + ", userName=" + userName + "]";
//     }

    @Override
    public String toString() {
         return userID + " " + userName;
    }
    

    
     
    
   
    }

    

    


    

