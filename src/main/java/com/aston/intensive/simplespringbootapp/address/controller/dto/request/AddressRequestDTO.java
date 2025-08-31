package com.aston.intensive.simplespringbootapp.address.controller.dto.request;

import lombok.Builder;

/**
 * DTO for providing address data in API requests.
 * <p>
 * Contains information such as building number, street, city, region,
 * and geolocation coordinates (latitude and longitude).
 */
@Builder
public record AddressRequestDTO(
        Integer building,
        String street,
        String city,
        String region,
        Double latitude,
        Double longitude
) {}
