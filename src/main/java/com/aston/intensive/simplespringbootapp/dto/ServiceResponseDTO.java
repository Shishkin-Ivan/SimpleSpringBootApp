package com.aston.intensive.simplespringbootapp.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ServiceResponseDTO(
        UUID id,
        String name,
        String description,
        String type
) {}