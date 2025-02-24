package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.mapper.ServiceMapper;
import com.aston.intensive.simplespringbootapp.model.Service;
import com.aston.intensive.simplespringbootapp.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public ServiceRequestDTO findById(UUID id) {
        log.debug("Find service by id: {}", id);

        Optional<Service> optionalService = serviceRepository.findById(id);
        if (optionalService.isPresent()) {
            Service service = optionalService.get();
            return serviceMapper.mapToServiceRequestDTO(service);
        }
        log.debug("No service found with id: {}", id);
        return null;
    }
}