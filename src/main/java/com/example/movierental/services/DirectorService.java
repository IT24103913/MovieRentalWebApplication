package com.example.movierental.services;

import com.example.movierental.models.Director;
import com.example.movierental.repository.DirectorRepository;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DirectorService {
    private final DirectorRepository repository = new DirectorRepository();

    public int getNextID(){
        return repository.getNextDirectorID();
    }

    public void addDirector(Director director) throws IOException {
        repository.saveDirector(director);
    }

    public List<Director> getAllDirectors() throws IOException {
        return repository.getAllDirectors();
    }

    public Director getDirectorById(int id) throws IOException {
        return repository.getAllDirectors().stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateDirector(Director updated) throws IOException {

        for (Director director : getAllDirectors()) {
            System.out.println(director.toString());
        }

        List<Director> directors = repository.getAllDirectors();
        for (int i = 0; i < directors.size(); i++) {
            if (directors.get(i).getId() == updated.getId()) {
                directors.set(i, updated);
                break;
            }
        }

        for (Director director : getAllDirectors()) {
            System.out.println(director.toString());
        }

        repository.updateDirectors(directors);
    }

    public void deleteDirector(int id) throws IOException {
        repository.deleteDirector(id);
    }
}
