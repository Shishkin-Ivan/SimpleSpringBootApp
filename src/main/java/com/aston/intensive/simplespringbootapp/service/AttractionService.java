package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.model.AttractionType;

import java.util.List;
import java.util.UUID;

public interface AttractionService {

    AttractionResponseDTO getAttractionById(UUID id);

    List<AttractionResponseDTO> getAllAttractions(int pageNumber, int pageSize, UUID id, String name, String description, AttractionType type);

    long getAttractionCount();

    AttractionResponseDTO createAttraction(AttractionRequestDTO attractionRequestDTO);

    AttractionResponseDTO updateAttraction(UUID id, AttractionRequestDTO attractionRequestDTO);

    void deleteAttraction(UUID id);
}
