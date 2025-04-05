package com.aston.intensive.simplespringbootapp.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FrontControllerImpl.class)
public class FrontControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testAddressPage() throws Exception {
        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(view().name("address"));
    }

    @Test
    void testAttractionPage() throws Exception {
        mockMvc.perform(get("/attraction"))
                .andExpect(status().isOk())
                .andExpect(view().name("attraction"));
    }

    @Test
    void testServicePage() throws Exception {
        mockMvc.perform(get("/service"))
                .andExpect(status().isOk())
                .andExpect(view().name("service"));
    }

    @Test
    void testTicketInfoPage() throws Exception {
        mockMvc.perform(get("/ticket-info"))
                .andExpect(status().isOk())
                .andExpect(view().name("ticket-info"));
    }

}
