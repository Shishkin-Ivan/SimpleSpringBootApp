package com.aston.intensive.simplespringbootapp.address.controller.dto.response;

import lombok.Builder;

import java.util.UUID;

/**
 * DTO for returning address data in API responses.
 * <p>
 * Contains information such as the address ID, building number, street name,
 * city, region, latitude, and longitude.
 */
@Builder
public record AddressResponseDTO(
        UUID id,
        Integer building,
        String street,
        String city,
        String region,
        Double latitude,
        Double longitude
) {}
