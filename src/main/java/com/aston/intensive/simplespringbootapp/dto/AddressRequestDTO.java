package com.aston.intensive.simplespringbootapp.dto;

import lombok.Builder;

@Builder
public record AddressRequestDTO(
        Integer building,
        String street,
        String city,
        String region
) {}
