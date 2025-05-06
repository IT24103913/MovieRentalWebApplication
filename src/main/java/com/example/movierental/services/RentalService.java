package com.example.movierental.services;

import com.example.movierental.models.Rental;
import com.example.movierental.repository.RentalRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class RentalService {
    private final RentalRepository rentalRepo = new RentalRepository();

    public List<Rental> getAllRentals() {
        return rentalRepo.getAllRentals();
    }

    public void addRental(Rental rental) {
        rentalRepo.saveRental(rental);
    }

    public void updateRental(Rental updatedRental) {
        List<Rental> rentals = rentalRepo.getAllRentals();
        for (int i = 0; i < rentals.size(); i++) {
            if (rentals.get(i).getRentalId().equals(updatedRental.getRentalId())) {
                rentals.set(i, updatedRental);
                break;
            }
        }
        rentalRepo.overwriteAll(rentals);
    }

    public Rental getRentalById(String rentalId) {
        return rentalRepo.getAllRentals().stream()
                .filter(r -> r.getRentalId().equals(rentalId))
                .findFirst()
                .orElse(null);
    }
}

