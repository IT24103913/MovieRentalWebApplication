package com.example.movierental.controllers;

import com.example.movierental.models.Movie;
import com.example.movierental.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("recentMovies", movieService.getRecentlyWatchedMovies());
        return "movies/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movies/create";
    }

    @PostMapping("/create")
    public String createMovie(@ModelAttribute Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) return "redirect:/movies";
        model.addAttribute("movie", movie);
        return "movies/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable long id, @ModelAttribute Movie movie) {
        movie.setId(id);
        movieService.updateMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }

    // View details (adds to recently watched)
    @GetMapping("/view/{id}")
    public String viewMovie(@PathVariable long id) {
        movieService.getMovieById(id);
        return "redirect:/movies";
    }
}