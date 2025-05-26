package com.example.movierental.utils;

import com.example.movierental.models.Review;
import com.example.movierental.dataStructures.MyArray;

import java.io.*;
import java.time.LocalDate;

public class ReviewUtils {

    public static MyArray<Review> loadReviewsFromTextFile() {
        MyArray<Review> reviews = new MyArray<>();
        File file = new File("reviews.txt"); // Ensure the file exists

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
