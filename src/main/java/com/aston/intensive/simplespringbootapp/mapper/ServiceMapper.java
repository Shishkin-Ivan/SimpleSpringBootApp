package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.dto.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.ServiceResponseDTO;
import com.aston.intensive.simplespringbootapp.model.Service;
import org.springframework.stereotype.Component;

/**
 * Mapper component for converting between Service entities and DTOs.
 * <p>
 * Used to isolate transformation logic between API layer (DTOs)
 * and persistence layer (entities).
 */
@Component
public class ServiceMapper {

    /**
     * Converts a {@link ServiceRequestDTO} to a {@link Service} entity.
     *
     * @param serviceRequestDTO the DTO containing service data from the client
     * @return a new {@link Service} entity with values mapped from the DTO
     */
    public Service mapToService(ServiceRequestDTO serviceRequestDTO) {
        Service service = new Service();
        service.setName(serviceRequestDTO.name());
        service.setDescription(serviceRequestDTO.description());
        service.setType(serviceRequestDTO.type());
        return service;
    }

    /**
     * Converts a {@link Service} entity to a {@link ServiceResponseDTO}.
     *
     * @param service the entity representing persisted service data
     * @return a DTO with values mapped from the service entity
     */
    public ServiceResponseDTO mapToServiceResponseDTO(Service service) {
        return ServiceResponseDTO.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .type(service.getType())
                .build();
    }

}
