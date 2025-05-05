package com.example.movierental.models;

public class Owner extends AbstractAdmin {
    public Owner() {
        super();
    }

    public Owner(String email, String firstName, String lastName, String password, String age, String description) {
        super(email, firstName, lastName, password, age,description, "owner");
    }

    @Override
    public String toString() {
        return email + "," + password + "," + firstName + "," + lastName + "," + age + "," + description + ",N/A," + role;
    }

    public static Owner fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 6) return null;

        Owner owner = new Owner();
        owner.setEmail(parts[0]);
        owner.setPassword(parts[1]);
        owner.setFirstName(parts[2]);
        owner.setLastName(parts[3]);
        owner.setAge(parts[4]);
        owner.setDescription(parts[5]);
        owner.setRole(parts[7]);

        return owner;
    }
}
