package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.ServiceResponseDTO;
import com.aston.intensive.simplespringbootapp.model.ServiceType;

import java.util.List;
import java.util.UUID;

public interface ServiceService {

    ServiceResponseDTO getServiceById(UUID id);

    List<ServiceResponseDTO> getAllServices(int pageNumber, int pageSize, UUID id, String name, String description, ServiceType type);

    long getServiceCount();

    ServiceResponseDTO createService(ServiceRequestDTO serviceRequestDTO);

    ServiceResponseDTO updateService(UUID id, ServiceRequestDTO serviceRequestDTO);

    void deleteService(UUID id);
}
