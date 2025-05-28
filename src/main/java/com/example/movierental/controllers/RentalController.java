package com.example.movierental.controllers;

import com.example.movierental.models.Movie;
import com.example.movierental.models.Rental;
import com.example.movierental.services.RentalService;
import com.example.movierental.utils.MovieStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private MovieStack movieStack;

    @GetMapping
    public String viewAll(Model model) {
        model.addAttribute("rentals", rentalService.getAllRentals());
        model.addAttribute("recentlyWatched", movieStack.getAll());
        return "rental-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("rental", new Rental());
        return "rental-form";
    }

    @PostMapping("/add")
    public String addRental(@ModelAttribute Rental rental) {
        rental.setRentalId(UUID.randomUUID().toString());
        rentalService.addRental(rental);
        return "redirect:/rentals";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") String id, Model model) {
        Rental rental = rentalService.getRentalById(id);
        model.addAttribute("rental", rental);
        return "rental-edit-form";
    }

    @PostMapping("/update")
    public String updateRental(@ModelAttribute Rental rental) {
        rentalService.updateRental(rental);
        return "redirect:/rentals";
    }

    @GetMapping("/delete/{id}")
    public String deleteRental(@PathVariable String id) {
        rentalService.deleteRental(id);
        return "redirect:/rentals";
    }

    @GetMapping("/watch/{id}")
    public String watchMovie(@PathVariable String id, Model model) {
        Rental rental = rentalService.getRentalById(id);
        if (rental != null) {
            Movie movie = new Movie();
            movie.setId(System.currentTimeMillis());
            movie.setTitle(rental.getMovieTitle());
            movieStack.push(movie);
            model.addAttribute("rental", rental);
        }
        return "watch";
    }
}