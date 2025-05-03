package com.example.movierental.models;

public class Admin {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String age;
    private String status;
    private String role;

    public Admin() {
    }

    public Admin(String email, String firstName, String lastName, String password, String age) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.age = age;
        this.status = "pending";
        this.role = "admin";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    @Override
//    public String toString() {
//        return "Admin{" +
//                "email='" + email + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", password='" + password + '\'' +
//                ", age='" + age + '\'' +
//                ", status='" + status + '\'' +
//                ", role='" + role + '\'' +
//                '}';
//    }

    public static Admin fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 6) return null;

        Admin admin = new Admin();
        admin.setEmail(parts[0]);
        admin.setPassword(parts[1]);
        admin.setFirstName(parts[2]);
        admin.setLastName(parts[3]);
        admin.setAge(parts[4]);
        admin.setStatus(parts[5]);
        admin.setRole(parts[6]);

        return admin;
    }

    @Override
    public String toString() {
        return email + "," + password + "," + firstName + "," + lastName + "," + age + "," + status + "," + role;
    }

}
