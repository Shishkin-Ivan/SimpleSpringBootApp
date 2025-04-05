package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.ServiceResponseDTO;
import com.aston.intensive.simplespringbootapp.mapper.ServiceMapper;
import com.aston.intensive.simplespringbootapp.model.Service;
import com.aston.intensive.simplespringbootapp.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceServiceTest {

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ServiceMapper serviceMapper;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    private UUID serviceId = UUID.randomUUID();
    private Service service;
    private ServiceRequestDTO serviceRequestDTO;
    private ServiceResponseDTO serviceResponseDTO;

    @BeforeEach
    void setUp() {
        serviceId = UUID.randomUUID();
        service = new Service();
        service.setId(serviceId);
        service.setName("Test Name");
        service.setDescription("Test Description");

        serviceRequestDTO = ServiceRequestDTO.builder()
                .name("Updated Name")
                .description("Updated Description")
                .build();

        serviceResponseDTO = ServiceResponseDTO.builder()
                .id(serviceId)
                .name("Test Name")
                .description("Test Description")
                .build();
    }

    @Test
    void testGetServiceById_Found() {
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(service));
        when(serviceMapper.mapToServiceResponseDTO(service)).thenReturn(serviceResponseDTO);

        ServiceResponseDTO result = serviceService.getServiceById(serviceId);
        assertNotNull(result);
        assertEquals(serviceId, result.id());
        verify(serviceRepository).findById(serviceId);
    }

    @Test
    void testGetServiceById_NotFound() {
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> serviceService.getServiceById(serviceId));
    }

    @Test
    void testGetAllServices(){
        List<Service> services = List.of(service);
        when(serviceRepository.findByFilters(any(), any(), any(), any()))
                .thenReturn(services);
        when(serviceMapper.mapToServiceResponseDTO(service)).thenReturn(serviceResponseDTO);

        List<ServiceResponseDTO> result = serviceService.getAllServices(0, 7, null, null, null, null);
        assertEquals(1, result.size());
        verify(serviceRepository).findByFilters(any(), any(), any(), any());
    }

    @Test
    void testGetServicesCount(){
        when(serviceRepository.count()).thenReturn(468L);
        assertEquals(468L, serviceService.getServiceCount());
    }

    @Test
    void testCreateService(){
        when(serviceMapper.mapToService(serviceRequestDTO)).thenReturn(service);
        when(serviceRepository.save(service)).thenReturn(service);
        when(serviceMapper.mapToServiceResponseDTO(service)).thenReturn(serviceResponseDTO);

        ServiceResponseDTO result = serviceService.createService(serviceRequestDTO);
        assertNotNull(result);
        assertEquals(serviceId, result.id());
    }

    @Test
    void testUpdateServiceById_Found() {
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(service));
        when(serviceMapper.mapToService(serviceRequestDTO)).thenReturn(service);
        when(serviceRepository.save(service)).thenReturn(service);
        when(serviceMapper.mapToServiceResponseDTO(service)).thenReturn(serviceResponseDTO);

        ServiceResponseDTO result = serviceService.updateService(serviceId, serviceRequestDTO);
        assertNotNull(result);
        assertEquals(serviceId, result.id());
    }

    @Test
    void testUpdateServiceById_NotFound() {
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> serviceService.updateService(serviceId, serviceRequestDTO));
    }

    @Test
    void testDeleteService_Found() {
        when(serviceRepository.existsById(serviceId)).thenReturn(true);
        doNothing().when(serviceRepository).deleteById(serviceId);

        serviceService.deleteService(serviceId);

        verify(serviceRepository).existsById(serviceId);
        verify(serviceRepository).deleteById(serviceId);
    }

    @Test
    void testDeleteService_NotFound() {
        when(serviceRepository.existsById(serviceId)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> serviceService.deleteService(serviceId));
    }

}
