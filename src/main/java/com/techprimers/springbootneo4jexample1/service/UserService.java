package com.techprimers.springbootneo4jexample1.service;

import com.techprimers.springbootneo4jexample1.model.Movie;
import com.techprimers.springbootneo4jexample1.model.User;
import com.techprimers.springbootneo4jexample1.repository.MovieRepository;
import com.techprimers.springbootneo4jexample1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    public Collection<User> getAll() {
        return userRepository.getAllUsers();
    }

    public Collection<User> getAllRelations() {
        return userRepository.getAllMaps();
    }

    public User saveUser(User user) {

        User savedUser = userRepository.saveUser(user.getId(),user.getName(),user.getAge());
        return savedUser;
    }

    public void delete(Long id) {

        userRepository.delete(id);
    }

    public boolean updateUser(User user) {

        User savedUser;
        if (userRepository.findByName(user.getName()).equals(user)) {

            savedUser = userRepository.save(user);
            return true;
        }

        return false;
    }

    public void createRelation(String id1,Long id2) {
      System.out.println("Inside the UserService : createRelation() "+ id1+"  "+id2);
      User user=userRepository.findByName(id1);
        //System.out.println(user);
     Movie movie=movieRepository.getById(id2);
       userRepository.createRelationships(user,movie,user.getName(),movie.getTitle());
    }


//    public void deleteRelations(String name, String title) {
//
//    }

//    public void createRelation(String name, String title) {
//
//        Iterator it =(userRepository.findByName(name)).iterator();
//
//        // Display element by element using Iterator
//        while (it.hasNext())
//            it.set
//
//    }
}
