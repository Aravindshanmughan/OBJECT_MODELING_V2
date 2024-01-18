package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IPlayListService;


public class PlaySongsCommand implements ICommand {

    private final IPlayListService playListService;
    

   


    public PlaySongsCommand(IPlayListService playListService) {
        this.playListService = playListService;
    }





    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub

        if(tokens.size()>=2 && tokens!=null){
            
            String playlistId=tokens.get(1);
            String songId=tokens.get(2);
            Song song=playListService.playSong(playlistId, songId);
            if(song!=null){
            System.out.println("Current Song Playing" + song);
            }else System.out.println("Given song id is not a part of the active playlist");
        
       }
    
   }
}
