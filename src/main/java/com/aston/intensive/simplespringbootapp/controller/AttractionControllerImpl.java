package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.model.AttractionType;
import com.aston.intensive.simplespringbootapp.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/attraction")
@RestController
@RequiredArgsConstructor
public class AttractionControllerImpl implements AttractionController {
    private final AttractionService attractionService;

    @Override
    public AttractionResponseDTO getAttractionById(@PathVariable UUID id) {
        return attractionService.getAttractionById(id);
    }

    @Override
    public List<AttractionResponseDTO> getAllAttractions(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) AttractionType type) {
        return attractionService.getAllAttractions(pageNumber, pageSize, id, name, description, type);
    }

    @Override
    public long getAttractionsCount() {
        return attractionService.getAttractionCount();
    }

    @Override
    public AttractionResponseDTO createAttraction(AttractionRequestDTO attractionRequestDTO) {
        return attractionService.createAttraction(attractionRequestDTO);
    }

    @Override
    public AttractionResponseDTO updateAttraction(@PathVariable UUID id, @RequestBody AttractionRequestDTO attractionRequestDTO) {
        return attractionService.updateAttraction(id, attractionRequestDTO);
    }

    @Override
    public void deleteAttraction(@PathVariable UUID id) {
        attractionService.deleteAttraction(id);
    }
}