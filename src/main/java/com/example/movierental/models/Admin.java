package com.example.movierental.models;

public class Admin extends AbstractAdmin {
    private String status;

    public Admin() {
        super();
    }

    public Admin(String email, String firstName, String lastName, String password, String age,String description) {
        super(email, firstName, lastName, password, age,description, "admin");
        this.status = "pending";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Admin fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 6) return null;

        Admin admin = new Admin();
        admin.setEmail(parts[0]);
        admin.setPassword(parts[1]);
        admin.setFirstName(parts[2]);
        admin.setLastName(parts[3]);
        admin.setAge(parts[4]);
        admin.setDescription(parts[5]);
        admin.setStatus(parts[6]);
        admin.setRole(parts[7]);

        return admin;
    }

    @Override
    public String toString() {
        return email + "," + password + "," + firstName + "," + lastName + "," + age + "," + description+ "," + status + "," + role;
    }

}
