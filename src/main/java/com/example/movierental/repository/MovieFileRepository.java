package com.example.movierental.repository;

import com.example.movierental.models.Movie;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List ;

@Repository
public class MovieFileRepository {
    private final String filePath = "src/main/data/movies.dat";

    @SuppressWarnings("unchecked")
    public List<Movie> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Movie>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>() ;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading movies from file", e);
        }
    }

    public void saveAll(List<Movie> movies) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(movies);
        } catch (IOException e) {
            throw new RuntimeException("Error writing movies to file", e);
        }
    }
}