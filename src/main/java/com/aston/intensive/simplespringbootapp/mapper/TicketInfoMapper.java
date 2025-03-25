package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.TicketInfoResponseDTO;
import com.aston.intensive.simplespringbootapp.model.TicketInfo;
import org.springframework.stereotype.Component;

@Component
public class TicketInfoMapper {

    public TicketInfo mapToTicketInfo(TicketInfoRequestDTO ticketInfoRequestDTO) {
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setPrice(ticketInfoRequestDTO.price());
        ticketInfo.setCurrency(ticketInfoRequestDTO.currency());
        ticketInfo.setAvailability(ticketInfoRequestDTO.availability());
        return ticketInfo;
    }

    public TicketInfoResponseDTO mapToTicketInfoResponseDTO(TicketInfo ticketInfo) {
        return TicketInfoResponseDTO.builder()
                .id(ticketInfo.getId())
                .price(ticketInfo.getPrice())
                .currency(ticketInfo.getCurrency())
                .availability(ticketInfo.getAvailability())
                .build();
    }
}
