package com.financeadvicechatbot.controller;

import com.financeadvicechatbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    // Get Mapping for the Register Page
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // Post Mapping for Register, trying to Register the User
    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password, Model model) {
            userService.registerUser(email, password);
            return "redirect:/login";
    }

    // Get Mapping for the Login Page
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
