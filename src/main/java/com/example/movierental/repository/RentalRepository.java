package com.example.movierental.repository;

import com.example.movierental.models.Rental;

import java.io.*;
import java.util.*;

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

    public void overwriteAll(List<Rental> rentals) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Rental rental : rentals) {
                bw.write(rental.toString());
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
                    writer.write(rental.getRentalId() + "," + rental.getMovieTitle() + "," + rental.getUserId() + "," +
                            rental.isReturned());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
