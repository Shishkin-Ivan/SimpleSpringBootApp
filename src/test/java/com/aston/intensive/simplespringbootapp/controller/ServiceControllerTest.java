package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.service.controller.ServiceControllerImpl;
import com.aston.intensive.simplespringbootapp.service.controller.dto.request.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.service.controller.dto.response.ServiceResponseDTO;
import com.aston.intensive.simplespringbootapp.model.ServiceType;
import com.aston.intensive.simplespringbootapp.service.service.ServiceService;
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
public class ServiceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ServiceService serviceService;

    @InjectMocks
    private ServiceControllerImpl serviceController;

    private UUID testId;
    private ServiceRequestDTO serviceRequestDTO;
    private ServiceResponseDTO serviceResponseDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(serviceController).build();
        testId = UUID.randomUUID();

        serviceRequestDTO = ServiceRequestDTO.builder()
                .name("Test Name")
                .description("Test Description")
                .type(ServiceType.EDUCATIONAL)
                .build();

        serviceResponseDTO = ServiceResponseDTO.builder()
                .id(testId)
                .name("Test Name")
                .description("Test Description")
                .type(ServiceType.EDUCATIONAL)
                .build();
    }

    @Test
    void testGetServices() throws Exception {
        when(serviceService.getAllServices(Mockito.anyInt(), Mockito.anyInt(), any(), any(), any(), any()))
                .thenReturn(List.of(serviceResponseDTO));

        mockMvc.perform(get("/api/service"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testId.toString()));
    }

    @Test
    void testGetServiceById() throws Exception {
        when(serviceService.getServiceById(testId)).thenReturn(serviceResponseDTO);

        mockMvc.perform(get("/api/service/{id}", testId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()))
                .andExpect(jsonPath("$.name").value("Test Name"));
    }

    @Test
    void testCreateService() throws Exception {
        when(serviceService.createService(any(ServiceRequestDTO.class))).thenReturn(serviceResponseDTO);

        mockMvc.perform(post("/api/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test City\",\"description\":\"Test Street\",\"type\":\"EDUCATIONAL\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()));
    }

    @Test
    void testUpdateService() throws Exception {
        when(serviceService.updateService(any(UUID.class), any(ServiceRequestDTO.class))).thenReturn(serviceResponseDTO);

        mockMvc.perform(put("/api/service/{id}", testId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test City\",\"description\":\"Test Street\",\"type\":\"EDUCATIONAL\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()));
    }

    @Test
    void testDeleteAddress() throws Exception {
        mockMvc.perform(delete("/api/service/{id}", testId))
                .andExpect(status().isOk());
    }

}
