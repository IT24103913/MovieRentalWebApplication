package com.example.movierental.services;

import com.example.movierental.models.Movie;
import com.example.movierental.models.Rental;
import com.example.movierental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepo;

    public List<Rental> getAllRentals() {
        return rentalRepo.getAllRentals();
    }

    public void addRental(Rental rental) {
        rentalRepo.saveRental(rental);
    }

    public Rental getRentalById(String rentalId) {
        return rentalRepo.getAllRentals().stream()
                .filter(r -> r.getRentalId().equals(rentalId))
                .findFirst()
                .orElse(null);
    }

    public void updateRental(Rental updatedRental) {
        rentalRepo.updateRental(updatedRental);
    }

    public void deleteRental(String id) {
        rentalRepo.deleteRental(id);
    }
}