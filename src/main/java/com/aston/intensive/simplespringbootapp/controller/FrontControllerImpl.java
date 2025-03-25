package com.aston.intensive.simplespringbootapp.controller;

import org.springframework.stereotype.Controller;

@Controller
public class FrontControllerImpl implements FrontController {

    @Override
    public String homePage() {
        return "index";
    }

    @Override
    public String addressPage() {
        return "address";
    }

    @Override
    public String attractionPage() {
        return "attraction";
    }

    @Override
    public String servicePage() {
        return "service";
    }

    @Override
    public String ticketInfoPage() {
        return "ticket-info";
    }
}
