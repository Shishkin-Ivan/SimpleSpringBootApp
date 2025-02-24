package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.AddressRequestDTO;

import java.util.UUID;

public interface AddressService {
    AddressRequestDTO findById(UUID id);
}
