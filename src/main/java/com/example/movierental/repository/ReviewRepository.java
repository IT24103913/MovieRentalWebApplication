package com.example.movierental.repository;

import com.example.movierental.models.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    private static final String FILE_PATH = "utils/reviews.txt";
    private static final String DELIMITER = ";;";

    public static List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        File file = new File(FILE_PATH);

        try {
            if (!file.exists()) file.createNewFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(DELIMITER);
                    if (parts.length == 5) {
                        reviews.add(new Review(
                                Integer.parseInt(parts[0]),
                                parts[1],
                                parts[2],
                                Integer.parseInt(parts[3]),
                                parts[4]
                        ));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public static void saveAllReviews(List<Review> reviews) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Review review : reviews) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveReview(Review review) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = String.join(DELIMITER,
                    String.valueOf(review.getId()),
                    review.getMovieTitle(),
                    review.getReviewText(),
                    String.valueOf(review.getRating()),
                    review.getUserName()
            );
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
