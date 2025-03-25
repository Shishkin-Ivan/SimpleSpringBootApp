package com.aston.intensive.simplespringbootapp.dto;

import com.aston.intensive.simplespringbootapp.model.AttractionType;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AttractionRequestDTO(
        UUID id,
        String name,
        String description,
        AttractionType type,
        String addressId,
        String ticketInfoId
) {}
