package com.aston.intensive.simplespringbootapp.dto;

import lombok.Builder;

@Builder
public record TicketInfoRequestDTO(
        Double price,
        String currency,
        Boolean availability
) {}