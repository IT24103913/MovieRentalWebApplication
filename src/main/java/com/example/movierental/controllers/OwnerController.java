package com.example.movierental.controllers;

import com.example.movierental.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
