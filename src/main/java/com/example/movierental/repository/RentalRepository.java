package com.example.movierental.repository;

import com.example.movierental.models.Rental;

import java.io.*;
import java.util.*;

public class RentalRepository {
    private static final String FILE_PATH = "rentals.txt";

    public void saveRental(Rental rental) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
        writer.write(String.format("%s,%s,%s,%s\n",
                rental.getRentalId(),
                rental.getMovieTitle(),
                rental.getUserId(),
                rental.isReturned()));
        writer.close();
    }

    public List<Rental> getAllRentals() throws IOException {
        List<Rental> rentals = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            Rental rental = new Rental(parts[0], parts[1], parts[2]);
            rental.setReturned(Boolean.parseBoolean(parts[3]));
            rentals.add(rental);
        }
        reader.close();
        return rentals;
    }

    public void updateRentals(List<Rental> rentals) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Rental rental : rentals) {
            writer.write(String.format("%s,%s,%s,%s\n",
                    rental.getRentalId(),
                    rental.getMovieTitle(),
                    rental.getUserId(),
                    rental.isReturned()));
        }
        writer.close();
    }

    public void deleteRental(String rentalId) throws IOException {
        List<Rental> rentals = getAllRentals();
        rentals.removeIf(r -> r.getRentalId().equals(rentalId));
        updateRentals(rentals);
    }
}
