package com.aston.intensive.simplespringbootapp.repository;

import com.aston.intensive.simplespringbootapp.TestcontainersConfiguration;
import com.aston.intensive.simplespringbootapp.model.Service;
import com.aston.intensive.simplespringbootapp.model.ServiceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfiguration.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ServiceRepositoryTest {

    @Autowired
    private ServiceRepository serviceRepository;

    @Test
    void testFindById_Found() {
        Service service = new Service();
        service.setName("Test Name");
        service.setDescription("Test Description");
        service.setType(ServiceType.FOOD);
        serviceRepository.save(service);

        Optional<Service> serviceOptional = serviceRepository.findById(service.getId());
        assertTrue(serviceOptional.isPresent());
        assertEquals("Test Name", serviceOptional.get().getName());
        assertEquals("Test Description", serviceOptional.get().getDescription());
        assertEquals(ServiceType.FOOD, serviceOptional.get().getType());
    }

    @Test
    void testFindByFilters_NoFilters_ReturnsAll() {
        Service service = new Service();
        service.setName("Test Name");
        service.setDescription("Test Description");
        service.setType(ServiceType.FOOD);
        serviceRepository.save(service);

        Service service2 = new Service();
        service2.setName("Test Name 2");
        service2.setDescription("Test Description 2");
        service2.setType(ServiceType.GUIDE);
        serviceRepository.save(service2);

        Service service3 = new Service();
        service3.setName("Test Name 3");
        service3.setDescription("Test Description 3");
        service3.setType(ServiceType.TRANSPORT);
        serviceRepository.save(service3);

        List<Service> result = serviceRepository.findByFilters(null, null, null, null);
        assertEquals(3, result.size());
    }

}
