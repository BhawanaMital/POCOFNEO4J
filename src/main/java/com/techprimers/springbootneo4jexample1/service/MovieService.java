package com.techprimers.springbootneo4jexample1.service;

import com.techprimers.springbootneo4jexample1.model.Movie;
import com.techprimers.springbootneo4jexample1.model.User;
import com.techprimers.springbootneo4jexample1.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Collection<Movie> getAll() {
        return movieRepository.getAllMovies();
    }

    public void delete(Long id) {

        movieRepository.delete(id);
    }

    public boolean saveMovie(Movie movie) {

        Movie savedMovie = movieRepository.save(movie);
        return true;
    }

    public boolean updateMovie(Movie movie) {

        Movie savedMovie;
        if (movieRepository.findByTitle(movie.getTitle()).isEmpty()) {

            savedMovie = movieRepository.save(movie);
            return true;
         }

        return false;
    }


}

