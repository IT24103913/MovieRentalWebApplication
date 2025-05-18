package com.example.movierental.controllers;

import com.example.movierental.models.Director;
import com.example.movierental.services.DirectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    private DirectorService service;

    // Form to add director
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("director", new Director(
                service.getNextID(), "", "", 0, "", 0, "")
        );
        return "add-director";
    }

    @PostMapping("/add")
    public String addDirector(@ModelAttribute Director director) throws IOException {
        // Generate the ID manually
        int generatedId = service.getNextID();
        director.setId(generatedId);

        // Save the director
        service.addDirector(director);
        return "redirect:/directors/admin";
    }

    // User view
    @GetMapping("/list")
    public String viewAllForUsers(Model model) throws IOException {
        model.addAttribute("directors", service.getAllDirectors());
        return "view-directors-user";
    }

    // Admin view
    @GetMapping("/admin")
    public String viewAllForAdmin(Model model) throws IOException {
        model.addAttribute("directors", service.getAllDirectors());
        return "view-directors-admin";
    }

    // Edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) throws IOException {
        Director director = service.getDirectorById(id);
        model.addAttribute("director", director);
        return "edit-director";
    }

    @PostMapping("/edit")
    public String updateDirector(@ModelAttribute Director director) throws IOException {
        service.updateDirector(director);
        return "redirect:/directors/admin";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String deleteDirector(@PathVariable int id) throws IOException {
        service.deleteDirector(id);
        return "redirect:/directors/admin";
    }
}
