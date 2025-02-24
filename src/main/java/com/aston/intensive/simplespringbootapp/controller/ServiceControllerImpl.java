package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ServiceControllerImpl implements ServiceController {
    private final ServiceService serviceService;

    @Override
    public ServiceRequestDTO getServiceById(UUID id) {
        return serviceService.findById(id);
    }
}