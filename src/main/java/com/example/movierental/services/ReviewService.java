package com.example.movierental.services;

import com.example.movierental.models.Review;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    private static final String FILE_PATH = "C:\\Users\\kavee\\Desktop\\OOP Project\\MovieRental\\src\\main\\resources\\reviews.txt";

    // Add a review to the file
    public void addReview(Review review) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(review.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get all reviews from the file
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                Review review = Review.fromString(line);
                if (review != null) {
                    reviews.add(review);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}



