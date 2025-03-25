package com.aston.intensive.simplespringbootapp.dto;

import com.aston.intensive.simplespringbootapp.model.ServiceType;
import lombok.Builder;

@Builder
public record ServiceRequestDTO(
        String name,
        String description,
        ServiceType type
) {}
