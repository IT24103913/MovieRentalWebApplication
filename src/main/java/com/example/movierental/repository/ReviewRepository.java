package com.example.movierental.repository;

import com.example.movierental.models.Review;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
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
                String dateString = reviewData[4];
                LocalDate date = null;
                if (dateString != null && !dateString.equals("null") && !dateString.isEmpty()) {
                    date = LocalDate.parse(dateString);
                } else {
                    // Set default date, e.g., today or null
                    date = LocalDate.now();  // or null if you allow nullable
                }
                String userName = reviewData[5];

                reviews.add(new Review(id, movieTitle, reviewText, rating, date, userName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    private int generateNextId() {
        List<Review> reviews = getAllReviews();
        int maxId = 0;
        for (Review review : reviews) {
            if (review.getId() > maxId) {
                maxId = review.getId();
            }
        }
        return maxId + 1;
    }

    // Method to add a review to the .txt file
    public void addReview(Review review) {
        review.setId(generateNextId());    // Auto-generate ID
        review.setDate(LocalDate.now());   // Set the current date if not set

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(review.getId() + ","
                    + review.getMovieTitle() + ","
                    + review.getReviewText() + ","
                    + review.getRating() + ","
                    + review.getDate() + ","
                    + review.getUserName());
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
                // Update the review that matches the ID
                if (review.getId() == updatedReview.getId()) {
                    // Directly update the fields
                    review.setMovieTitle(updatedReview.getMovieTitle());
                    review.setReviewText(updatedReview.getReviewText());
                    review.setRating(updatedReview.getRating());
                    review.setDate(updatedReview.getDate());
                    review.setUserName(updatedReview.getUserName());
                }

                // Write the review (updated or not) back to the file
                bw.write(review.getId() + ","
                        + review.getMovieTitle() + ","
                        + review.getReviewText() + ","
                        + review.getRating() + ","
                        + review.getDate() + ","
                        + review.getUserName());
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
                    bw.write(review.getId() + ","
                            + review.getMovieTitle() + ","
                            + review.getReviewText() + ","
                            + review.getRating() + ","
                            + review.getDate() + ","
                            + review.getUserName());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}