package com.aston.intensive.simplespringbootapp.controller;

import org.springframework.stereotype.Controller;

/**
 * Implementation of {@link FrontController} that maps frontend routes
 * to their corresponding HTML templates.
 */
@Controller
public class FrontControllerImpl implements FrontController {

    /**
     * {@inheritDoc}
     */
    @Override
    public String homePage() {
        return "index";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addressPage() {
        return "address";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String attractionPage() {
        return "attraction";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String servicePage() {
        return "service";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String ticketInfoPage() {
        return "ticket-info";
    }

}
