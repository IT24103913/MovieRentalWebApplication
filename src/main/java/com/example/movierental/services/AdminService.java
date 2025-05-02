package com.example.movierental.services;

import com.example.movierental.models.Admin;
import com.example.movierental.repository.AdminRepository;

import java.util.List;

public class AdminService {
    public void registerAdmin(String email, String firstName, String lastName, String password, String age) {
        Admin newAdmin = new Admin(email, firstName, lastName, password, age);
        AdminRepository.saveAdmin(newAdmin);
    }

//    public List<Admin> getAllAdmins() {
//        return AdminRepository.getAllAdmins();
//    }
//
//    public List<Admin> getPendingAdmins() {
//        return getAllAdmins().stream()
//                .filter(admin -> admin.getStatus().equals("PENDING"))
//                .toList();
//    }
}
