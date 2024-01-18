package com.crio.jukebox.CommandsTest;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.commands.CreatePlaylistCommand;
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

@DisplayName("CreatePlayListCommandTest")
@ExtendWith(MockitoExtension.class)
public class CreatePlayListCommandTest {

    private final PrintStream standardOut=System.out;
    private final ByteArrayOutputStream outputStreamCaptor=new ByteArrayOutputStream();
  
  @Mock
  IPlayListService playListServiceMock;
  @InjectMocks
  CreatePlaylistCommand playlistCommand;

  @BeforeEach
  public void setup(){
    System.setOut(new PrintStream(outputStreamCaptor));
  }

    @Test
    @DisplayName("execute method of CreatePlayListCommand Should Print newly Created Playlist To Console")
    public void execute_ShouldPrintNewlyCreatedPlayList() {

        //Arrange
        String expectedOutput="Playlist [playlistID=1]";
        User user=new User("1", "Kiran");
  
        List<Song> songs =  new ArrayList<Song>(){
          {
            add(new Song("1", "Heavy", "No one", "Pop", "EdSheeran", Arrays.asList("OtherArtist1", "OtherArtist2")));
            add(new Song("2", "Rhythm of Love", "Someone Else", "R&B", "Adele", Arrays.asList("AnotherArtist1", "AnotherArtist2")));
        }
      };
        Playlist playlist=new Playlist("1", "MY_PLAYLIST_1", songs,user);
     when(playListServiceMock.createPlaylist( anyString(),anyList(), anyString())).thenReturn(playlist);

     //ACT
     playlistCommand.execute(List.of("CREATE-PLAYLIST","1","MY_PLAYLIST_1","1","4","5","6"));

     //Assert
     Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
     verify(playListServiceMock,times(1)).createPlaylist( anyString(), anyList(), anyString());
  }

  
  @AfterEach
  public void tearDown() {
      System.setOut(standardOut);
  }
    
}
