package com.aston.intensive.simplespringbootapp.ticket_info.controller.dto.response;

import lombok.Builder;

import java.util.UUID;

/**
 * DTO for returning ticket info data in API responses.
 * <p>
 * Contains information such as price, currency, and availability.
 */
@Builder
public record TicketInfoResponseDTO(
        UUID id,
        Double price,
        String currency,
        Boolean availability
) {}
