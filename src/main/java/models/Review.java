package com.example.movierental.models;

public class Review {
    private String movieTitle;
    private String reviewText;
    private int rating;

    public Review() {
    }

    public Review(String movieTitle, String reviewText, int rating) {
        this.movieTitle = movieTitle;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    // Getters and Setters (Encapsulation)
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
