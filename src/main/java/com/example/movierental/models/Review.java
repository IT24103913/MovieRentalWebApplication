package com.example.movierental.models;

public class Review {
    private String reviewerName;
    private int rating;
    private String comment;

    // Constructor
    public Review(String reviewerName, int rating, String comment) {
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters and Setters
    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // toString() method for writing the review to the file
    @Override
    public String toString() {
        return reviewerName + "," + rating + "," + comment;  // You can format it as you like
    }

    // fromString() method for parsing a review from the file
    public static Review fromString(String reviewString) {
        String[] parts = reviewString.split(",");
        if (parts.length >= 3) {
            String reviewerName = parts[0];
            int rating = Integer.parseInt(parts[1]);
            String comment = parts[2];
            return new Review(reviewerName, rating, comment);
        }
        return null;  // Return null if the line is malformed
    }
}
