package com.project.Covoiturage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Home Page");
        model.addAttribute("content", "index");
        return "Home";
    }

}
