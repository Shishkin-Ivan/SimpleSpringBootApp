package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.ServiceResponseDTO;
import com.aston.intensive.simplespringbootapp.model.ServiceType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ServiceController {

    @GetMapping("/{id}")
    ServiceResponseDTO getServiceById(@PathVariable UUID id);

    @GetMapping
    List<ServiceResponseDTO> getAllServices(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) ServiceType type
    );

    @GetMapping("/count")
    long getServiceCount();

    @PostMapping
    ServiceResponseDTO createService(@RequestBody ServiceRequestDTO serviceRequestDTO);

    @PutMapping("/{id}")
    ServiceResponseDTO updateService(@PathVariable UUID id, @RequestBody ServiceRequestDTO serviceRequestDTO);

    @DeleteMapping("/{id}")
    void deleteService(@PathVariable UUID id);
}
