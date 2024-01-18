package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IPlayListService;

public class ModifyPalylistCommand implements ICommand{

    private final IPlayListService playListService;
   
    public ModifyPalylistCommand(IPlayListService playListService) {
        this.playListService = playListService;
      
    }




    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub

        String command=tokens.get(1);
        String userId=tokens.get(2);
        String playListId=tokens.get(3);
        List<String> songId=new ArrayList<String>();
        if(tokens.size()>=4){
            for (int i = 4; i < tokens.size(); i++) {
                songId.add(tokens.get(i));
        }

      
       Playlist playList=playListService.modifyPlayList(userId, playListId, songId, command);
       List<String> songIdlist=playList.getSongs().stream()
                            .filter(a->!a.getSongID().isEmpty())
                            .map(Song::getSongID)
                            .collect(Collectors.toList());
                            
                            System.out.println("Playlist ID - " + playListId +
                            "\nPlaylist Name - " + playList.getPlayListName() +
                            "\nSong IDs - " + String.join(" ", songIdlist));
                     
        
        
    
}
}
}
