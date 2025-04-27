package com.example.movierental.services;

import com.example.movierental.models.Review;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getAllReviews() {
        return reviews;
    }
}
