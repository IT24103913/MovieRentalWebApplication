package com.example.movierental.services;

import com.example.movierental.models.Movie;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieReadingService {

    public List<Movie> readMoviesFromFile() {
        List<Movie> movies = new ArrayList<>();

        try (InputStream inputStream = new ClassPathResource("movies.txt").getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    Movie movie = new Movie();
                    movie.setId(Long.parseLong(parts[0].trim()));
                    movie.setTitle(parts[1].trim());
                    movie.setGenre(parts[2].trim());
                    movie.setYear(Integer.parseInt(parts[3].trim()));
                    if (parts.length > 4) {
                        movie.setPictureUrl(parts[4].trim());
                    }
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }
}