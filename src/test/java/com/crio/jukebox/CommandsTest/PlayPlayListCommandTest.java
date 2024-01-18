package com.crio.jukebox.CommandsTest;


import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.PlayPlayListCommand;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IPlayListService;
import com.crio.jukebox.services.IUserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ExtendWith(MockitoExtension.class)
public class PlayPlayListCommandTest {
    
    private final PrintStream standardOut=System.out;
    private final ByteArrayOutputStream outputStreamCaptor=new ByteArrayOutputStream();
  
    @BeforeEach
    public void setup(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Mock
    IPlayListService playListServiceMock;

    @InjectMocks
    PlayPlayListCommand playListCommand;


    @Test
    @DisplayName("PlayPlayListTest")
    public void playPlayList() {
   
   //Arrange
   String expectedOutput = "Playing Current Song Song{songId='1', songName='songName', album='album', genre='genre', mainArtist='mainArtist', otherArtist=[]}";
   
 String playListId="1";
 String songId="1";
  Song song=new Song("1", "songName", "album", "genre", "mainArtist", Collections.emptyList());
  when(playListServiceMock.playPlayList(playListId, songId)).thenReturn(song);


      //Act
      playListCommand.execute(List.of("PLAY-PLAYLIST","1","1"));

    //Assert
    Assertions.assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
  //  verify(playListServiceMock, times(1)).playPlayList(anyString(), anyString());

   }
   
   @AfterEach
   public void tearDown() {
       System.setOut(standardOut);
   }
    


    
}

