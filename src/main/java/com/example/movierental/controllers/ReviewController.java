package com.example.movierental.controllers;

import com.example.movierental.models.Review;
import com.example.movierental.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public String viewAllReviews(@RequestParam(required = false) String sort, Model model) {
        List<Review> reviews = reviewService.getAllReviews();

        // Sort by rating if sort=rating
        if ("rating".equals(sort)) {
            bubbleSortByRating(reviews);
        }

        model.addAttribute("reviews", reviews);
        return "reviews"; // Return the Thymeleaf template name
    }

    @GetMapping("/add")
    public String addReviewForm(Model model) {
        model.addAttribute("review", new Review());
        return "addReview";
    }

    @PostMapping("/add")
    public String addReview(@ModelAttribute Review review) {
        reviewService.addReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/edit/{id}")
    public String editReviewForm(@PathVariable int id, Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        for (Review review : reviews) {
            if (review.getId() == id) {
                model.addAttribute("review", review);
                break;
            }
        }
        return "editReview";
    }

    @PostMapping("/edit")
    public String updateReview(@ModelAttribute Review review) {
        reviewService.updateReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }

    // Bubble sort by rating (descending order)
    private static void bubbleSortByRating(List<Review> reviews) {
        int n = reviews.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (reviews.get(j).getRating() < reviews.get(j + 1).getRating()) {
                    // Swap reviews
                    Review temp = reviews.get(j);
                    reviews.set(j, reviews.get(j + 1));
                    reviews.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}