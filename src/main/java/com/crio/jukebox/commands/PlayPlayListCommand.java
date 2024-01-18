package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlayListService;
import com.crio.jukebox.entities.Song;

public class PlayPlayListCommand implements ICommand {

  private final IPlayListService playListService;
    


    public PlayPlayListCommand(IPlayListService playListService) {
    this.playListService = playListService;
}



    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        if(tokens.size()>=3){
        String playListId=tokens.get(1);
        String songId=tokens.get(2);
        Song song=playListService.playPlayList(playListId, songId);
        System.out.println("Current Song Playing" + song);

        
    }
    
}
}
