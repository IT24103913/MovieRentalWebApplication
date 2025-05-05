package com.example.movierental.models;

// models/User.java


import jakarta.validation.constraints.*; // Correct import for Spring Boot 3+
import lombok.Data;

@Data
public class User {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Min(value = 13, message = "Age must be at least 13")
    @Max(value = 100, message = "Age cannot exceed 100")
    private int age;
}
