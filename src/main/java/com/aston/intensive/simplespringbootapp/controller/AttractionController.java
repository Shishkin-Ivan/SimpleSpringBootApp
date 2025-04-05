package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.model.AttractionType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface AttractionController {

    @GetMapping("/{id}")
    AttractionResponseDTO getAttractionById(@PathVariable UUID id);

    @GetMapping
    List<AttractionResponseDTO> getAllAttractions(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) AttractionType type
    );

    @GetMapping("/count")
    long getAttractionsCount();

    @PostMapping
    AttractionResponseDTO createAttraction(@RequestBody AttractionRequestDTO attractionRequestDTO);

    @PutMapping("/{id}")
    AttractionResponseDTO updateAttraction(@PathVariable UUID id, @RequestBody AttractionRequestDTO attractionRequestDTO);

    @DeleteMapping("/{id}")
    void deleteAttraction(@PathVariable UUID id);
}
