package com.example.movierental.utils;

import com.example.movierental.models.Review;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewUtils {

    public static List<Review> loadReviewsFromTextFile() {
        List<Review> reviews = new ArrayList<>();
        File file = new File("reviews.txt"); // Make sure this file exists

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::"); // Adjust based on your file format

                if (parts.length >= 5) {
                    Review review = new Review();
                    review.setUserName(parts[0]);
                    review.setMovieTitle(parts[1]);
                    review.setReviewText(parts[2]);
                    review.setRating(Integer.parseInt(parts[3]));
                    review.setDate(LocalDate.parse(parts[4]));

                    reviews.add(review);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reviews;
    }
}
