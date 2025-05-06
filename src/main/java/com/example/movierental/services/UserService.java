package com.example.movierental.services;

// services/UserService.java

import com.example.movierental.models.User;
import com.example.movierental.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUpUser(User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already registered");
        }
        return userRepository.save(user);
    }

    public User signInUser(String email, String password) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new Exception("Invalid credentials");
        }
        return user.get();
    }

    // In UserService.java
    public User updateUser(User updatedUser) throws Exception {
        User existingUser = userRepository.findByEmail(updatedUser.getEmail())
                .orElseThrow(() -> new Exception("User not found"));

        // Only update password if it was changed (not dots)
        if(!updatedUser.getPassword().equals("••••••••") &&
                !updatedUser.getPassword().equals(existingUser.getPassword())) {
            existingUser.setPassword(updatedUser.getPassword());
        }

        // Update other fields
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setAge(updatedUser.getAge());

        return userRepository.save(existingUser);
    }

    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}

