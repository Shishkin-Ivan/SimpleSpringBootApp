package com.aston.intensive.simplespringbootapp.service.service;

import com.aston.intensive.simplespringbootapp.service.controller.dto.request.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.service.controller.dto.response.ServiceResponseDTO;
import com.aston.intensive.simplespringbootapp.model.ServiceType;

import java.util.List;
import java.util.UUID;

/**
 * Interface for managing services.
 * Provides core operations such as creating, updating, deleting, and retrieving services.
 */
public interface ServiceService {

    /**
     * Retrieves a service by its unique identifier.
     *
     * @param id the unique identifier of the service to retrieve.
     * @return a DTO object containing the service information.
     * @throws EntityNotFoundException if no service is found with the given ID.
     */
    ServiceResponseDTO getServiceById(UUID id);

    /**
     * Retrieves a list of services with optional filters, paginated.
     *
     * @param pageNumber the page number.
     * @param pageSize the page size.
     * @param id an optional filter for the service ID.
     * @param name an optional filter for the service name.
     * @param description an optional filter for the service description.
     * @param type an optional filter for the service type.
     * @return a list of service DTOs matching the filters, paginated.
     */
    List<ServiceResponseDTO> getAllServices(int pageNumber, int pageSize, UUID id, String name, String description, ServiceType type);

    /**
     * Returns the total count of services.
     *
     * @return the total number of services.
     */
    long getServiceCount();

    /**
     * Creates a new service based on the provided data.
     *
     * @param serviceRequestDTO the DTO containing data for the new service.
     * @return a DTO representing the created service.
     */
    ServiceResponseDTO createService(ServiceRequestDTO serviceRequestDTO);

    /**
     * Updates an existing service identified by its ID.
     *
     * @param id the unique identifier of the service to update.
     * @param serviceRequestDTO the DTO containing updated data for the service.
     * @return a DTO containing the updated service information.
     * @throws EntityNotFoundException if no service is found with the given ID.
     */
    ServiceResponseDTO updateService(UUID id, ServiceRequestDTO serviceRequestDTO);

    /**
     * Delete a service by ID.
     *
     * @param id the service ID to delete.
     * @throws EntityNotFoundException if the service with the given ID is not found.
     */
    void deleteService(UUID id);

}
