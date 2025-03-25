package com.aston.intensive.simplespringbootapp.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record TicketInfoResponseDTO(
        UUID id,
        Double price,
        String currency,
        Boolean availability
) {}