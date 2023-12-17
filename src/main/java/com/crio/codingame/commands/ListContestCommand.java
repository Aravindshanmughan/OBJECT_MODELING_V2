package com.crio.codingame.commands;

import java.util.List;
import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class ListContestCommand implements ICommand{

    private final IContestService contestService;
    
    public ListContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllContestLevelWise method of IContestService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_CONTEST","HIGH"]
    // or
    // ["LIST_CONTEST"]

    @Override
    public void execute(List<String> tokens) {
        Level level = null;
        if (tokens.size() > 1) {
            String token = tokens.get(1);
            try {
                level = Level.valueOf(token);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid level specified!");
            }
        }
    
        if (contestService != null) {
            List<Contest> contests;
            if (level != null) {
                contests = contestService.getAllContestLevelWise(level);
            } else {
                contests = contestService.getAllContestLevelWise(null);
            }
    
            // Print contests
            System.out.println(contests);
        } else {
            System.out.println("Contest service not available!");
        }
    }
    
    
}
