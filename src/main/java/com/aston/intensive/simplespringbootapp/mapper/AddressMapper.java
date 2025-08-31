package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.address.controller.dto.request.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.address.controller.dto.response.AddressResponseDTO;
import com.aston.intensive.simplespringbootapp.model.Address;
import org.springframework.stereotype.Component;

/**
 * Mapper component for converting between Address entities and DTOs.
 * <p>
 * Used to isolate transformation logic between API layer (DTOs)
 * and persistence layer (entities).
 */
@Component
public class AddressMapper {

    /**
     * Converts an {@link AddressRequestDTO} to an {@link Address} entity.
     *
     * @param addressRequestDTO the DTO containing address data from the client
     * @return a new {@link Address} entity with values mapped from the DTO
     */
    public Address mapToAddress(AddressRequestDTO addressRequestDTO) {
        Address address = new Address();
        address.setBuilding(addressRequestDTO.building());
        address.setCity(addressRequestDTO.city());
        address.setRegion(addressRequestDTO.region());
        address.setStreet(addressRequestDTO.street());
        address.setLatitude(addressRequestDTO.latitude());
        address.setLongitude(addressRequestDTO.longitude());
        return address;
    }

    /**
     * Converts an {@link Address} entity to an {@link AddressResponseDTO}.
     *
     * @param address the entity representing persisted address data
     * @return a DTO with values mapped from the address entity
     */
    public AddressResponseDTO mapToAddressResponseDTO(Address address) {
        return AddressResponseDTO.builder()
                .id(address.getId())
                .building(address.getBuilding())
                .city(address.getCity())
                .region(address.getRegion())
                .street(address.getStreet())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .build();
    }

}
