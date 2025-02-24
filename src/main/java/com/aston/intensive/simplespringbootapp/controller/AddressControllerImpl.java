package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements AddressController{
    private final AddressService addressService;

    @Override
    public AddressRequestDTO getAddressById(UUID id) {
        return addressService.findById(id);
    }
}