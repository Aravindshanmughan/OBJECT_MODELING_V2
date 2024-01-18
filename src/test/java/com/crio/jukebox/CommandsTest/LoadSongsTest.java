package com.crio.jukebox.CommandsTest;

import static org.mockito.Mockito.doNothing;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import com.crio.jukebox.commands.LoadSongsCommand;
import com.crio.jukebox.services.ISongService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("CreateUserCommandTest")
@ExtendWith(MockitoExtension.class)
public class LoadSongsTest {

    private final PrintStream standardOut=System.out;
    private final ByteArrayOutputStream outputStreamCaptor=new ByteArrayOutputStream();

    @Mock
    ISongService songServiceMock;
    @InjectMocks
    LoadSongsCommand loadSongsCommand;

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of LoadSongsCommand to print Songs Loaded successfully")
    public void testloadsongs() {
    
        //Arrange
        String expectedOutput="Songs Loaded successfully";
        doNothing().when(songServiceMock).loadSongsFromCSV("songs.csv");

        //Act
        loadSongsCommand.execute(List.of("LOAD-DATA","songs.csv"));

        //Assert
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    
}
