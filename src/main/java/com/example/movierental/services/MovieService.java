package com.example.movierental.services;

import com.example.movierental.models.Movie;
import com.example.movierental.repository.MovieFileRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {
    private final MovieFileRepository movieRepository;
    private final Deque<Movie> recentlyWatchedStack = new ArrayDeque<>();

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
        recentlyWatchedStack.removeIf(m -> m.getId().equals(movie.getId()));
        recentlyWatchedStack.push(movie);
        if (recentlyWatchedStack.size() > 5) {
            recentlyWatchedStack.removeLast();
        }
    }

    public List<Movie> getRecentlyWatchedMovies() {
        return new ArrayList<>(recentlyWatchedStack);
    }
}