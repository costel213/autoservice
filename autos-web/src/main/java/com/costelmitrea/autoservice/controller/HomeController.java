package com.costelmitrea.autoservice.controller;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserDetailsService userDetailsService;

    public HomeController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping({"/", "/home"})
    public String home() {
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }
}
