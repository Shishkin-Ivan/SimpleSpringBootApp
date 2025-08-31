package com.aston.intensive.simplespringbootapp.address.controller;

import com.aston.intensive.simplespringbootapp.address.controller.dto.request.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.address.controller.dto.response.AddressResponseDTO;
import com.aston.intensive.simplespringbootapp.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller implementation for managing addresses.
 * Handles HTTP requests and delegates business logic to AddressService.
 */
@RequestMapping("/api/address")
@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController {

    private final AddressService addressService;

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressResponseDTO getAddressById(@PathVariable UUID id) {
        return addressService.getAddressById(id);
    }

    /**
     * {@inheritDoc}
     */
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

        return addressService.getAllAddresses(pageNumber, pageSize, id, city, street, building, region, latitude, longitude);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getAddressesCount() {
        return addressService.getAddressesCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressResponseDTO createAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        return addressService.createAddress(addressRequestDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressResponseDTO updateAddress(@PathVariable UUID id, @RequestBody AddressRequestDTO addressRequestDTO) {
        return addressService.updateAddress(id, addressRequestDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAddress(@PathVariable UUID id) {
        addressService.deleteAddress(id);
    }

}
