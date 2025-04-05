package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.model.Address;
import com.aston.intensive.simplespringbootapp.model.AttractionType;
import com.aston.intensive.simplespringbootapp.model.TicketInfo;
import com.aston.intensive.simplespringbootapp.service.AttractionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AttractionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AttractionService attractionService;

    @InjectMocks
    private AttractionControllerImpl attractionController;

    private UUID testId;
    private UUID ticketInfoId;
    private UUID addressId;
    private AttractionRequestDTO attractionRequestDTO;
    private AttractionResponseDTO attractionResponseDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(attractionController).build();
        testId = UUID.randomUUID();

        ticketInfoId = UUID.randomUUID();
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setId(ticketInfoId);
        ticketInfo.setPrice(9999.99);
        ticketInfo.setCurrency("RUB");
        ticketInfo.setAvailability(true);

        addressId = UUID.randomUUID();
        Address address = new Address();
        address.setId(addressId);
        address.setCity("San Francisco");
        address.setStreet("Main Street");
        address.setBuilding(12);
        address.setRegion("SF");
        address.setLongitude(5656.28);
        address.setLatitude(7878.852);

        attractionRequestDTO = AttractionRequestDTO.builder()
                .id(ticketInfoId)
                .name("Name Test")
                .description("Description Test")
                .type(AttractionType.CULTURAL)
                .ticketInfoId(ticketInfoId.toString())
                .addressId(addressId.toString())
                .build();

        attractionResponseDTO = AttractionResponseDTO.builder()
                .id(testId)
                .name("Name Test")
                .description("Description Test")
                .type(AttractionType.CULTURAL)
                .ticketInfo(ticketInfo.getPrice() + " " + ticketInfo.getCurrency())
                .address(address.getBuilding() + " " + address.getStreet() + ", " + address.getCity())
                .build();
    }

    @Test
    void testGetAttractions() throws Exception {
        when(attractionService.getAllAttractions(Mockito.anyInt(), Mockito.anyInt(), any(), any(), any(), any()))
                .thenReturn(List.of(attractionResponseDTO));

        mockMvc.perform(get("/api/attraction"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testId.toString()));
    }

    @Test
    void testGetAttractionById() throws Exception {
        when(attractionService.getAttractionById(testId))
                .thenReturn(attractionResponseDTO);

        mockMvc.perform(get("/api/attraction/{id}", testId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()))
                .andExpect(jsonPath("$.name").value("Name Test"));
    }

    @Test
    void testCreateAttraction() throws Exception {
        when(attractionService.createAttraction(any(AttractionRequestDTO.class)))
                .thenReturn(attractionResponseDTO);

        mockMvc.perform(post("/api/attraction")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Name Test\",\"description\":\"Description Test\",\"type\":\"CULTURAL\", \"addressId\":\""
                        + addressId.toString() + "\", \"ticketInfoId\":\"" + ticketInfoId.toString() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()));
    }

    @Test
    void testUpdateAttraction() throws Exception {
        when(attractionService.updateAttraction(any(UUID.class), any(AttractionRequestDTO.class)))
                .thenReturn(attractionResponseDTO);

        mockMvc.perform(put("/api/attraction/{id}", testId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Name Test\",\"description\":\"Description Test\",\"type\":\"CULTURAL\", \"addressId\":\""
                        + addressId.toString() + "\", \"ticketInfoId\":\"" + ticketInfoId.toString() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()));
    }

    @Test
    void testDeleteAttraction() throws Exception {
        mockMvc.perform(delete("/api/attraction/{id}", testId))
                .andExpect(status().isOk());
    }

}
