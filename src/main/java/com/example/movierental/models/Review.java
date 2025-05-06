package com.example.movierental.models;

public class Review {
    private int id;
    private String movieTitle;
    private String reviewText;
    private int rating;

    public Review(int id, String movieTitle, String reviewText, int rating) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public Review() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
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
}