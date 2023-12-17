package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.entities.Question;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.QuestionNotFoundException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.repositories.QuestionRepository;
import com.crio.codingame.services.IContestService;
import com.crio.codingame.services.QuestionService;

public class CreateContestCommand implements ICommand{

    private final IContestService contestService;

    public CreateContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IContestService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_CONTEST","CRIODO2_CONTEST","LOW Monica","40"]
    // or
    // ["CREATE_CONTEST","CRIODO1_CONTEST","HIGH","Ross"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

  
    @Override
    public void execute(List<String> tokens) {
        if (tokens != null && tokens.size() >= 4) {
            String contestName = tokens.get(1);
            String levelStr = tokens.get(2);
            String contestCreator = tokens.get(3);
            Level level;
            Integer questions = null;
    
            try {
                level = Level.valueOf(levelStr);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid level provided.");
                return;
            }
    
            if (tokens.size() > 4) {
                try {
                    questions = Integer.parseInt(tokens.get(4));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format for questions.");
                    return;
                }
            }
    
            try {
                Contest result;
                if (questions != null) {
                    result = contestService.create(contestName, level, contestCreator, questions);
                } else {
                    result = contestService.create(contestName, level, contestCreator, null);
                }
                System.out.println(result);
            } catch (UserNotFoundException | QuestionNotFoundException e) {
                System.out.println(e.getMessage());
            } 
        } else {
            System.err.println("Not enough tokens or tokens list is null");
        }
    }
    
    }

    

