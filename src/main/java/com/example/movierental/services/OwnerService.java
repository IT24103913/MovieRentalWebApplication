package com.example.movierental.services;

import com.example.movierental.models.Admin;
import com.example.movierental.models.Owner;
import com.example.movierental.repository.AdminRepository;
import com.example.movierental.repository.OwnerRepository;

import java.util.List;

public class OwnerService{
    public void registerOwner(String email, String firstName, String lastName, String password, String age, String description) {
        Owner newOwner = new Owner(email, firstName, lastName, password, age, description);
        OwnerRepository.saveOwner(newOwner);
    }
    public List<Owner> getAllOwners() {
        return OwnerRepository.getAllOwners();
    }

}
