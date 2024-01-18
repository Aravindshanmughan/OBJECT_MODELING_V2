package com.crio.jukebox.CommandsTest;

import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import com.crio.jukebox.commands.PlaySongsCommand;
import com.crio.jukebox.entities.Song;
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
public class PlaySongCommandTest {
 




    private final PrintStream standardOut=System.out;
    private final ByteArrayOutputStream outputStreamCaptor=new ByteArrayOutputStream();

    @Mock
    IPlayListService playListServiceMock;
    @InjectMocks
    PlaySongsCommand playSongsCommand;
    

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("PlaySongTest")
    public void playSongTest() {
    
        //Arrange
        String expectedOutput="Current Song PlayingSong{songId='1', songName='songName', album='album', genre='genre', mainArtist='mainArtist', otherArtist=[]}";
        // User user=new User("1", "Kiran"); 
        // Playlist playlist=new Playlist("1", "playListName", songs, user);
        String playListId="1";
        String songId="1";
        Song song=new Song("1", "songName", "album", "genre", "mainArtist", Collections.emptyList());
        when(playListServiceMock.playSong(playListId, songId)).thenReturn(song);

        //Act
        playSongsCommand.execute(List.of("PLAY-SONG","1","1"));

        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
     
     
}
