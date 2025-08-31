package com.aston.intensive.simplespringbootapp.service.controller.dto.response;

import com.aston.intensive.simplespringbootapp.model.ServiceType;
import lombok.Builder;

import java.util.UUID;

/**
 * DTO for returning service data in API responses.
 * <p>
 * Contains information such as name, description, and type.
 */
@Builder
public record ServiceResponseDTO(
        UUID id,
        String name,
        String description,
        ServiceType type
) {}
