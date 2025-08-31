package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.address.controller.dto.request.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.address.controller.dto.response.AddressResponseDTO;
import com.aston.intensive.simplespringbootapp.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressMapperTest {

    private AddressMapper addressMapper;

    @BeforeEach
    void setUp() {
        addressMapper = new AddressMapper();
    }

    @Test
    void testMapToAddressResponseDTO() {
        Address address = new Address();
        address.setCity("New York");
        address.setStreet("Main Street");
        address.setRegion("NY");
        address.setBuilding(12);
        address.setLongitude(44.22);
        address.setLatitude(50.22);

        AddressResponseDTO addressResponseDTO = addressMapper.mapToAddressResponseDTO(address);

        assertNotNull(addressResponseDTO);
        assertEquals(address.getCity(), addressResponseDTO.city());
        assertEquals(address.getStreet(), addressResponseDTO.street());
        assertEquals(address.getRegion(), addressResponseDTO.region());
        assertEquals(address.getBuilding(), addressResponseDTO.building());
        assertEquals(address.getLongitude(), addressResponseDTO.longitude());
        assertEquals(address.getLatitude(), addressResponseDTO.latitude());
    }

    @Test
    void testMapToAddress() {
        AddressRequestDTO addressRequestDTO = AddressRequestDTO.builder()
                .city("New York")
                .street("Main Street")
                .region("NY")
                .building(12)
                .longitude(44.22)
                .latitude(50.22)
                .build();

        Address address = addressMapper.mapToAddress(addressRequestDTO);
        assertNotNull(address);
        assertEquals(address.getCity(), addressRequestDTO.city());
        assertEquals(address.getStreet(), addressRequestDTO.street());
        assertEquals(address.getRegion(), addressRequestDTO.region());
        assertEquals(address.getBuilding(), addressRequestDTO.building());
        assertEquals(address.getLongitude(), addressRequestDTO.longitude());
        assertEquals(address.getLatitude(), addressRequestDTO.latitude());
    }

}
