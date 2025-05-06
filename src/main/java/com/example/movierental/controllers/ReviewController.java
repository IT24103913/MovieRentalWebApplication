package com.example.movierental.controllers;

import com.example.movierental.models.Review;
import com.example.movierental.services.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Get all reviews with sorting
    @GetMapping
    public String showAllReviews(
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc") String order,
            Model model
    ) throws IOException {
        model.addAttribute("reviews", reviewService.getAllSortedReviews(sortBy, order));
        model.addAttribute("newReview", new Review());
        return "reviews";
    }

    // Create new review
    @PostMapping("/create")
    public String createReview(
            @ModelAttribute("newReview") Review review,
            RedirectAttributes redirectAttributes
    ) {
        try {
            reviewService.createReview(review);
            System.out.println("✅ Review saved successfully!");
            redirectAttributes.addFlashAttribute("success",
                    "Chronicle carved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Carving failed: " + e.getMessage());
        }
        return "redirect:/reviews";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable int id,
            Model model
    ) throws IOException {
        model.addAttribute("reviewToEdit", reviewService.getReviewById(id));
        return "edit-review";
    }

    // Update review
    @PostMapping("/update/{id}")
    public String updateReview(
            @PathVariable int id,
            @ModelAttribute Review updatedReview,
            RedirectAttributes redirectAttributes
    ) {
        try {
            reviewService.updateReview(updatedReview);
            redirectAttributes.addFlashAttribute("success",
                    "Chronicle updated!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Update failed: " + e.getMessage());
        }
        return "redirect:/reviews";
    }

    // Delete review
    @PostMapping("/delete/{id}")
    public String deleteReview(
            @PathVariable int id,
            RedirectAttributes redirectAttributes
    ) {
        try {
            reviewService.deleteReview(id);
            redirectAttributes.addFlashAttribute("success",
                    "Chronicle burned!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Burning failed: " + e.getMessage());
        }
        return "redirect:/reviews";
    }
}