package com.example.movierental.repository;

import com.example.movierental.dataStructures.MyArray;
import com.example.movierental.models.Review;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;

@Repository
public class ReviewRepository {

    private static final String FILE_PATH = "utils/reviews.txt";

    // Helper method to read all reviews into an array
    public Review[] getAllReviews() {
        Review[] reviews = new Review[10]; // initial capacity
        int count = 0;

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
                    date = LocalDate.now();
                }
                String userName = reviewData[5];

                if (count == reviews.length) {
                    // Resize array (double size)
                    Review[] newReviews = new Review[reviews.length * 2];
                    for (int i = 0; i < reviews.length; i++) {
                        newReviews[i] = reviews[i];
                    }
                    reviews = newReviews;
                }

                reviews[count++] = new Review(id, movieTitle, reviewText, rating, date, userName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Shrink array to actual size
        Review[] result = new Review[count];
        for (int i = 0; i < count; i++) {
            result[i] = reviews[i];
        }
        return result;
    }

    private int generateNextId() {
        Review[] reviews = getAllReviews();
        int maxId = 0;
        for (int i = 0; i < reviews.length; i++) {
            if (reviews[i].getId() > maxId) {
                maxId = reviews[i].getId();
            }
        }
        return maxId + 1;
    }

    public void addReview(Review review) {
        review.setId(generateNextId());
        review.setDate(LocalDate.now());

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

    public void updateReview(Review updatedReview) {
        Review[] reviews = getAllReviews();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < reviews.length; i++) {
                Review review = reviews[i];
                if (review.getId() == updatedReview.getId()) {
                    review.setMovieTitle(updatedReview.getMovieTitle());
                    review.setReviewText(updatedReview.getReviewText());
                    review.setRating(updatedReview.getRating());
                    review.setDate(updatedReview.getDate());
                    review.setUserName(updatedReview.getUserName());
                }

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

    public void deleteReview(int id) {
        Review[] reviews = getAllReviews();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < reviews.length; i++) {
                Review review = reviews[i];
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
