package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.ServiceResponseDTO;
import com.aston.intensive.simplespringbootapp.model.ServiceType;
import com.aston.intensive.simplespringbootapp.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/service")
@RestController
@RequiredArgsConstructor
public class ServiceControllerImpl implements ServiceController {
    private final ServiceService serviceService;

    @Override
    public ServiceResponseDTO getServiceById(@PathVariable UUID id) {
        return serviceService.getServiceById(id);
    }

    @Override
    public List<ServiceResponseDTO> getAllServices(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) ServiceType type) {

        return serviceService.getAllServices(pageNumber, pageSize, id, name, description, type);
    }

    @Override
    public long getServiceCount() {
        return serviceService.getServiceCount();
    }

    @Override
    public ServiceResponseDTO createService(@RequestBody ServiceRequestDTO serviceRequestDTO) {
        return serviceService.createService(serviceRequestDTO);
    }

    @Override
    public ServiceResponseDTO updateService(@PathVariable UUID id, @RequestBody ServiceRequestDTO serviceRequestDTO) {
        return serviceService.updateService(id, serviceRequestDTO);
    }

    @Override
    public void deleteService(@PathVariable UUID id) {
        serviceService.deleteService(id);
    }
}
