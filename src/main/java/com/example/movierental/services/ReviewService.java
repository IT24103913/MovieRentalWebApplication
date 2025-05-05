package com.example.movierental.services;

import com.example.movierental.models.Review;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

 class ReviewFileWriter {

    private static final String FILE_PATH = "utils/reviews.txt";

    public static void saveReview(String movieTitle, String userName, String comment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("Movie: " + movieTitle + ", User: " + userName + ", Review: " + comment);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

 @Service
public class ReviewService {
     private static final String FILE_PATH = "utils\\reviews.txt";
     private static final String DELIMITER = ";;";

     public void saveReviewToFile(String movieTitle, String userName, String comment) {
         ReviewFileWriter.saveReview(movieTitle, userName, comment);
     }

     public void createReview(Review review) {
     }

     public Object getAllReviews() {
         return null;
     }

     public Object updateReview(Review updatedReview) {
         return null;
     }

     public void deleteReview(int id) {
     }

     // Modified to include username in the review model
    public static class EnhancedReview extends Review {
        private String userName;

        public EnhancedReview(int id, String movieTitle, String reviewText, int rating, String userName) {
            super();
            this.userName = userName;
        }

        public String getUserName() { return userName; }
    }

    // Unified save method
    public void createReviewWithUser(String movieTitle, String userName, String reviewText, int rating) throws IOException {
        List<EnhancedReview> reviews = getAllEnhancedReviews();
        int newId = reviews.stream().mapToInt(EnhancedReview::getId).max().orElse(0) + 1;
        EnhancedReview review = new EnhancedReview(newId, movieTitle, reviewText, rating, userName);
        reviews.add(review);
        saveAllReviews(reviews);
    }

    // Unified read method
    private List<EnhancedReview> getAllEnhancedReviews() throws IOException {
        List<EnhancedReview> reviews = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) file.createNewFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length == 5) {
                    reviews.add(new EnhancedReview(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            parts[2],
                            Integer.parseInt(parts[3]),
                            parts[4]
                    ));
                }
            }
        }
        return reviews;
    }

    // Unified save method
    private void saveAllReviews(List<EnhancedReview> reviews) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (EnhancedReview review : reviews) {
                String line = String.join(DELIMITER,
                        String.valueOf(review.getId()),
                        review.getMovieTitle(),
                        review.getReviewText(),
                        String.valueOf(review.getRating()),
                        review.getUserName()
                );
                writer.write(line);
                writer.newLine();
            }


        }
    }

    // Original methods modified for EnhancedReview
    // ... (update updateReview, deleteReview, getReviewById accordingly) ...
}


