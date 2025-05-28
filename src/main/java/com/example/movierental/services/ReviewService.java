package com.example.movierental.services;

import com.example.movierental.dataStructures.MyArray;
import com.example.movierental.models.Review;
import com.example.movierental.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    // Return MyArray instead of List
    public Review[] getAllReviews() {
        return reviewRepository.getAllReviews();
    }

    public MyArray<Review> getAllReviewsAsMyArray() {
        return reviewRepository.getAllReviewsAsMyArray();
    }

    // Assuming your repository accepts MyArray or Review objects as-is
    public void addReview(Review review) {
        reviewRepository.addReview(review);
    }

    public Review[] sortReviewsByRatingDescending(Review[] reviews) {
        Review[] sorted = reviews.clone();

        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = 0; j < sorted.length - i - 1; j++) {
                if (sorted[j].getRating() < sorted[j + 1].getRating()) {
                    Review temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }

        return sorted;
    }


    public void updateReview(Review review) {
        reviewRepository.updateReview(review);
    }

    public void deleteReview(int id) {
        reviewRepository.deleteReview(id);
    }
}