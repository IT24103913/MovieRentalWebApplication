package com.example.movierental.utils;

import com.example.movierental.models.Review;

import java.util.List;

public class ReviewSorter {
    public static void bubbleSortByRating(List<Review> reviews) {
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