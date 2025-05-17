package com.example.movierental.controllers;

// controllers/UserController.java



import com.example.movierental.models.User;
import com.example.movierental.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Signup
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpUser(@Valid @ModelAttribute("user") User user,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "signup";
        }
        try {
            userService.signUpUser(user);
            return "redirect:/users/loginUs";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    // Login
    @GetMapping("/loginUs")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "loginUs";
    }

    @PostMapping("/loginUs")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        try {
            User user = userService.signInUser(email, password);
            session.setAttribute("currentUser", user);
            return "redirect:/movies";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid credentials");
            return "loginUs";
        }
    }

    // Profile
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return "redirect:/users/loginUs";
        }
        model.addAttribute("user", user);
        return "userprofile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid @ModelAttribute("user") User updatedUser,
                                BindingResult result,
                                HttpSession session,
                                Model model) {
        if (result.hasErrors()) {
            return "userprofile";
        }
        try {
            User currentUser = (User) session.getAttribute("currentUser");
            updatedUser.setEmail(currentUser.getEmail());
            User user = userService.updateUser(updatedUser);
            session.setAttribute("currentUser", user);
            model.addAttribute("success", "Profile updated successfully");
            return "userprofile";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "userprofile";
        }
    }

    // Delete User
    @PostMapping("/delete")
    public String deleteUser(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            userService.deleteUser(user.getEmail());
            session.invalidate();
        }
        return "redirect:/users/loginUs";
    }

    // List Users
    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    @PostMapping("/delete/{email}")
    public String deleteUserByEmail(@PathVariable String email, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            // Optionally add authorization check here
            // For example, only allow admin to delete other users
            userService.deleteUser(email);

            // If user is deleting themselves, invalidate session
            if (currentUser.getEmail().equals(email)) {
                session.invalidate();
                return "redirect:/users/list";
            }
        }
        return "redirect:/users/list";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/loginUs";
    }
}