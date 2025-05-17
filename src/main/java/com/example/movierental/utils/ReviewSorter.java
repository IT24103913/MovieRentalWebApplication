package com.example.movierental.utils;

import com.example.movierental.dataStructures.MyArray;
import com.example.movierental.models.Review;

public class ReviewSorter {
    public static void bubbleSortByRating(MyArray<Review> reviews) {
        if (reviews == null || reviews.size() <= 1) {
            return;
        }

        int n = reviews.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                Review current = reviews.get(j);
                Review next = reviews.get(j + 1);
                // Check for null reviews to avoid NullPointerException
                if (current != null && next != null &&
                        current.getRating() < next.getRating()) {
                    // Swap reviews
                    reviews.set(j, next);
                    reviews.set(j + 1, current);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}