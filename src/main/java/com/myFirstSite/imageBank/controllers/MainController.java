package com.myFirstSite.imageBank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String showInfoPage() {
        return "info";
    }
}
