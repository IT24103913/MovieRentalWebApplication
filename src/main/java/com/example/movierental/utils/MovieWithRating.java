package com.example.movierental.utils;

import com.example.movierental.models.Movie;

public class MovieWithRating {
    private Movie movie;
    private double averageRating;

    public MovieWithRating(Movie movie, double averageRating) {
        this.movie = movie;
        this.averageRating = averageRating;
    }

    public Movie getMovie() {
        return movie;
    }

    public double getAverageRating() {
        return averageRating;
    }
}