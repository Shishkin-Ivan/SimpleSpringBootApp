package com.aston.intensive.simplespringbootapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public interface FrontController {

    @GetMapping("/")
    String homePage();

    @GetMapping("/address")
    String addressPage();

    @GetMapping("/attraction")
    String attractionPage();

    @GetMapping("/service")
    String servicePage();

    @GetMapping("/ticket-info")
    String ticketInfoPage();


}