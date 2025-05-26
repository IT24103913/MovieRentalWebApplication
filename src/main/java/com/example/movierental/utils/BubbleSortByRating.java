package com.example.movierental.utils;

import com.example.movierental.models.Movie;
import com.example.movierental.models.Review;
import com.example.movierental.utils.MovieWithRating;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class BubbleSortByRating  {

    public static void main(String[] args) {
        // Sample movies
        Movie[] movies = new Movie[3];
        movies[0] = new Movie(1L, "The Shawshank Redemption", "Drama", 1994, 3.99);
        movies[1] = new Movie(2L, "The Godfather", "Crime", 1972, 4.99);
        movies[2] = new Movie(3L, "Inception", "Sci-Fi", 2010, 2.99);

        // Sample reviews
        Review[] reviewArray = new Review[5];
        reviewArray[0] = new Review(1, "The Shawshank Redemption", "Amazing!", 5, LocalDate.now(), "Alice");
        reviewArray[1] = new Review(2, "The Shawshank Redemption", "Good", 4, LocalDate.now(), "Bob");
        reviewArray[2] = new Review(3, "The Godfather", "Classic!", 5, LocalDate.now(), "Charlie");
        reviewArray[3] = new Review(4, "The Godfather", "Okay", 3, LocalDate.now(), "Dave");
        reviewArray[4] = new Review(5, "Inception", "Mind-blowing", 4, LocalDate.now(), "Eve");

        // Convert arrays to lists just before calling calculateAverageRatings (no changes to that function)
        List<Movie> movieList = new ArrayList<>();
        for (Movie m : movies) {
            movieList.add(m);
        }

        List<Review> reviewList = new ArrayList<>();
        for (Review r : reviewArray) {
            reviewList.add(r);
        }
    }
}
