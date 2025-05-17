package com.example.movierental.services;

import com.example.movierental.models.Movie;
import com.example.movierental.repository.MovieFileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService {

    private final MovieFileRepository movieRepository;

    public MovieService(MovieFileRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Get all movies
    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    // Add a new movie
    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    // Get a movie by ID
    public Movie getMovieById(long id) {
        return movieRepository.getMovieById(id);
    }

    // Update a movie
    public void updateMovie(Movie updatedMovie) {
        movieRepository.updateMovie(updatedMovie);
    }

    // Delete a movie
    public void deleteMovie(long id) {
        movieRepository.deleteMovie(id);
    }
}