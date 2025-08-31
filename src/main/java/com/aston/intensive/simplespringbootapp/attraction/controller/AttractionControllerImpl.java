package com.aston.intensive.simplespringbootapp.attraction.controller;

import com.aston.intensive.simplespringbootapp.attraction.controller.dto.request.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.attraction.controller.dto.response.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.model.AttractionType;
import com.aston.intensive.simplespringbootapp.attraction.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller implementation for managing attractions.
 * Handles HTTP requests and delegates business logic to AttractionService.
 */
@RequestMapping("/api/attraction")
@RestController
@RequiredArgsConstructor
public class AttractionControllerImpl implements AttractionController {

    private final AttractionService attractionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public AttractionResponseDTO getAttractionById(@PathVariable UUID id) {
        return attractionService.getAttractionById(id);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public long getAttractionsCount() {
        return attractionService.getAttractionCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttractionResponseDTO createAttraction(AttractionRequestDTO attractionRequestDTO) {
        return attractionService.createAttraction(attractionRequestDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttractionResponseDTO updateAttraction(@PathVariable UUID id, @RequestBody AttractionRequestDTO attractionRequestDTO) {
        return attractionService.updateAttraction(id, attractionRequestDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAttraction(@PathVariable UUID id) {
        attractionService.deleteAttraction(id);
    }

}
