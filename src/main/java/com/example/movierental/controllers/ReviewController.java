package com.example.movierental.controllers;

import com.example.movierental.models.Review;
import com.example.movierental.services.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "index"; // maps to templates/index.html
    }

    @PostMapping("/submit-review")
    public String submitReview(Review review) {
        reviewService.addReview(review);
        return "redirect:/";
    }
}

