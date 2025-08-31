package com.aston.intensive.simplespringbootapp.address.controller;

import com.aston.intensive.simplespringbootapp.address.controller.dto.request.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.address.controller.dto.response.AddressResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST API for managing addresses.
 * Provides endpoints to perform CRUD operations and search/filtering.
 */
public interface AddressController {

    /**
     * Get address by its unique ID.
     *
     * @param id UUID of the address.
     * @return the address details.
     */
    @GetMapping("/{id}")
    AddressResponseDTO getAddressById(@PathVariable UUID id);

    /**
     * Get a paginated list of addresses, optionally filtered by parameters.
     *
     * @param pageNumber page number for pagination (default is 0).
     * @param pageSize page size for pagination (default is Integer.MAX_VALUE).
     * @param id filter by address ID (optional).
     * @param city filter by city (optional).
     * @param street filter by street (optional).
     * @param building filter by building number (optional).
     * @param region filter by region (optional).
     * @param latitude filter by latitude (optional).
     * @param longitude filter by longitude (optional).
     * @return list of matching addresses.
     */
    @GetMapping
    List<AddressResponseDTO> getAddresses(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "2147483647") int pageSize,
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) Integer building,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude
    );

    /**
     * Get total count of addresses in the system.
     *
     * @return total address count.
     */
    @GetMapping("/count")
    long getAddressesCount();

    /**
     * Create a new address.
     *
     * @param addressRequestDTO DTO containing address data.
     * @return created address.
     */
    @PostMapping
    AddressResponseDTO createAddress(@RequestBody AddressRequestDTO addressRequestDTO);

    /**
     * Update an existing address by ID.
     *
     * @param id UUID of the address to update.
     * @param addressRequestDTO new address data.
     * @return updated address.
     */
    @PutMapping("/{id}")
    AddressResponseDTO updateAddress(@PathVariable UUID id, @RequestBody AddressRequestDTO addressRequestDTO);

    /**
     * Delete an address by ID.
     *
     * @param id UUID of the address to delete.
     */
    @DeleteMapping("/{id}")
    void deleteAddress(@PathVariable UUID id);

}
