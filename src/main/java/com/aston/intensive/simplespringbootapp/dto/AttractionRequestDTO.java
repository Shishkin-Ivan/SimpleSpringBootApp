package com.aston.intensive.simplespringbootapp.dto;

import com.aston.intensive.simplespringbootapp.model.AttractionType;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

/**
 * DTO for providing attraction data in API requests.
 * <p>
 * Contains information such as name, description, type, addressId,
 * ticketInfoId, and list of serviceIds.
 */
@Builder
public record AttractionRequestDTO(
        UUID id,
        String name,
        String description,
        AttractionType type,
        String addressId,
        String ticketInfoId,
        List<String> serviceIds
) {}
