package com.example.movierental.services;

import com.example.movierental.models.Review;
import com.example.movierental.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.getAllReviews();
    }

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
