package com.example.movierental.models;

import java.time.LocalDate;

//Encapsulation
public class Review {
    private int id;
    private String movieTitle;
    private String reviewText;
    private int rating;
    private LocalDate date;
    private String userName;


    public Review() {
    }

    //Parameterized constructor
    public Review(int id, String movieTitle, String reviewText, int rating,LocalDate date, String userName) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.reviewText = reviewText;
        this.rating = rating;
        this.date = date;
        this.userName = userName;
    }

    public String getMovieTitle() {

        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {

        this.movieTitle = movieTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getReviewText() {

        return reviewText;
    }

    public void setReviewText(String reviewText) {

        this.reviewText = reviewText;
    }

    public int getRating() {

        return rating;
    }

    public void setRating(int rating) {

        this.rating = rating;
    }

    public LocalDate getDate() {

        return date;
    }

    public void setDate(LocalDate date) {

        this.date = date;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }
}

