package com.myFirstSite.imageBank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showInfoPage(Model model) {
        model.addAttribute("statusInfo", "active");
        return "info";
    }
}
