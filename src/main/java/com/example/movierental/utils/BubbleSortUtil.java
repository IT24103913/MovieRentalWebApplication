package com.example.movierental.utils;

import com.example.movierental.models.Movie;
import java.util.List;
import java.util.Comparator;

public class BubbleSortUtil {

    public static void bubbleSort(List<Movie> movies, String sortBy) {
        int n = movies.size();
        Comparator<Movie> comparator;

        // Define comparator based on sortBy field
        switch (sortBy.toLowerCase()) {
            case "title":
                comparator = (m1, m2) -> m1.getTitle().compareToIgnoreCase(m2.getTitle());
                break;
            case "year":
                comparator = (m1, m2) -> Integer.compare(m1.getYear(), m2.getYear());
                break;
            case "rentalprice":
                comparator = (m1, m2) -> Double.compare(m1.getRentalPrice(), m2.getRentalPrice());
                break;
            default:
                return; // No sorting if invalid sortBy
        }

        // Bubble Sort implementation
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(movies.get(j), movies.get(j + 1)) > 0) {
                    // Swap elements
                    Movie temp = movies.get(j) ;
                    movies.set(j, movies.get(j + 1));
                    movies.set(j + 1, temp);
                }
            }
        }
    }
}