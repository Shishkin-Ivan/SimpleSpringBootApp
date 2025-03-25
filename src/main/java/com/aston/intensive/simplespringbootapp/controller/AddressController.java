package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AddressResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface AddressController {

    @GetMapping("/{id}")
    AddressResponseDTO getAddressById(@PathVariable UUID id);

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

    @GetMapping("/count")
    long getAddressesCount();

    @PostMapping
    AddressResponseDTO createAddress(@RequestBody AddressRequestDTO addressRequestDTO);

    @PutMapping("/{id}")
    AddressResponseDTO updateAddress(@PathVariable UUID id, @RequestBody AddressRequestDTO addressRequestDTO);

    @DeleteMapping("/{id}")
    void deleteAddress(@PathVariable UUID id);
}
