package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.ticket_info.controller.TicketInfoControllerImpl;
import com.aston.intensive.simplespringbootapp.ticket_info.controller.dto.request.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.ticket_info.controller.dto.response.TicketInfoResponseDTO;
import com.aston.intensive.simplespringbootapp.ticket_info.service.TicketInfoService;
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
public class TicketInfoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TicketInfoService ticketInfoService;

    @InjectMocks
    private TicketInfoControllerImpl ticketInfoController;

    private UUID testId;
    private TicketInfoRequestDTO ticketInfoRequestDTO;
    private TicketInfoResponseDTO ticketInfoResponseDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketInfoController).build();
        testId = UUID.randomUUID();

        ticketInfoRequestDTO = TicketInfoRequestDTO.builder()
                .price(99999.99)
                .currency("RUB")
                .availability(true)
                .build();
        ticketInfoResponseDTO = TicketInfoResponseDTO.builder()
                .id(testId)
                .price(99999.99)
                .currency("RUB")
                .availability(true)
                .build();
    }

    @Test
    void testGetTicketsInfo() throws Exception {
        when(ticketInfoService.getAllTicketsInfo(Mockito.anyInt(), Mockito.anyInt(), any(), any(), any(), any()))
                .thenReturn(List.of(ticketInfoResponseDTO));

        mockMvc.perform(get("/api/ticket-info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testId.toString()));
    }

    @Test
    void testGetTicketInfoById() throws Exception {
        when(ticketInfoService.getTicketInfoById(testId))
                .thenReturn(ticketInfoResponseDTO);

        mockMvc.perform(get("/api/ticket-info/{id}", testId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()))
                .andExpect(jsonPath("$.price").value(99999.99));
    }

    @Test
    void testCreateTicketInfo() throws Exception {
        when(ticketInfoService.createTicketInfo(any(TicketInfoRequestDTO.class)))
                .thenReturn(ticketInfoResponseDTO);

        mockMvc.perform(post("/api/ticket-info")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"price\":99999.99,\"currency\":\"RUB\",\"availability\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()));
    }

    @Test
    void testUpdateTicketInfo() throws Exception {
        when(ticketInfoService.updateTicketInfo(any(UUID.class), any(TicketInfoRequestDTO.class)))
                .thenReturn(ticketInfoResponseDTO);

        mockMvc.perform(put("/api/ticket-info/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"price\":99999.99,\"currency\":\"RUB\",\"availability\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()));
    }

    @Test
    void testDeleteAddress() throws Exception {
        mockMvc.perform(delete("/api/ticket-info/{id}", testId))
                .andExpect(status().isOk());
    }

}
