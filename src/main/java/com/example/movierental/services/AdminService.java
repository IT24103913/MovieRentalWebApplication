package com.example.movierental.services;

import com.example.movierental.models.Admin;
import com.example.movierental.repository.AdminRepository;

import java.util.List;

public class AdminService implements AdminServiceInterface {
    public void registerAdmin(String email, String firstName, String lastName, String password, String age, String description) {
        Admin newAdmin = new Admin(email, firstName, lastName, password, age, description);
        AdminRepository.saveAdmin(newAdmin);
    }

    public Admin login(String email, String password) {
        List<Admin> admins = getAllAdmins(); // Read from your .txt file
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }


    public List<Admin> getAllAdmins() {
        return AdminRepository.getAllAdmins();
    }

    public List<Admin> getAdminsByStatus(String status) {
        return getAllAdmins().stream()
                .filter(admin -> admin.getStatus().equals(status))
                .toList();
    }

    public void approveAdmin(String email) {
        AdminRepository.updateAdminStatus(email, "approved");
    }

    public void rejectAdmin(String email) {
        AdminRepository.updateAdminStatus(email, "rejected");
    }

    public int countPendingAdmins() {
        return AdminRepository.countByStatus("pending");
    }

    public long countApprovedAdmins() {
        return AdminRepository.countByStatus("approved");
    }

    public long countRejectedAdmins() {
        return AdminRepository.countByStatus("rejected");
    }

    public Admin getAdminByEmail(String email) {
        List<Admin> admins = AdminRepository.getAllAdmins();
        for (Admin admin : admins) {
            if (admin.getEmail().equalsIgnoreCase(email)) {
                return admin;
            }
        }
        return null; // not found
    }
}
