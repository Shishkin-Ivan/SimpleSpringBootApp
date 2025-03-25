package com.aston.intensive.simplespringbootapp.dto;

import com.aston.intensive.simplespringbootapp.model.AttractionType;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record AttractionResponseDTO(
        UUID id,
        String name,
        String description,
        AttractionType type,
        String address,
        String ticketInfo,
        List<String> services
) {}