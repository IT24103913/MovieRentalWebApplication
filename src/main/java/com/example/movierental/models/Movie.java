package com.example.movierental.models;

import java.io.Serializable;

public class Movie {
    private Long id;
    private String title;
    private String genre;
    private int year;
    private double rentalPrice;

    // Constructor
    public Movie() {
    }

    public Movie (Long id, String title, String genre, int year, double rentalPrice) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rentalPrice = rentalPrice;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre ;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

}