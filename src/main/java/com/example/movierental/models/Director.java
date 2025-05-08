package com.example.movierental.models;

// Base class
public class Director {
    private int id;
    private String name;
    private String nationality;
    private int yearsOfExperience;
    private String dateOfBirth;
    private double salary;
    private String gender; // New field

    public Director() {}

    public Director(int id, String name, String nationality, int yearsOfExperience, String dateOfBirth, double salary, String gender) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.yearsOfExperience = yearsOfExperience;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString(){
        return id + "," + name  + "," + salary + "," + nationality + "," + yearsOfExperience + "," + dateOfBirth + "," + gender + "\n";
    }
}
