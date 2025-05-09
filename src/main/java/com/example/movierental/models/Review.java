package com.example.movierental.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class Review {
    @Setter
    private int id;
    @Setter
    private String movieTitle;
    @Setter
    private String reviewText;
    @Setter
    private int rating;
    private LocalDate date;
    private String userName;

    public Review(int id, String movieTitle, String reviewText, int rating) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.reviewText = reviewText;
        this.rating = rating;
        this.date = date;
        this.userName = userName;
    }

    public Review() {
    }

    public Review(String reviewerName, String reviewText, LocalDate datePosted) {

    }

    public int getId() {
        return id;
    }

    public void displayReview() {
        System.out.println(getUserName() + " says: " + getReviewText());
    }


    public class PublicReview extends Review {
        // Constructor
        public PublicReview(String reviewerName, String reviewText, LocalDate datePosted) {
            super(reviewerName, reviewText, datePosted);  // Inherit properties from Review
        }

        // Optionally, you can override displayReview() if you want to add any extra features
        // But for now, we'll leave it as it is to use the inherited behavior.
    }
}

