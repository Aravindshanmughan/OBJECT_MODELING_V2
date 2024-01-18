package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IUserRepository;

public class UserService implements IUserService {

  
IUserRepository userRepository;

  
public UserService(IUserRepository userRepository) {
    this.userRepository = userRepository;
}

    @Override
    public User create(String name) {
        // TODO Auto-generated method stub
        User newUser = new User( name);
        
        User user=userRepository.save(newUser);
        return user;
    }
    
}
