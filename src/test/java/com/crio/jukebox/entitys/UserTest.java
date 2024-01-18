package com.crio.jukebox.entitys;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
 
  @Test 
  @DisplayName("checkIfContestExists should Return true If Playlist is Found") 
  public void checkPlayListTest(){
     
    String id="1";
    String name="PlayList";
    List<Song> songs =  new ArrayList<Song>(){
      {
        add(new Song("1", "Heavy", "No one", "Pop", "EdSheeran", Arrays.asList("OtherArtist1", "OtherArtist2")));
        add(new Song("2", "Rhythm of Love", "Someone Else", "R&B", "Adele", Arrays.asList("AnotherArtist1", "AnotherArtist2")));
    }
  };
    User user=new User("1","HeavyUser");
  Playlist playlist=new Playlist(id, name, songs,user );
User user1=new User("2", name, new ArrayList<Playlist>(){{add(playlist);}});


  }
 

  @Test 
  @DisplayName("checkIfContestExists should Return false If Playlist is not Found") 
  public void checkPlayListTest2(){
     
    String id="1";
    String name="PlayList";
    List<Song> songs =  new ArrayList<Song>(){
      {
        add(new Song("1", "Heavy", "No one", "Pop", "EdSheeran", Arrays.asList("OtherArtist1", "OtherArtist2")));
        add(new Song("2", "Rhythm of Love", "Someone Else", "R&B", "Adele", Arrays.asList("AnotherArtist1", "AnotherArtist2")));
    }
  };
    User user=new User("1","HeavyUser");
  Playlist playlist=new Playlist(id, name, songs,user );
User user1=new User("2", name);


  }
}



 
  



