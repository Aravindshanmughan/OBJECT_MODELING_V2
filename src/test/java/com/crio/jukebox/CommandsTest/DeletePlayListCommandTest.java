package com.crio.jukebox.CommandsTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import com.crio.jukebox.commands.DeletePlayListCommand;
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
public class DeletePlayListCommandTest {

    private final PrintStream standardOut=System.out;
    private final ByteArrayOutputStream outputStreamCaptor=new ByteArrayOutputStream();
  
  @Mock
  IPlayListService playListServiceMock;
  @InjectMocks
  DeletePlayListCommand playlistCommand;

  @BeforeEach
  public void setup(){
    System.setOut(new PrintStream(outputStreamCaptor));
  }
  

    @Test
    @DisplayName("execute method of CreatePlayListCommand Should Print newly Created Playlist To Console")
    public void execute_ShouldDeleteNewlyCreatedPlayList() {

        //Arrange
        String expectedOutput="Delete Successful";
        String userId = "1";
        String playListId = "MY_PLAYLIST_1";
        doNothing().when(playListServiceMock).deletePlaylist(userId, playListId);

        //Act
        playlistCommand.execute(List.of("DELETE-PLAYLIST","1","MY_PLAYLIST_1"));

        //Assert
        Assertions.assertEquals(expectedOutput,outputStreamCaptor.toString().trim() );
        verify(playListServiceMock,times(1)).deletePlaylist(userId, playListId);
  }

  @AfterEach
  public void tearDown() {
      System.setOut(standardOut);
  }
    }
