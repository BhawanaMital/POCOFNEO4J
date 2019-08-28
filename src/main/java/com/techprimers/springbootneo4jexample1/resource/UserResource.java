package com.techprimers.springbootneo4jexample1.resource;

import com.techprimers.springbootneo4jexample1.model.Movie;
import com.techprimers.springbootneo4jexample1.model.User;
import com.techprimers.springbootneo4jexample1.repository.MovieRepository;
import com.techprimers.springbootneo4jexample1.repository.UserRepository;
import com.techprimers.springbootneo4jexample1.service.MovieService;
import com.techprimers.springbootneo4jexample1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/rest/neo4j")
public class UserResource {


    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieService movieService;

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("Users")
    public Collection<User> getAllUsers() { return userService.getAll(); }

    @GetMapping("Movies")
    public Collection<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @GetMapping("Relations")
    public Collection<User> getAllMaps() {
        return userService.getAllRelations();
    }

    @PostMapping("/{name1}/{name2}")
    public ResponseEntity<Movie> createRelation(@PathVariable("name1")String name1,@PathVariable("name2") String name2){
        ResponseEntity responseEntity;
        System.out.println(name1+" "+name2);
        movieService.createRelation(name1,name2);
        responseEntity=new ResponseEntity("Succesfully Created", HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("user")
    public ResponseEntity<User> saveUsers(@RequestBody User user){

        ResponseEntity responseEntity;
        userService.saveUser(user);
        responseEntity=new ResponseEntity("Succesfully Created", HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("movie")
    public ResponseEntity<User> saveMovies(@RequestBody Movie movie){

        ResponseEntity responseEntity;
        movieService.saveMovie(movie);
        responseEntity=new ResponseEntity("Succesfully Created", HttpStatus.CREATED);
        return responseEntity;
    }

//    @GetMapping("RelationCreation/{name}/{title}")
//    public ResponseEntity<User> saveRelation(@PathVariable("id1") String id1,@PathVariable("id2") String id2){
//        System.out.println("Inside the UserResource : saveRelation() "+ id1+"  "+id2);
//        ResponseEntity responseEntity;
////        User user=userRepository.findById(Long.valueOf(id1));
////        Movie movie=movieRepository.findById(Long.valueOf(id2));
////        userService.createRelation(user,movie);
//      userService.createRelation(id1,id2));
//        responseEntity=new ResponseEntity("Succesfully Created", HttpStatus.CREATED);
//        return responseEntity;
//    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable String id)
    {
        userService.delete(Long.valueOf(id));
        movieService.delete(Long.valueOf(id));
        return "redirect:/user/list";
    }

//    @DeleteMapping("delete/{name},{title}")
//    public String deleteRelationship(@PathVariable String name,@PathVariable String title)
//    {
//        userService.deleteRelations(name,title);
//        return "redirect:/user/list";
//    }

    @PutMapping(value = "update/movie")
    public ResponseEntity<?> updateMovies(@RequestBody Movie movie){

        ResponseEntity responseEntity;
        movieService.updateMovie(movie);
        responseEntity=new ResponseEntity("Succesfully Updated", HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping(value = "update/user")
    public ResponseEntity<?> updateUsers(@RequestBody User user){

        ResponseEntity responseEntity;
        userService.updateUser(user);
        responseEntity=new ResponseEntity("Succesfully Updated user", HttpStatus.CREATED);
        return responseEntity;
    }
}
