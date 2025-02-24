package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;

import java.util.UUID;

public interface AttractionService {
    AttractionRequestDTO findById(UUID id);
}