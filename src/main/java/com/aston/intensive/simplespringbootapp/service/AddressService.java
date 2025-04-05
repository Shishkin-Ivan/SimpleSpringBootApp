package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AddressResponseDTO;

import java.util.List;
import java.util.UUID;

public interface AddressService {

    AddressResponseDTO getAddressById(UUID id);

    List<AddressResponseDTO> getAllAddresses(int pageNumber, int pageSize, UUID id, String city, String street, Integer building,
                                             String region, Double latitude, Double longitude);

    long getAddressesCount();

    AddressResponseDTO createAddress(AddressRequestDTO addressRequestDTO);

    AddressResponseDTO updateAddress(UUID id, AddressRequestDTO addressRequestDTO);

    void deleteAddress(UUID id);

}
