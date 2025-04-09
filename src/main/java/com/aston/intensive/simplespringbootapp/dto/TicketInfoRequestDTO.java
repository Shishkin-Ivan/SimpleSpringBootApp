package com.aston.intensive.simplespringbootapp.dto;

import lombok.Builder;

/**
 * DTO for providing ticket info data in API requests.
 * <p>
 * Contains information such as price, currency, and availability.
 */
@Builder
public record TicketInfoRequestDTO(
        Double price,
        String currency,
        Boolean availability
) {}
