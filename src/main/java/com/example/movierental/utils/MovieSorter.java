package com.example.movierental.utils;

import com.example.movierental.dataStructures.MyArray;
import com.example.movierental.models.Movie;
import com.example.movierental.models.Review;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class MovieSorter {
    public static List<MovieWithRating> calculateAverageRatings(List<Movie> movies, MyArray<Review> reviews) {
        List<MovieWithRating> movieRatings = new ArrayList<>();

        for (Movie movie : movies) {
            double totalRating = 0.0;
            int reviewCount = 0;

            // Find all reviews for this movie
//            for (Review review : reviews) {
//                if (review.getMovieTitle().equalsIgnoreCase(movie.getTitle())) {
//                    totalRating += review.getRating();
//                    reviewCount++;
//                }
//            }

            // Calculate average rating (0 if no reviews)
            double averageRating = (reviewCount > 0) ? totalRating / reviewCount : 0.0;
            movieRatings.add(new MovieWithRating(movie, averageRating));
        }

        return movieRatings;
    }
}