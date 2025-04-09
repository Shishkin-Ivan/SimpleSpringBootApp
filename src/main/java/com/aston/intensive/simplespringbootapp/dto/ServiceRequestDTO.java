package com.aston.intensive.simplespringbootapp.dto;

import com.aston.intensive.simplespringbootapp.model.ServiceType;
import lombok.Builder;

/**
 * DTO for providing service data in API requests.
 * <p>
 * Contains information such as name, description, and type.
 */
@Builder
public record ServiceRequestDTO(
        String name,
        String description,
        ServiceType type
) {}
