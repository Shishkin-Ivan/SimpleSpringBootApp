package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.service.controller.dto.request.ServiceRequestDTO;
import com.aston.intensive.simplespringbootapp.service.controller.dto.response.ServiceResponseDTO;
import com.aston.intensive.simplespringbootapp.model.Service;
import com.aston.intensive.simplespringbootapp.model.ServiceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceMapperTest {

    private ServiceMapper serviceMapper;

    @BeforeEach
    void setUp() {
        serviceMapper = new ServiceMapper();
    }

    @Test
    void testMapToService() {
        ServiceRequestDTO serviceRequestDTO = ServiceRequestDTO.builder()
                .name("Name Test")
                .description("Description test")
                .type(ServiceType.TRANSPORT)
                .build();

        Service service = serviceMapper.mapToService(serviceRequestDTO);

        assertNotNull(service);
        assertEquals(serviceRequestDTO.name(), serviceRequestDTO.name());
        assertEquals(serviceRequestDTO.description(), serviceRequestDTO.description());
        assertEquals(serviceRequestDTO.type().ordinal(), serviceRequestDTO.type().ordinal());

    }

    @Test
    void testMapServiceResponseDTO() {
        Service service = new Service();
        service.setName("Name Test");
        service.setDescription("Description test");
        service.setType(ServiceType.TRANSPORT);

        ServiceResponseDTO serviceResponseDTO = serviceMapper.mapToServiceResponseDTO(service);

        assertNotNull(serviceResponseDTO);
        assertEquals(service.getName(), serviceResponseDTO.name());
        assertEquals(service.getDescription(), serviceResponseDTO.description());
        assertEquals(serviceResponseDTO.type().ordinal(), serviceResponseDTO.type().ordinal());
    }

}
