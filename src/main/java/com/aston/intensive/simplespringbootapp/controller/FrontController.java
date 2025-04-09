package com.aston.intensive.simplespringbootapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling web page navigation in the application.
 * Maps frontend routes to their corresponding view templates.
 */
@Controller
public interface FrontController {

    /**
     * Displays the home page.
     *
     * @return the name of the home page view template.
     */
    @GetMapping("/")
    String homePage();

    /**
     * Displays the address management page.
     *
     * @return the name of the address page view template.
     */
    @GetMapping("/address")
    String addressPage();

    /**
     * Displays the attraction management page.
     *
     * @return the name of the attraction page view template.
     */
    @GetMapping("/attraction")
    String attractionPage();

    /**
     * Displays the service management page.
     *
     * @return the name of the service page view template.
     */
    @GetMapping("/service")
    String servicePage();

    /**
     * Displays the ticket information page.
     *
     * @return the name of the ticket info page view template.
     */
    @GetMapping("/ticket-info")
    String ticketInfoPage();

}
