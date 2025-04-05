package com.aston.intensive.simplespringbootapp.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttractionTest {

    @Test
    void testCreateAttraction() {
        UUID uuid = UUID.randomUUID();

        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setPrice(9999.99);
        ticketInfo.setCurrency("RUB");
        ticketInfo.setAvailability(true);

        Address address = new Address();
        address.setCity("San Francisco");
        address.setStreet("Main Street");
        address.setBuilding(12);
        address.setRegion("SF");
        address.setLongitude(5656.28);
        address.setLatitude(7878.852);

        Attraction attraction = new Attraction();
        attraction.setId(uuid);
        attraction.setName("Name Test");
        attraction.setDescription("Description Test");
        attraction.setType(AttractionType.CULTURAL);
        attraction.setAddress(address);
        attraction.setTicketInfo(ticketInfo);

        assertEquals(uuid, attraction.getId());
        assertEquals("Name Test", attraction.getName());
        assertEquals("Description Test", attraction.getDescription());
        assertEquals(AttractionType.CULTURAL.ordinal(), attraction.getType().ordinal());

        assertEquals(address.getCity(), attraction.getAddress().getCity());
        assertEquals(address.getStreet(), attraction.getAddress().getStreet());
        assertEquals(address.getBuilding(), attraction.getAddress().getBuilding());

        assertEquals(ticketInfo.getPrice(), attraction.getTicketInfo().getPrice());
        assertEquals(ticketInfo.getCurrency(), attraction.getTicketInfo().getCurrency());
    }

}
