package com.example.movierental.services;

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

    // Assuming your repository accepts MyArray or Review objects as-is
    public void addReview(Review review) {
        reviewRepository.addReview(review);
    }

    public void updateReview(Review review) {
        reviewRepository.updateReview(review);
    }

    public void deleteReview(int id) {
        reviewRepository.deleteReview(id);
    }
}