package com.example.movierental.repositories;

import com.example.movierental.models.Review;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepository {
    private static final String FILE_PATH;
    private static final String DELIMITER = ";;";

    static {
        FILE_PATH = System.getProperty("user.dir") + File.separator + "reviews.txt";
    }

    public List<Review> findAll() throws IOException {
        List<Review> reviews = new ArrayList<>();
        File file = new File(FILE_PATH);

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
        return reviews;
    }

    public void saveAll(List<Review> reviews) throws IOException {
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
        }
    }
}
