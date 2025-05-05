package com.example.movierental.services;

import com.example.movierental.models.Admin;

import java.util.List;

public interface AdminServiceInterface {
    void registerAdmin(String email, String firstName, String lastName, String password, String age, String description);

    Admin login(String email, String password);

    List<Admin> getAllAdmins();

    List<Admin> getAdminsByStatus(String status);

    void approveAdmin(String email);

    void rejectAdmin(String email);

    int countPendingAdmins();

    long countApprovedAdmins();

    long countRejectedAdmins();

    Admin getAdminByEmail(String email);
}
