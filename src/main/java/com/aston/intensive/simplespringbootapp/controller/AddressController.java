package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.AddressRequestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api/address")
public interface AddressController {

    @GetMapping("/{id}")
    AddressRequestDTO getAddressById(@PathVariable UUID id);
}
