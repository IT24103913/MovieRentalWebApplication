package com.example.movierental.repository;

import com.example.movierental.models.Rental;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentalRepository {
    private static final String FILE_PATH = "utils/rentals.txt";

    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                rentals.add(Rental.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    public void saveRental(Rental rental) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(rental.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateRental(Rental updatedRental) {
        List<Rental> rentals = getAllRentals();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Rental rental : rentals) {
                if (rental.getRentalId().equals(updatedRental.getRentalId())) {
                    bw.write(updatedRental.toString());
                } else {
                    bw.write(rental.toString());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteRental(String id) {
        List<Rental> rentals = getAllRentals();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Rental rental : rentals) {
                if (!rental.getRentalId().equalsIgnoreCase(id)) {
                    writer.write(rental.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}