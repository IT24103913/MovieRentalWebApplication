package com.example.movierental.repository;

import com.example.movierental.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private static final String DB_FILE = "utils/users.txt";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserRepository() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        try {
            Path path = Paths.get(DB_FILE);
            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        try {
            return Files.readAllLines(Paths.get(DB_FILE))
                    .stream()
                    .map(line -> {
                        try {
                            return objectMapper.readValue(line, User.class);
                        } catch (IOException e) {
                            return null;
                        }
                    })
                    .filter(user -> user != null)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public User save(User user) {
        List<User> users = findAll();
        users.removeIf(u -> u.getEmail().equalsIgnoreCase(user.getEmail()));
        users.add(user);
        writeToFile(users);
        return user;
    }

    public void deleteByEmail(String email) {
        List<User> users = findAll();
        users.removeIf(user -> user.getEmail().equalsIgnoreCase(email));
        writeToFile(users);
    }

    public Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    private void writeToFile(List<User> users) {
        try {
            Files.write(Paths.get(DB_FILE),
                    users.stream()
                            .map(user -> {
                                try {
                                    return objectMapper.writeValueAsString(user);
                                } catch (IOException e) {
                                    return "";
                                }
                            })
                            .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
