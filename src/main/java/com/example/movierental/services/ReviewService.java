package com.example.movierental.services;

import com.example.movierental.models.Review;
import com.example.movierental.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> getAllReviews() throws IOException {
        return repository.findAll();
    }

    public Optional<Review> getReviewById(int id) throws IOException {
        return getAllReviews().stream()
                .filter(r -> r.getId() == id)
                .findFirst();
    }

    public void createReview(Review review) throws IOException {
        List<Review> reviews = repository.findAll();
        int newId = reviews.stream().mapToInt(Review::getId).max().orElse(0) + 1;
        review.setId(newId);
        reviews.add(review);
        repository.saveAll(reviews);
    }

    public void updateReview(Review updatedReview) throws IOException {
        List<Review> reviews = repository.findAll();
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getId() == updatedReview.getId()) {
                reviews.set(i, updatedReview);
                break;
            }
        }
        repository.saveAll(reviews);
    }

    public void deleteReview(int id) throws IOException {
        List<Review> reviews = repository.findAll();
        reviews.removeIf(r -> r.getId() == id);
        repository.saveAll(reviews);
    }

    public void bubbleSortByRating(List<Review> reviews, boolean ascending) {
        int n = reviews.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                int comparison = Integer.compare(
                        reviews.get(j).getRating(),
                        reviews.get(j + 1).getRating()
                );
                if (ascending ? comparison > 0 : comparison < 0) {
                    Collections.swap(reviews, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}


