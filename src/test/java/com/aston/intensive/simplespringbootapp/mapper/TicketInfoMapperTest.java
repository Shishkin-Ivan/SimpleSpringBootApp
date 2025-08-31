package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.ticket_info.controller.dto.request.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.ticket_info.controller.dto.response.TicketInfoResponseDTO;
import com.aston.intensive.simplespringbootapp.model.TicketInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicketInfoMapperTest {

    private TicketInfoMapper ticketInfoMapper;

    @BeforeEach
    void setUp() {
        ticketInfoMapper = new TicketInfoMapper();
    }

    @Test
    void testMapToTicketInfo() {
        TicketInfoRequestDTO ticketInfoRequestDTO = TicketInfoRequestDTO.builder()
                .price(399.26)
                .currency("CNY")
                .availability(true)
                .build();

        TicketInfo ticketInfo = ticketInfoMapper.mapToTicketInfo(ticketInfoRequestDTO);

        assertNotNull(ticketInfo);
        assertEquals(ticketInfoRequestDTO.price(), ticketInfo.getPrice());
        assertEquals(ticketInfo.getCurrency(), ticketInfo.getCurrency());
        assertEquals(ticketInfo.getAvailability(), ticketInfo.getAvailability());
    }

    @Test
    void testMapToTicketInfoDTO() {
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setPrice(4600.5);
        ticketInfo.setCurrency("RUB");
        ticketInfo.setAvailability(false);

        TicketInfoResponseDTO ticketInfoResponseDTO = ticketInfoMapper.mapToTicketInfoResponseDTO(ticketInfo);

        assertNotNull(ticketInfoResponseDTO);
        assertEquals(ticketInfo.getPrice(), ticketInfoResponseDTO.price());
        assertEquals(ticketInfo.getCurrency(), ticketInfoResponseDTO.currency());
        assertEquals(ticketInfo.getAvailability(), ticketInfoResponseDTO.availability());
    }

}
