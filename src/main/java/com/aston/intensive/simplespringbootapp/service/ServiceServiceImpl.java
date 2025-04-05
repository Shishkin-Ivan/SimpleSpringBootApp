package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.ServiceResponseDTO;
import com.aston.intensive.simplespringbootapp.mapper.ServiceMapper;
import com.aston.intensive.simplespringbootapp.model.Service;
import com.aston.intensive.simplespringbootapp.model.ServiceType;
import com.aston.intensive.simplespringbootapp.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Override
    @Transactional(readOnly = true)
    public ServiceResponseDTO getServiceById(UUID id) {
        log.debug("Find service by id: {}", id);

        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service with id " + id + " not found"));

        return serviceMapper.mapToServiceResponseDTO(service);
    }

    @Override
    public List<ServiceResponseDTO> getAllServices(int pageNumber, int pageSize, UUID id, String name, String description, ServiceType type) {
        log.debug("Find all services with filters");

        List<Service> services = serviceRepository.findByFilters(id, name, description, type);

        return services.stream().skip(pageNumber * pageSize).limit(pageSize)
                .map(serviceMapper::mapToServiceResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public long getServiceCount() {
        log.debug("Counting all services");
        return serviceRepository.count();
    }

    @Override
    @Transactional
    public ServiceResponseDTO createService(ServiceRequestDTO serviceRequestDTO) {
        Service service = serviceMapper.mapToService(serviceRequestDTO);
        serviceRepository.save(service);
        return serviceMapper.mapToServiceResponseDTO(service);
    }

    @Override
    @Transactional
    public ServiceResponseDTO updateService(UUID id, ServiceRequestDTO serviceRequestDTO) {
        Optional<Service> optionalService = serviceRepository.findById(id);
        if (optionalService.isPresent()) {
            Service service = optionalService.get();
            Service updatedService = serviceMapper.mapToService(serviceRequestDTO);

            if(updatedService.getName() != null) {
                service.setName(updatedService.getName());
            }

            if(updatedService.getDescription() != null) {
                service.setDescription(updatedService.getDescription());
            }

            if(updatedService.getType() != null) {
                service.setType(updatedService.getType());
            }
            serviceRepository.save(service);
            return serviceMapper.mapToServiceResponseDTO(service);
        }
        throw new EntityNotFoundException("Service with id " + id + " not found");
    }

    @Override
    @Transactional
    public void deleteService(UUID id) {
        if (!serviceRepository.existsById(id)) {
            throw new EntityNotFoundException("Service with id " + id + " not found");
        }
        serviceRepository.deleteById(id);
    }

}
