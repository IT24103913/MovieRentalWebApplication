package com.example.movierental.controllers;

import com.example.movierental.models.Review;
import com.example.movierental.services.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController() {
        this.reviewService = new ReviewService();
    }

    @GetMapping
    public String viewAllReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews"; // Return the Thymeleaf template name
    }

    @GetMapping("/add")
    public String addReviewForm(Model model) {
        model.addAttribute("review", new Review(0, "", "", 0)); // Empty review for form
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

    @PostMapping("/reviews/delete/{id}")
    public String deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews/delete"; // or to your delete page
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            // Get all reviews first
            List<Review> allReviews = reviewService.getAllReviews();

            // Find the exact review to delete
            Review toDelete = null;
            for (Review review : allReviews) {
                if (review.getId() == id) {
                    toDelete = review;
                    break;
                }
            }

            if (toDelete != null) {
                // Create a temporary list without the deleted review
                List<Review> updatedReviews = allReviews.stream()
                        .filter(review -> review.getId() != id)
                        .collect(Collectors.toList());

                // Update the data source through the service
                reviewService.saveAllReviews(updatedReviews);
            }

            return "redirect:/reviews";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting review");
            return "redirect:/reviews";
        }



    }
}