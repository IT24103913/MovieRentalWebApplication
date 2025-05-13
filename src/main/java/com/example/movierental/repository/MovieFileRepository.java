package com.example.movierental.repository;

import com.example.movierental.models.Movie;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List ;

@Repository
public class MovieFileRepository {
    private static final String FILE_PATH = "utils/movies.txt";

    // Get all movies
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                long id = Long.parseLong(data[0]);
                String title = data[1];
                String genre = data[2];
                int year = Integer.parseInt(data[3]);
                double price = Double.parseDouble(data[4]);

                movies.add(new Movie(id, title, genre, year, price));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    // Add a movie
    public void addMovie(Movie movie) {
        List<Movie> movies = getAllMovies(); // Get all the movies already in the file
        long nextId = movies.stream().mapToLong(Movie::getId).max().orElse(0L) + 1; // Generate next ID

        // Set the generated ID to the new movie
        movie.setId(nextId);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(movie.getId() + "," + movie.getTitle() + "," + movie.getGenre() + "," + movie.getYear() + "," + movie.getRentalPrice());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update a movie
    public void updateMovie(Movie updatedMovie) {
        List<Movie> movies = getAllMovies();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Movie movie : movies) {
                if (movie.getId() == updatedMovie.getId()) {
                    movie = updatedMovie; // Replace old with new
                }
                bw.write(movie.getId() + "," + movie.getTitle() + "," + movie.getGenre() + "," + movie.getYear() + "," + movie.getRentalPrice());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Delete a movie
    public void deleteMovie(long id) {
        List<Movie> movies = getAllMovies();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Movie movie : movies) {
                if (movie.getId() != id) {
                    bw.write(movie.getId() + "," + movie.getTitle() + "," + movie.getGenre() + "," + movie.getYear() + "," + movie.getRentalPrice());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get a movie by ID
    public Movie getMovieById(long id) {
        List<Movie> movies = getAllMovies();
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }
}