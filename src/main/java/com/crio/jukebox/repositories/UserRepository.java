package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository {

    private final Map<String,User> userMap;
    private Integer autoIncrement = 0;

    

	public UserRepository() {
        userMap = new HashMap<String,User>();
    }

    public UserRepository(Map<String, User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    @Override
    public User save(User entity) {
        // TODO Auto-generated method stub
        if(entity.getUserID()==null){

            autoIncrement++;
            User u =new User(Integer.toString(autoIncrement), entity.getUserName());
            userMap.put(u.getUserID(), u);
            return u;
        }
        userMap.put(entity.getUserID(), entity);
        return entity;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return userMap.values().stream()
                   .collect(Collectors.toList());  
    }

    @Override
    public Optional<User> findbyId(String id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void count() {
        // TODO Auto-generated method stub
        
    }

   

    @Override
    public Optional<User> findUser(String id) {
        // TODO Auto-generated method stub
        return userMap.values().stream()
                       .filter(e->e.getUserID().equals(id))
                       .findFirst();      
                       
    }

   

   

   

   
    
}
