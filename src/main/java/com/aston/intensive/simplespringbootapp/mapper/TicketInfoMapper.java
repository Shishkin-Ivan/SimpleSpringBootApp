package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.model.TicketInfo;
import org.springframework.stereotype.Component;

@Component
public class TicketInfoMapper {
    public TicketInfoRequestDTO mapToTicketInfoRequestDTO(TicketInfo ticketInfo) {
        return TicketInfoRequestDTO.builder()
                .price(ticketInfo.getPrice())
                .currency(ticketInfo.getCurrency())
                .availability(ticketInfo.getAvailability())
                .build();
    }
}