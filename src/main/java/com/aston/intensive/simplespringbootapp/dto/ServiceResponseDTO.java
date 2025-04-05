package com.aston.intensive.simplespringbootapp.dto;

import com.aston.intensive.simplespringbootapp.model.ServiceType;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ServiceResponseDTO(
        UUID id,
        String name,
        String description,
        ServiceType type
) {}