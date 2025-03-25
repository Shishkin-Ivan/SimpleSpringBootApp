package com.aston.intensive.simplespringbootapp.dto;

import lombok.Builder;

import java.util.UUID;

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