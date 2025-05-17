package com.example.movierental.repository;

import com.example.movierental.models.Movie;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieFileRepository {
    private static final String FILE_PATH = "utils/movies.txt";

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
                String pictureUrl = data.length > 5 ? data[5] : "";

                movies.add(new Movie(id, title, genre, year, price, pictureUrl));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public void addMovie(Movie movie) {
        List<Movie> movies = getAllMovies();
        long nextId = movies.stream().mapToLong(Movie::getId).max().orElse(0L) + 1;
        movie.setId(nextId);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(movie.getId() + "," + movie.getTitle() + "," + movie.getGenre() + "," +
                    movie.getYear() + "," + movie.getRentalPrice() + "," + movie.getPictureUrl());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMovie(Movie updatedMovie) {
        List<Movie> movies = getAllMovies();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Movie movie : movies) {
                if (movie.getId() == updatedMovie.getId()) {
                    movie = updatedMovie;
                }
                bw.write(movie.getId() + "," + movie.getTitle() + "," + movie.getGenre() + "," +
                        movie.getYear() + "," + movie.getRentalPrice() + "," + movie.getPictureUrl());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteMovie(long id) {
        List<Movie> movies = getAllMovies();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Movie movie : movies) {
                if (movie.getId() != id) {
                    bw.write(movie.getId() + "," + movie.getTitle() + "," + movie.getGenre() + "," +
                            movie.getYear() + "," + movie.getRentalPrice() + "," + movie.getPictureUrl());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Movie getMovieById(long id) {
        return getAllMovies().stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }
}