package com.bankManagement.bankManagement.controller;

import com.bankManagement.bankManagement.model.User;
import com.bankManagement.bankManagement.service.UserService;
import com.bankManagement.bankManagement.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BankService bankService;

    // Serve admin login page
    @GetMapping("/admin/login")
    public String showAdminLoginPage(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/admin/dashboard"; // Redirect to admin dashboard if already logged in
        }
        return "admin/login"; // Return admin login view
    }

    // Serve customer login page
    @GetMapping("/customer/login")
    public String showCustomerLoginPage(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/customer/dashboard"; // Redirect to customer dashboard if already logged in
        }
        return "customer/login"; // Return customer login view
    }

    // Handle admin login
    @PostMapping("/admin/login")
    public String loginAdmin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        if (email == null || password == null) {
            model.addAttribute("error", "Email and password must be provided");
            return "admin/login"; // Redirect back to admin login with error
        }

        Optional<User> userOptional = userService.login(email, password);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if ("admin".equalsIgnoreCase(user.getRole())) {
                session.setAttribute("user", user); // Store admin in session
                return "redirect:/admin/dashboard"; // Redirect to admin dashboard
            } else {
                model.addAttribute("error", "Unauthorized access for admin login");
                return "admin/login"; // Redirect back to admin login with error
            }
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "admin/login"; // Redirect back to admin login with error
        }
    }

    // Handle customer login
    @PostMapping("/customer/login")
    public String loginCustomer(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        if (email == null || password == null) {
            model.addAttribute("error", "Email and password must be provided");
            return "customer/login"; // Redirect back to customer login with error
        }
        
        Optional<User> userOptional = userService.login(email, password);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if ("customer".equalsIgnoreCase(user.getRole())) {
                session.setAttribute("user", user); // Store customer in session
                return "redirect:/customer/dashboard"; // Redirect to customer dashboard
            } else {
                model.addAttribute("error", "Unauthorized access for customer login");
                return "customer/login"; // Redirect back to customer login with error
            }
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "customer/login"; // Redirect back to customer login with error
        }
    }

    // Handle admin logout
    @GetMapping("/admin/logout")
    public String logoutAdmin(HttpSession session) {
        session.invalidate(); // Invalidate session
        return "redirect:/admin/login"; // Redirect to admin login page
    }

    // Handle customer logout
    @GetMapping("/customer/logout")
    public String logoutCustomer(HttpSession session) {
        session.invalidate(); // Invalidate session
        return "redirect:/customer/login"; // Redirect to customer login page
    }

    // Serve admin dashboard
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null && "admin".equalsIgnoreCase(user.getRole())) {
            return "admin/dashboard"; // Return admin dashboard view
        }
        return "redirect:/admin/login"; // Redirect to admin login if not authenticated
    }

    // Serve customer dashboard
    @GetMapping("/customer/dashboard")
    public String showCustomerDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null && "customer".equalsIgnoreCase(user.getRole())) {
            // Fetch total accepted amount for the logged-in user
            Double totalAcceptedAmount = bankService.getTotalAcceptedAmountByUser(user.getId());

            // Add the total amount to the model
            model.addAttribute("totalAcceptedAmount", totalAcceptedAmount);

            return "customer/dashboard"; // Return customer dashboard view
        }
        return "redirect:/customer/login"; // Redirect to customer login if not authenticated
    }
}
