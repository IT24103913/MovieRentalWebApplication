package com.example.movierental.controllers;

import ch.qos.logback.core.model.Model;
import com.example.movierental.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private final AdminService adminService = new AdminService();

    @GetMapping("/")
    public String showWelcomePage() {
        return "welcome";
    }

    @GetMapping("/signup")
    public String showRoleSelectionPage() {
        return "role-selection"; // maps to role-selection.html
    }

    @GetMapping("/register-admin")
    public String showAdminRegistrationForm() {
        return "register";
    }


    @PostMapping("/register")
    public String handleRegistration(@RequestParam String email,
                                     @RequestParam String firstName,
                                     @RequestParam String lastName,
                                     @RequestParam String password,
                                     @RequestParam String age,
                                     Model model) {
        adminService.registerAdmin(email, firstName,lastName, password, age);
       // model.addAttribute("message", "Registration submitted. Wait for approval.");
        return "register";
    }
}
