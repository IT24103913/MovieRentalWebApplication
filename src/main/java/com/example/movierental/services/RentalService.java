package com.example.movierental.services;

import com.example.movierental.models.Rental;
import com.example.movierental.repository.RentalRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class RentalService {
    private RentalRepository repository = new RentalRepository();

    public void createRental(String movieTitle, String userId) throws IOException {
        String id = UUID.randomUUID().toString();
        Rental rental = new Rental(id, movieTitle, userId);
        repository.saveRental(rental);
    }

    public List<Rental> getAllRentals() throws IOException {
        return repository.getAllRentals();
    }

    public void markAsReturned(String rentalId) throws IOException {
        List<Rental> rentals = repository.getAllRentals();
        for (Rental rental : rentals) {
            if (rental.getRentalId().equals(rentalId)) {
                rental.setReturned(true);
            }
        }
        repository.updateRentals(rentals);
    }

    public void deleteRental(String rentalId) throws IOException {
        repository.deleteRental(rentalId);
    }
}
