package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AddressResponseDTO;
import com.aston.intensive.simplespringbootapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/address")
@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController {
    private final AddressService addressService;

    @Override
    public AddressResponseDTO getAddressById(@PathVariable UUID id) {
        return addressService.getAddressById(id);
    }

    @Override
    public List<AddressResponseDTO> getAddresses(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "2147483647") int pageSize,
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) Integer building,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude) {

        return addressService.findAll(pageNumber, pageSize, id, city, street, building, region, latitude, longitude);
    }

    @Override
    public long getAddressesCount() {
        return addressService.getAddressesCount();
    }

    @Override
    public AddressResponseDTO createAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        return addressService.createAddress(addressRequestDTO);
    }

    @Override
    public AddressResponseDTO updateAddress(@PathVariable UUID id, @RequestBody AddressRequestDTO addressRequestDTO) {
        return addressService.updateAddress(id, addressRequestDTO);
    }

    @Override
    public void deleteAddress(@PathVariable UUID id) {
        addressService.deleteAddress(id);
    }
}
