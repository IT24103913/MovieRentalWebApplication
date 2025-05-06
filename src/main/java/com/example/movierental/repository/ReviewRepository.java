package com.example.movierental.repository;

import com.example.movierental.models.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    private static final String FILE_PATH = "utils/reviews.txt";

    // Method to get all reviews from the .txt file
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] reviewData = line.split(",");
                int id = Integer.parseInt(reviewData[0]);
                String movieTitle = reviewData[1];
                String reviewText = reviewData[2];
                int rating = Integer.parseInt(reviewData[3]);
                reviews.add(new Review(id, movieTitle, reviewText, rating));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    // Method to add a review to the .txt file
    public void addReview(Review review) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(review.getId() + "," + review.getMovieTitle() + "," + review.getReviewText() + "," + review.getRating());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to update a review
    public void updateReview(Review updatedReview) {
        List<Review> reviews = getAllReviews();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Review review : reviews) {
                if (review.getId() == updatedReview.getId()) {
                    review = updatedReview; // Update the review
                }
                bw.write(review.getId() + "," + review.getMovieTitle() + "," + review.getReviewText() + "," + review.getRating());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a review
    public void deleteReview(int id) {
        List<Review> reviews = getAllReviews();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Review review : reviews) {
                if (review.getId() != id) {
                    bw.write(review.getId() + "," + review.getMovieTitle() + "," + review.getReviewText() + "," + review.getRating());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}