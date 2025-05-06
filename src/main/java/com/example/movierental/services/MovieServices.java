package com.example.movierental.services;

import com.example.movierental.models.Movie;
import com.example.movierental.repository.MovieFileRepository;
import com.example.movierental.utils.BubbleSortUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServices {
    private final MovieFileRepository movieRepository;

    public MovieServices(MovieFileRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Create
    public void saveMovie(Movie movie) {
        List<Movie> movies = movieRepository.findAll();
        // Generate ID if not provided
        if (movie.getId() == null) {
            Long newId = movies.isEmpty() ? 1L : movies.get(movies.size() - 1).getId() + 1;
            movie.setId(newId);
        }
        movies.add(movie);
        movieRepository.saveAll(movies);
    }

    // Read (all)
    public List<Movie> findAllMovies(String sortBy) {
        List<Movie> movies = movieRepository.findAll();
        if (sortBy != null && !sortBy.isEmpty()) {
            BubbleSortUtil.bubbleSort(movies, sortBy);
        }
        return movies;
    }

    // Read (by ID)
    public Movie findMovieById(Long id) {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Update
    public void updateMovie(Movie updatedMovie) {
        List<Movie> movies = movieRepository.findAll();
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId().equals(updatedMovie.getId())) {
                movies.set(i, updatedMovie);
                break;
            }
        }
        movieRepository.saveAll(movies);
    }

    // Delete
    public void deleteMovie(Long id) {
        List<Movie> movies = movieRepository.findAll();
        movies.removeIf(movie -> movie.getId().equals(id));
        movieRepository.saveAll(movies);
    }
}