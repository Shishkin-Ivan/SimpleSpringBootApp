package com.aston.intensive.simplespringbootapp.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTest {

    @Test
    void testCreateService() {
        UUID uuid = UUID.randomUUID();

        Service service = new Service();
        service.setId(uuid);
        service.setName("Test Name");
        service.setDescription("Test Description");
        service.setType(ServiceType.EDUCATIONAL);

        assertEquals(uuid, service.getId());
        assertEquals("Test Name", service.getName());
        assertEquals("Test Description", service.getDescription());
        assertEquals(ServiceType.EDUCATIONAL.ordinal(), service.getType().ordinal());
    }

}
