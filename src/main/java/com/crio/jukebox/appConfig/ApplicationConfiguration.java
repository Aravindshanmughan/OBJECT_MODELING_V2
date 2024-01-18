package com.crio.jukebox.appConfig;


import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlayListCommand;
import com.crio.jukebox.commands.LoadSongsCommand;
import com.crio.jukebox.commands.ModifyPalylistCommand;
import com.crio.jukebox.commands.PlayPlayListCommand;
import com.crio.jukebox.commands.PlaySongsCommand;
import com.crio.jukebox.repositories.IPlayListRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.IPlayListService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.PlayListService;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.UserService;

public class ApplicationConfiguration {

    private final ISongRepository songRepository=new SongRepository();
    private final IPlayListRepository playListRepository=new PlaylistRepository();
    private final IUserRepository userRepository=new UserRepository();
    
    private final ISongService songService=new SongService(userRepository, songRepository,playListRepository);
    private final IPlayListService playListService=new PlayListService(playListRepository, songRepository, userRepository);
    private final IUserService userService=new UserService(userRepository);

    private final CreatePlaylistCommand playlistCommand=new CreatePlaylistCommand(playListService);
    private final CreateUserCommand userCommand=new CreateUserCommand(userService);
    private final LoadSongsCommand loadSongs=new LoadSongsCommand(songService);
    private final DeletePlayListCommand deletePlayListCommand=new DeletePlayListCommand(playListService);
    private final PlayPlayListCommand playPlayListCommand=new PlayPlayListCommand(playListService);
    private final ModifyPalylistCommand modifyPalylistCommand=new ModifyPalylistCommand(playListService);
    private final PlaySongsCommand playSongsCommand=new PlaySongsCommand(playListService);

    private final CommandInvoker commandInvoker=new CommandInvoker();


    public CommandInvoker getCommandInvoker(){

        commandInvoker.register("CREATE-USER", userCommand);
        commandInvoker.register("CREATE-PLAYLIST", playlistCommand);
        commandInvoker.register("LOAD-DATA", loadSongs);
        commandInvoker.register("DELETE-PLAYLIST", deletePlayListCommand);
        commandInvoker.register("PLAY-PLAYLIST", playPlayListCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPalylistCommand);
        commandInvoker.register("PLAY-SONG", playSongsCommand);
        
        
        return commandInvoker;
    }


    

        
    
    
}
