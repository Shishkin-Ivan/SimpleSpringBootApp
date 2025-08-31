package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.ticket_info.controller.dto.request.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.ticket_info.controller.dto.response.TicketInfoResponseDTO;
import com.aston.intensive.simplespringbootapp.model.TicketInfo;
import org.springframework.stereotype.Component;

/**
 * Mapper component for converting between TicketInfo entities and DTOs.
 * <p>
 * Used to isolate transformation logic between API layer (DTOs)
 * and persistence layer (entities).
 */
@Component
public class TicketInfoMapper {

    /**
     * Converts a {@link TicketInfoRequestDTO} to a {@link TicketInfo} entity.
     *
     * @param ticketInfoRequestDTO the DTO containing ticket info data from the client
     * @return a new {@link TicketInfo} entity with values mapped from the DTO
     */
    public TicketInfo mapToTicketInfo(TicketInfoRequestDTO ticketInfoRequestDTO) {
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setPrice(ticketInfoRequestDTO.price());
        ticketInfo.setCurrency(ticketInfoRequestDTO.currency());
        ticketInfo.setAvailability(ticketInfoRequestDTO.availability());
        return ticketInfo;
    }

    /**
     * Converts a {@link TicketInfo} entity to a {@link TicketInfoResponseDTO}.
     *
     * @param ticketInfo the entity representing persisted ticket info data
     * @return a DTO with values mapped from the ticket info entity
     */
    public TicketInfoResponseDTO mapToTicketInfoResponseDTO(TicketInfo ticketInfo) {
        return TicketInfoResponseDTO.builder()
                .id(ticketInfo.getId())
                .price(ticketInfo.getPrice())
                .currency(ticketInfo.getCurrency())
                .availability(ticketInfo.getAvailability())
                .build();
    }

}
