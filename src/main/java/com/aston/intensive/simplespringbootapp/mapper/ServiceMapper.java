package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.dto.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.model.Service;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {
    public ServiceRequestDTO mapToServiceRequestDTO(Service service) {
        return ServiceRequestDTO.builder()
                .name(service.getName())
                .description(service.getDescription())
                .serviceType(service.getType())
                .build();
    }
}