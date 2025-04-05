package com.aston.intensive.simplespringbootapp.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketInfoTest {

    @Test
    void testCreateTicketInfo() {
        UUID uuid = UUID.randomUUID();

        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setId(uuid);
        ticketInfo.setPrice(9999.99);
        ticketInfo.setCurrency("RUB");
        ticketInfo.setAvailability(true);

        assertEquals(uuid, ticketInfo.getId());
        assertEquals(9999.99, ticketInfo.getPrice());
        assertEquals("RUB", ticketInfo.getCurrency());
        assertEquals(true, ticketInfo.getAvailability());
    }

}
