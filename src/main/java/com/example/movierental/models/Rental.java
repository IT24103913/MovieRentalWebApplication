package com.example.movierental.models;

public class Rental {
    private String rentalId;
    private String movieTitle;
    private String userId;
    private boolean isReturned;

    public Rental(String rentalId, String movieTitle, String userId) {
        this.rentalId = rentalId;
        this.movieTitle = movieTitle;
        this.userId = userId;
        this.isReturned = false;
    }

       // Getters and setters (Encapsulation)
        public String getRentalId() { return rentalId; }
        public void setRentalId(String rentalId) { this.rentalId = rentalId; }

        public String getMovieTitle() { return movieTitle; }
        public void setMovieId(String movieTitle) { this.movieTitle = movieTitle; }

        public String getUserId() { return userId; }
        public void setCustomerId(String userId) { this.userId = userId; }

        public boolean isReturned() { return isReturned; }
        public void setReturned(boolean returned) { isReturned = returned; }

    // For file storage
    @Override
    public String toString() {
        return rentalId + "," + movieTitle + "," + userId + ","  + isReturned;
    }
}



