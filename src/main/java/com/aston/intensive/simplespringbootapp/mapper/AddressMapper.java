package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.dto.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AddressResponseDTO;
import com.aston.intensive.simplespringbootapp.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address mapToAddress(AddressRequestDTO dto) {
        Address address = new Address();
        address.setBuilding(dto.building());
        address.setCity(dto.city());
        address.setRegion(dto.region());
        address.setStreet(dto.street());
        address.setLatitude(dto.latitude());
        address.setLongitude(dto.longitude());
        return address;
    }

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
