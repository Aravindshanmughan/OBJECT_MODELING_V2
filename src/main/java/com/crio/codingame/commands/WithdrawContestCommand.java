package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.dtos.UserRegistrationDto;
import com.crio.codingame.exceptions.ContestNotFoundException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.services.IUserService;

public class WithdrawContestCommand implements ICommand{

    private final IUserService userService;
    
    public WithdrawContestCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute withdrawContest method of IUserService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["WITHDRAW_CONTEST","3","Joey"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) {
    

        String contestId;
        String userName;
        try {
            if (tokens.size() >= 3) {
                contestId = tokens.get(1);
                userName = tokens.get(2);
            } else {
                throw new IllegalArgumentException("Insufficient tokens provided");
            }
    
            UserRegistrationDto result = userService.withdrawContest(contestId, userName);
            System.out.println(result);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Contest ID or User name not found in tokens");
        } catch (ContestNotFoundException | UserNotFoundException e) {
            System.out.println(e.getMessage()); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    }
    

