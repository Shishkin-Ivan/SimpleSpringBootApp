package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.dto.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.ServiceResponseDTO;
import com.aston.intensive.simplespringbootapp.model.Service;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    public Service mapToService(ServiceRequestDTO dto) {
        Service service = new Service();
        service.setName(dto.name());
        service.setDescription(dto.description());
        service.setType(dto.type());
        return service;
    }

    public ServiceResponseDTO mapToServiceResponseDTO(Service service) {
        return ServiceResponseDTO.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .type(service.getType() != null ?
                        service.getType().name().substring(0, 1).toUpperCase() + service.getType().name().substring(1).toLowerCase() : null)
                .build();
    }
}
