package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.exceptions.PlayListNotFoundException;
import com.crio.jukebox.services.IPlayListService;


public class DeletePlayListCommand implements ICommand {

    IPlayListService playListService;

     public DeletePlayListCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }

    @Override
    public void execute(List<String> tokens) {
      
        // TODO Auto-generated method stub
        if(tokens.size()>=3){
        String userId=tokens.get(1);
        String playlistId=tokens.get(2);
        playListService.deletePlaylist(userId, playlistId);
        System.out.println("Delete Successful");
       
        }
        
    }
    
}
