package com.example.movierental.models;
public class Review {
    private int id;
    private String movieTitle;
    private String reviewText;
    private int rating;

    public Review() {
        this.id = id;
        this.movieTitle = movieTitle;
        this.reviewText = reviewText;
        setRating(rating);
    }

    public Review(int id, String movieTitle, String reviewText, int rating, String userName) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.reviewText = reviewText;
        this.rating = rating;
        this.userName = userName;


    }

    // Validation in setter
    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1-5");
        }
        this.rating = rating;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

}
