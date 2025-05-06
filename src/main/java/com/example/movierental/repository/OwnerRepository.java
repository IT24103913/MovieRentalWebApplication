package com.example.movierental.repository;

import com.example.movierental.models.Owner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerRepository {
    private static final String FILE_PATH = "utils/admins.txt";

    public static void saveOwner(Owner owner) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(owner.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Owner> getAllOwners() {
        List<Owner> owners = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                Owner owner = Owner.fromString(line);
                if (owner != null) owners.add(owner);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return owners;
    }

    public Owner findByEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(email)) {
                    Owner owner = new Owner();
                    owner.setEmail(parts[0]);
                    owner.setPassword(parts[1]);
                    owner.setFirstName(parts[2]);
                    owner.setLastName(parts[3]);
                    owner.setAge(parts[4]);
                    owner.setDescription(parts[5]);
                    return owner;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}