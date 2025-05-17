package com.example.movierental.controllers;

import com.example.movierental.models.Admin;
import com.example.movierental.services.AdminServiceInterface;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.example.movierental.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    private final AdminServiceInterface adminService = new AdminService();

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
                                     @RequestParam String description,
                                     Model model) {
        adminService.registerAdmin(email, firstName, lastName, password, age, description);
        model.addAttribute("success", true);
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email,
                              @RequestParam String password,
                              Model model,
                              HttpSession session) {
        Admin admin = adminService.login(email, password);

        if (admin == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        session.setAttribute("loggedInAdminEmail", admin.getEmail());
        session.setAttribute("loggedIn", admin);

        if ("owner".equalsIgnoreCase(admin.getRole())) {
            return "redirect:/owner/dashboard";
        } else {
            if ("approved".equalsIgnoreCase(admin.getStatus())) {
                return "redirect:/admin/dashboard";
            } else if ("rejected".equalsIgnoreCase(admin.getStatus())) {
                model.addAttribute("error", "You have rejected the role");
                return "login";
            } else if ("pending".equalsIgnoreCase(admin.getStatus())) {
                model.addAttribute("error", "You have pending the role");
                return "login";
            }
            return "login";
        }
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboardPage() {
        return "admin-dashboard";
    }

    @GetMapping("/owner/admin/list")
    public String showOwnerAdminList(Model model) {
        model.addAttribute("pendingAdmins", adminService.getAdminsByStatus("pending"));
        model.addAttribute("approvedAdmins", adminService.getAdminsByStatus("approved"));
        model.addAttribute("rejectedAdmins", adminService.getAdminsByStatus("rejected"));
        return "admin-list";
    }

    @GetMapping("/owner/dashboard")
    public String showOwnerDashboard(Model model) {
        model.addAttribute("countPending", adminService.countPendingAdmins());
        model.addAttribute("countApproved", adminService.countApprovedAdmins());
        model.addAttribute("countRejected", adminService.countRejectedAdmins());
        return "owner-dashboard";
    }

    @GetMapping("/admin/approve/{email}")
    public String approveAdmin(@PathVariable String email) {
        adminService.approveAdmin(email);
        return "redirect:/owner/admin/list";
    }

    @GetMapping("/admin/reject/{email}")
    public String rejectAdmin(@PathVariable String email) {
        adminService.rejectAdmin(email);
        return "redirect:/owner/admin/list";
    }

    @GetMapping("/admin/view/{email}")
    public String viewAdminProfile(@PathVariable String email, Model model) {
        Admin admin = adminService.getAdminByEmail(email);
        if (admin != null) {
            model.addAttribute("admin", admin);
            return "admin-profile"; // this is the view that will show admin details
        } else {
            return "redirect:/owner/admin/list"; // or show error page if not found
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // This clears all session data
        return "redirect:/login"; // Redirect to your login page
    }

    @GetMapping("/choose-role")
    public String chooseRole() {
        return "choose-role"; // Name of your Thymeleaf HTML file
    }

    @GetMapping("/admin/delete/{email}")
    public String deleteAdmin(@PathVariable String email) {
        adminService.deleteAdmin(email);
        return "redirect:/owner/admin/list";
    }
}
