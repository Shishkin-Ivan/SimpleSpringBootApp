package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.ServiceResponseDTO;
import com.aston.intensive.simplespringbootapp.model.ServiceType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST API for managing services.
 * Provides endpoints to perform CRUD operations and search/filtering.
 */
public interface ServiceController {

    /**
     * Get service by its unique ID.
     *
     * @param id UUID of the service.
     * @return the service details.
     */
    @GetMapping("/{id}")
    ServiceResponseDTO getServiceById(@PathVariable UUID id);

    /**
     * Get a paginated list of services, optionally filtered by parameters.
     *
     * @param pageNumber page number for pagination (default is 0).
     * @param pageSize page size for pagination (default is Integer.MAX_VALUE).
     * @param id filter by service ID (optional).
     * @param name filter by name (optional).
     * @param description filter by description (optional).
     * @param type filter by type (optional).
     * @return list of matching services.
     */
    @GetMapping
    List<ServiceResponseDTO> getAllServices(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) ServiceType type
    );

    /**
     * Get total count of services in the system.
     *
     * @return total service count.
     */
    @GetMapping("/count")
    long getServiceCount();

    /**
     * Create a new service.
     *
     * @param serviceRequestDTO DTO containing service data.
     * @return created service.
     */
    @PostMapping
    ServiceResponseDTO createService(@RequestBody ServiceRequestDTO serviceRequestDTO);

    /**
     * Update an existing service by ID.
     *
     * @param id UUID of the service to update.
     * @param serviceRequestDTO new service data.
     * @return updated service.
     */
    @PutMapping("/{id}")
    ServiceResponseDTO updateService(@PathVariable UUID id, @RequestBody ServiceRequestDTO serviceRequestDTO);

    /**
     * Delete a service by ID.
     *
     * @param id UUID of the service to delete.
     */
    @DeleteMapping("/{id}")
    void deleteService(@PathVariable UUID id);

}
