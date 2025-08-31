package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.address.controller.AddressControllerImpl;
import com.aston.intensive.simplespringbootapp.address.controller.dto.request.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.address.controller.dto.response.AddressResponseDTO;
import com.aston.intensive.simplespringbootapp.address.service.AddressService;
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
public class AddressControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressControllerImpl addressController;

    private UUID testId;
    private AddressRequestDTO addressRequestDTO;
    private AddressResponseDTO addressResponseDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();

        testId = UUID.randomUUID();

        addressRequestDTO = AddressRequestDTO.builder()
                .city("Test City")
                .street("Test Street")
                .building(10)
                .latitude(10.0)
                .longitude(20.0)
                .build();

        addressResponseDTO = AddressResponseDTO.builder()
                .id(testId)
                .city("Test City")
                .street("Test Street")
                .building(10)
                .latitude(10.0)
                .longitude(20.0)
                .build();
    }

    @Test
    void testGetAddressById() throws Exception {
        when(addressService.getAddressById(testId)).thenReturn(addressResponseDTO);

        mockMvc.perform(get("/api/address/{id}", testId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()))
                .andExpect(jsonPath("$.city").value("Test City"));
    }

    @Test
    void testGetAddresses() throws Exception {
        when(addressService.getAllAddresses(Mockito.anyInt(), Mockito.anyInt(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(List.of(addressResponseDTO));

        mockMvc.perform(get("/api/address"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testId.toString()));
    }

    @Test
    void testCreateAddress() throws Exception {
        when(addressService.createAddress(any(AddressRequestDTO.class))).thenReturn(addressResponseDTO);

        mockMvc.perform(post("/api/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"city\":\"Test City\",\"street\":\"Test Street\",\"building\":10,\"region\":\"Test Region\",\"latitude\":10.0,\"longitude\":20.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()));
    }

    @Test
    void testUpdateAddress() throws Exception {
        when(addressService.updateAddress(any(UUID.class), any(AddressRequestDTO.class))).thenReturn(addressResponseDTO);

        mockMvc.perform(put("/api/address/{id}", testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"city\":\"Test City\",\"street\":\"Test Street\",\"building\":10,\"region\":\"Test Region\",\"latitude\":10.0,\"longitude\":20.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testId.toString()));
    }

    @Test
    void testDeleteAddress() throws Exception {
        mockMvc.perform(delete("/api/address/{id}", testId))
                .andExpect(status().isOk());
    }

}
