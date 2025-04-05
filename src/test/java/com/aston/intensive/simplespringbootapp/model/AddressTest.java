package com.aston.intensive.simplespringbootapp.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    @Test
    void testCreateAddress() {
        UUID uuid = UUID.randomUUID();

        Address address = new Address();
        address.setId(uuid);
        address.setCity("San Francisco");
        address.setStreet("Main Street");
        address.setBuilding(12);
        address.setRegion("SF");
        address.setLongitude(5656.28);
        address.setLatitude(7878.852);

        assertEquals(uuid, address.getId());
        assertEquals("San Francisco", address.getCity());
        assertEquals("Main Street", address.getStreet());
        assertEquals(12, address.getBuilding());
        assertEquals("SF", address.getRegion());
        assertEquals(5656.28, address.getLongitude(), 0);
        assertEquals(7878.852, address.getLatitude(), 0);
    }

}
