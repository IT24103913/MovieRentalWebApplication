package com.example.movierental.controllers;

import com.example.movierental.models.Rental;
import com.example.movierental.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rentals")

public class RentalController {
    private final RentalService rentalService;

    public RentalController() {
        this(null);
    }

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/rent")
    public String rentMovie(@RequestBody Rental rental) throws IOException {
        rentalService.getAllRentals();
        return "Movie rented successfully.";
    }

    @GetMapping
    public List<Rental> viewAllRentals() throws IOException {
        return rentalService.getAllRentals();
    }

    @PutMapping("/return/{id}")
    public String returnMovie(@PathVariable String id) throws IOException {
        rentalService.markAsReturned(id);
        return "Movie returned successfully.";
    }
    @DeleteMapping("/{id}")
    public String deleteRental(@PathVariable String id) throws IOException {
        rentalService.deleteRental(id);
        return "Rental deleted successfully.";
    }
}    

