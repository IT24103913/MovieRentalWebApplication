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

    @GetMapping
    public String showAllReviews(Model model) throws IOException {
        model.addAttribute("reviews", reviewService.getAllReviews());
        model.addAttribute("newReview", new Review());
        return "reviews";
    }

    @PostMapping("/create")
    public String createReview(@ModelAttribute("newReview") Review review,
                               RedirectAttributes redirectAttributes) {
        try {
            reviewService.createReview(review);
            redirectAttributes.addFlashAttribute("success", "Your chronicle has been carved in stone!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to preserve your chronicle: " + e.getMessage());
        }
        return "redirect:/reviews";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Review id, Model model) throws IOException {
        model.addAttribute("reviewToEdit", reviewService.updateReview(id));
        return "edit-review";
    }

    @PostMapping("/update/{id}")
    public String updateReview(@PathVariable int id,
                               @ModelAttribute Review updatedReview,
                               RedirectAttributes redirectAttributes) {
        try {
            reviewService.updateReview(updatedReview);
            redirectAttributes.addFlashAttribute("success",
                    "Chronicle updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Update failed: " + e.getMessage());
        }
        return "redirect:/reviews";
    }

    @PostMapping("/delete/{id}")
    public String deleteReview(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            reviewService.deleteReview(id);
            redirectAttributes.addFlashAttribute("success",
                    "Chronicle burned successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Failed to burn chronicle: " + e.getMessage());
        }
        return "redirect:/reviews";
    }

    @PostMapping("/submitReview")
    public String submitReview(@RequestParam String movieTitle,
                               @RequestParam String userName,
                               @RequestParam String comment,
                               @RequestParam int rating) {

        Review review = new Review(0, movieTitle, comment, rating, userName);
        reviewService.createReview(review);

        return "redirect:/reviews";
    }
}
