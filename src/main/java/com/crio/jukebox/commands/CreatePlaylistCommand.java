package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlayListService;

public class CreatePlaylistCommand implements ICommand {

    private final IPlayListService playListService;


    public CreatePlaylistCommand(IPlayListService playListService) {

        this.playListService=playListService;
    }


    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        if(tokens.size()>=4){
        String id=tokens.get(1);
        String playListName=tokens.get(2);
        List<String> songId = tokens.subList(3, tokens.size());
        Playlist playlist= playListService.createPlaylist( playListName, songId, id);
        System.out.println(playlist);
      }
      
       
}

    
    
}
