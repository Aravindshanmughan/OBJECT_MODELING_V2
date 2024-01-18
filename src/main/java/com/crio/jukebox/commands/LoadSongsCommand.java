package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.ISongService;

public class LoadSongsCommand implements ICommand {


    private final ISongService songservice;

    public LoadSongsCommand(ISongService songservice) {
        this.songservice = songservice;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        if(tokens.size()>=2){
        String file=tokens.get(1);
        songservice.loadSongsFromCSV(file);
        System.out.println("Songs Loaded successfully");
         }

    }
}

