package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.dto.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressRequestDTO mapToAddressRequestDTO(Address address) {
        return AddressRequestDTO.builder()
                .building(address.getBuilding())
                .city(address.getCity())
                .region(address.getRegion())
                .street(address.getStreet())
                .build();
    }
}
