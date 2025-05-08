package com.example.movierental.repository;

import com.example.movierental.models.Director;

import java.io.*;
import java.util.*;

public class DirectorRepository {
    private static final String FILE_PATH = "directors.txt";
    private static int directorID;

    public void saveDirector(Director director) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
        writer.write(String.format("%d,%s,%s,%d,%s,%.2f,%s\n",
                director.getId(),
                director.getName(),
                director.getNationality(),
                director.getYearsOfExperience(),
                director.getDateOfBirth(),
                director.getSalary(),
                director.getGender()));
        writer.close();
    }

    public List<Director> getAllDirectors() throws IOException {
        List<Director> directors = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length < 7) continue; // skip malformed lines
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String nationality = parts[2];
            int experience = Integer.parseInt(parts[3]);
            String dob = parts[4];
            double salary = Double.parseDouble(parts[5]);
            String gender = parts[6];
            directorID = id;
            Director director = new Director(id, name, nationality, experience, dob, salary, gender);
            directors.add(director);
        }
        reader.close();
        return directors;
    }

    public int getNextDirectorID(){
        return ++directorID;
    }

    public void updateDirectors(List<Director> directors) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Director director : directors) {
            writer.write(String.format("%d,%s,%s,%d,%s,%.2f,%s\n",
                    director.getId(),
                    director.getName(),
                    director.getNationality(),
                    director.getYearsOfExperience(),
                    director.getDateOfBirth(),
                    director.getSalary(),
                    director.getGender()));
        }
        writer.close();
    }

    public void deleteDirector(int directorId) throws IOException {
        List<Director> directors = getAllDirectors();
        directors.removeIf(d -> d.getId() == directorId);
        updateDirectors(directors);
    }
}
