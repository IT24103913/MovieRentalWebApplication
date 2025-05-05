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

    public Review(int i, String part, String part1, int i1, String part2) {
    }

    // Validation in setter
    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1-5");
        }
        this.rating = rating;
    }

    // Getters
    public int getId() { return id; }
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

    public CharSequence getUserName() {
        return getUserName();
    }
}
