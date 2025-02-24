package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.ServiceRequestDTO;

import java.util.UUID;

public interface ServiceService {
    ServiceRequestDTO findById(UUID id);
}