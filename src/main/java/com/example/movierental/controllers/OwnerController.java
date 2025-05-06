package com.example.movierental.controllers;

import com.example.movierental.models.Owner;
import com.example.movierental.services.OwnerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OwnerController {
    private final OwnerService ownerService = new OwnerService();

    @GetMapping("/owner/register")
    public String ownerRegistration() {
        return "owner-registration";
    }

    @PostMapping("/owner/register")
    public String handleRegistration(@RequestParam String email,
                                     @RequestParam String firstName,
                                     @RequestParam String lastName,
                                     @RequestParam String password,
                                     @RequestParam String age,
                                     @RequestParam String description,
                                     Model model) {
        ownerService.registerOwner(email, firstName, lastName, password, age, description);
        model.addAttribute("success", true);
        return "owner-registration";
    }

    @GetMapping("/owner/profile")
    public String viewOwnerProfile(HttpSession session, Model model) {
        String email = (String) session.getAttribute("loggedInAdminEmail");

        if (email == null) {
            return "redirect:/login"; // not logged in
        }

        Owner owner = ownerService.viewOwner(email); // Make sure this fetches from your .txt or data store
        model.addAttribute("owner", owner);
        return "owner-profile";
    }

}
