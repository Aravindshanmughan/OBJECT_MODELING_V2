package com.crio.jukebox.ServicesTest;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;


import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;

import com.crio.jukebox.services.PlayListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayListServiceTest {
    @Mock
    private  IPlayListRepository playListRepositoryMock;
    @Mock                                   
    private ISongRepository songRepositoryMock;
    @Mock                   
    private  IUserRepository userRepositoryMock;

    @InjectMocks
    PlayListService playListService;

    @Test
    @DisplayName("Create method Throw UserNotFoundException If No Creator User Found")
    public void playlistTest1(){

        //Arrange
      when(userRepositoryMock.findbyId(anyString())).thenReturn(Optional.empty());

      //Act and Assert
      Assertions.assertThrows(UserNotFoundException.class,()->playListService.createPlaylist( "playListName", anyList(), "1"));
    }


    @Test
    @DisplayName("Delete Playlist - User Not Found")
    public void deletePlaylist_UserNotFound() {
        // Arrange
        String userId = "1";
        String playlistId = "123";

        when(userRepositoryMock.findUser(userId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(UserNotFoundException.class, () -> playListService.deletePlaylist(userId, playlistId));

    }

    @Test
    @DisplayName("PlayPlayList")
    public void playPlayList(){


        //Arrange
        User user=new User("1", "Kiran");
        Song expectedSong= new Song("1", "Heavy", "No one","Pop", "EdSheeran", Arrays.asList("OtherArtist1", "OtherArtist2"));
        List<Song> songs =  new ArrayList<Song>(){
          {
            add(new Song("1", "Heavy", "No one", "Pop", "EdSheeran", Arrays.asList("OtherArtist1", "OtherArtist2")));
            add(new Song("2", "Rhythm of Love", "Someone Else", "R&B", "Adele", Arrays.asList("AnotherArtist1", "AnotherArtist2")));
        }
      };
        Playlist playlist=new Playlist("1", "MY_PLAYLIST_1", songs,user);
        when(playListRepositoryMock.findbyId("1")).thenReturn(Optional.of(playlist));

        //Act
       Song actualSong= playListService.playPlayList("1", "1");

       //Assert
        Assertions.assertEquals(expectedSong, actualSong);
    }

    @Test
    @DisplayName("PlaySong in playlist using_NEXT_Command")
    public void playSong1(){

        //Arrange
        Song expectedSong=new Song("2", "Rhythm of Love", "Someone Else", "R&B", "Adele", Arrays.asList("AnotherArtist1", "AnotherArtist2"));
        User user=new User("1", "Kiran");
        List<Song> songs =  new ArrayList<Song>(){
            {
              add(new Song("1", "Heavy", "No one", "Pop", "EdSheeran", Arrays.asList("OtherArtist1", "OtherArtist2")));
              add(new Song("2", "Rhythm of Love", "Someone Else", "R&B", "Adele", Arrays.asList("AnotherArtist1", "AnotherArtist2")));
          }
        };
          Playlist playlist=new Playlist("1", "MY_PLAYLIST_1", songs,user);
        when(playListRepositoryMock.findbyId("1")).thenReturn(Optional.of(playlist));
        when(playListRepositoryMock.getSongsFromPlaylist("1")).thenReturn((songs));

        //Act
        Song actualSong=playListService.playSong("1", "NEXT");

        //Assert
        Assertions.assertEquals(expectedSong, actualSong);

    
}

@Test
    @DisplayName("PlaySong in playlist using_BACK_Command")
    public void playSong2(){

        //Arrange
        Song expectedSong=new Song("4", "Bohemian Rhapsody", "A Night at the Opera", "Rock", "Queen", Arrays.asList("None"));
        User user=new User("1", "Kiran");
        List<Song> songs =  new ArrayList<Song>(){
            {
              add(new Song("1", "Heavy", "No one", "Pop", "EdSheeran", Arrays.asList("OtherArtist1", "OtherArtist2")));
              add(new Song("2", "Rhythm of Love", "Someone Else", "R&B", "Adele", Arrays.asList("AnotherArtist1", "AnotherArtist2")));
              add(new Song ("3", "Shape of You", "Divide", "Pop", "Ed Sheeran", Arrays.asList("None")));
              add(new Song("4", "Bohemian Rhapsody", "A Night at the Opera", "Rock", "Queen", Arrays.asList("None")));
          }
        };
          Playlist playlist=new Playlist("1", "MY_PLAYLIST_1", songs,user);
        when(playListRepositoryMock.findbyId("1")).thenReturn(Optional.of(playlist));
        when(playListRepositoryMock.getSongsFromPlaylist("1")).thenReturn((songs));

        //Act
        Song actualSong=playListService.playSong("1", "BACK");

        //Assert
        Assertions.assertEquals(expectedSong, actualSong);

    
}
    @Test
    @DisplayName("PlaySong in playlist using_SongID")
    public void playSong3(){

        //Arrange
        Song expectedSong=new Song ("3", "Shape of You", "Divide", "Pop", "Ed Sheeran", Arrays.asList("None"));
        User user=new User("1", "Kiran");
        List<Song> songs =  new ArrayList<Song>(){
            {
              add(new Song("1", "Heavy", "No one", "Pop", "EdSheeran", Arrays.asList("OtherArtist1", "OtherArtist2")));
              add(new Song("2", "Rhythm of Love", "Someone Else", "R&B", "Adele", Arrays.asList("AnotherArtist1", "AnotherArtist2")));
              add(new Song ("3", "Shape of You", "Divide", "Pop", "Ed Sheeran", Arrays.asList("None")));
              add(new Song("4", "Bohemian Rhapsody", "A Night at the Opera", "Rock", "Queen", Arrays.asList("None")));
          }
        };
        Song song =new Song ("3", "Shape of You", "Divide", "Pop", "Ed Sheeran", Arrays.asList("None"));
          Playlist playlist=new Playlist("1", "MY_PLAYLIST_1", songs,user);
        when(playListRepositoryMock.findbyId("1")).thenReturn(Optional.of(playlist));
        when(playListRepositoryMock.getSongsFromPlaylist("1")).thenReturn((songs));
        // when(songRepositoryMock.findbyId("3")).thenReturn(Optional.of(song));
        when(songRepositoryMock.findSongbyId("3")).thenReturn(song);

        //Act
        Song actualSong=playListService.playSong("1", "3");

        //Assert
        Assertions.assertEquals(expectedSong, actualSong);

    
}

@Test
@DisplayName("Modify PlayList using ADD-SONG Command")
public void modifyPlaylist1(){

    //Arrange
    
    User user=new User("1", "Kiran");
    List<Song> song1 =  new ArrayList<Song>(){
        {
          add(new Song("1", "Heavy", "No one", "Pop", "EdSheeran", Arrays.asList("OtherArtist1", "OtherArtist2")));
          add(new Song("2", "Rhythm of Love", "Someone Else", "R&B", "Adele", Arrays.asList("AnotherArtist1", "AnotherArtist2")));
          add(new Song ("3", "Shape of You", "Divide", "Pop", "Ed Sheeran", Arrays.asList("None")));
          add(new Song("4", "Bohemian Rhapsody", "A Night at the Opera", "Rock", "Queen", Arrays.asList("None")));
      }
    };
    
   
    Song song5 = new Song("5", "Dancing Queen", "Arrival", "Pop", "ABBA", Arrays.asList("ABBA"));
    Song song6 =new Song("6", "Hotel California", "Hotel California", "Rock", "Eagles", Arrays.asList("Eagles"));
    // Song song7 =new Song("7", "Bohemian Rhapsody", "A Night at the Opera", "Rock", "Queen", Arrays.asList("Queen"));
    // Song song8 =new Song("8", "Yesterday", "Help!", "Pop", "The Beatles", Arrays.asList("The Beatles"));
  
 
      Playlist playlist=new Playlist("1", "MY_PLAYLIST_1", song1,user);
     Playlist expectedPlaylist=new Playlist(playlist);
     expectedPlaylist.getSongs().add(song5);
     expectedPlaylist.getSongs().add(song6);
      when(userRepositoryMock.findUser("1")).thenReturn(Optional.of(user));
    when(playListRepositoryMock.findbyId("1")).thenReturn(Optional.of(playlist));
   when(songRepositoryMock.findByListofId(Arrays.asList("5","6"))).thenReturn(Arrays.asList(song5,song6));

    //Act
   Playlist actualplaylist=playListService.modifyPlayList("1","1",Arrays.asList("5","6"),"ADD-SONG");

    //Assert
    Assertions.assertEquals(expectedPlaylist, actualplaylist);


}
@Test
@DisplayName("Modify PlayList using DELETE-SONG Command")
public void modifyPlaylist2(){

    //Arrange
    
    User user=new User("1", "Kiran");
    List<Song> song1 =  new ArrayList<Song>(){
        {
          add(new Song("1", "Heavy", "No one", "Pop", "EdSheeran", Arrays.asList("OtherArtist1", "OtherArtist2")));
          add(new Song("2", "Rhythm of Love", "Someone Else", "R&B", "Adele", Arrays.asList("AnotherArtist1", "AnotherArtist2")));
          add(new Song ("3", "Shape of You", "Divide", "Pop", "Ed Sheeran", Arrays.asList("None")));
          add(new Song("4", "Bohemian Rhapsody", "A Night at the Opera", "Rock", "Queen", Arrays.asList("None")));
          add(new Song("5", "Dancing Queen", "Arrival", "Pop", "ABBA", Arrays.asList("ABBA")));
          add(new Song("6", "Hotel California", "Hotel California", "Rock", "Eagles", Arrays.asList("Eagles")));
      }
    };
    
   
    Song song5 = new Song("5", "Dancing Queen", "Arrival", "Pop", "ABBA", Arrays.asList("ABBA"));
    Song song6 =new Song("6", "Hotel California", "Hotel California", "Rock", "Eagles", Arrays.asList("Eagles"));
    // Song song7 =new Song("7", "Bohemian Rhapsody", "A Night at the Opera", "Rock", "Queen", Arrays.asList("Queen"));
    // Song song8 =new Song("8", "Yesterday", "Help!", "Pop", "The Beatles", Arrays.asList("The Beatles"));
  
 
      Playlist playlist=new Playlist("1", "MY_PLAYLIST_1", song1,user);
     Playlist expectedPlaylist=new Playlist(playlist);
     expectedPlaylist.getSongs().remove(song5);
     expectedPlaylist.getSongs().remove(song6);
      when(userRepositoryMock.findUser("1")).thenReturn(Optional.of(user));
    when(playListRepositoryMock.findbyId("1")).thenReturn(Optional.of(playlist));
   when(songRepositoryMock.findByListofId(Arrays.asList("5","6"))).thenReturn(Arrays.asList(song5,song6));

    //Act
   Playlist actualplaylist=playListService.modifyPlayList("1","1",Arrays.asList("5","6"),"DELETE-SONG");

    //Assert
    Assertions.assertEquals(expectedPlaylist, actualplaylist);


}
}

