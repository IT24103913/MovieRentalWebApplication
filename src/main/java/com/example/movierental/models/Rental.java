package com.example.movierental.models;

public class Rental {
    private String rentalId;
    private String movieTitle;
    private String userId;
    private boolean isReturned;

    public Rental() {}

    public Rental(String rentalId, String movieTitle, String userId, boolean isReturned) {
        this.rentalId = rentalId;
        this.movieTitle = movieTitle;
        this.userId = userId;
        this.isReturned = isReturned;
    }

    // Getters and Setters
    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return rentalId + "," + movieTitle + "," + userId + "," + isReturned;
    }

    public static Rental fromString(String line) {
        String[] parts = line.split(",");
        return new Rental(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3]));
    }
}