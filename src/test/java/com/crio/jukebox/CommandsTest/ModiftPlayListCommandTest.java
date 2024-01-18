package com.crio.jukebox.CommandsTest;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.commands.ModifyPalylistCommand;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IPlayListService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ModiftPlayListCommandTest {

    private final PrintStream standardOut=System.out;
    private final ByteArrayOutputStream outputStreamCaptor=new ByteArrayOutputStream();
  
 
    @Mock
    IPlayListService playListServiceMock;

    @InjectMocks
    ModifyPalylistCommand playListCommand;

    @BeforeEach
    public void setup(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    @Test
    @DisplayName("ModifyPlayListTest")
    public void modifyPlayList() {
   
   //Arrange
   String expectedOutput = "PlayListId: 1, PlayListName: My Playlist, SongIds:[1, 2] ";
  
   
   User user=new User("1", "Kiran");
 List<Song> songs1 =  new ArrayList<Song>(){
  {
    add(new Song("1", "Heavy", "No one", "Pop", "EdSheeran", Arrays.asList("OtherArtist1", "OtherArtist2")));
    add(new Song("2", "Rhythm of Love", "Someone Else", "R&B", "Adele", Arrays.asList("AnotherArtist1", "AnotherArtist2")));
}
 };

 Playlist playList1=new Playlist("1", "My Playlist", songs1, user);

 

   when(playListServiceMock.modifyPlayList("1", "1", Arrays.asList("1","2"), "ADD-SONG")).thenReturn(playList1);


      //Act
      playListCommand.execute(List.of("MODIFY-PLAYLIST","ADD-SONG","1","1","1","2"));

    //Assert
    Assertions.assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
   verify(playListServiceMock, times(1)).modifyPlayList("1", "1", Arrays.asList("1","2"), "ADD-SONG");

   }
  
   
   @AfterEach
   public void tearDown() {
       System.setOut(standardOut);
   }
    
    
}

