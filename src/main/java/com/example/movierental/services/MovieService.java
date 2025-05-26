package com.example.movierental.services;

import com.example.movierental.models.Movie;
import com.example.movierental.repository.MovieFileRepository;
import com.example.movierental.utils.MovieStack;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    private final MovieFileRepository movieRepository;
    private final MovieStack recentlyWatchedStack = new MovieStack();

    public MovieService(MovieFileRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    public Movie getMovieById(long id) {
        Movie movie = movieRepository.getMovieById(id);
        if (movie != null) addToRecentlyWatched(movie);
        return movie;
    }

    public void updateMovie(Movie updatedMovie) {
        movieRepository.updateMovie(updatedMovie);
    }

    public void deleteMovie(long id) {
        movieRepository.deleteMovie(id);
    }

    public void addToRecentlyWatched(Movie movie) {
        recentlyWatchedStack.push(movie);
    }

    public List<Movie> getRecentlyWatchedMovies() {
        Movie[] array = recentlyWatchedStack.getAll();
        List<Movie> result = new ArrayList<>();
        for (Movie m : array) {
            result.add(m);
        }
        return result;
    }
}