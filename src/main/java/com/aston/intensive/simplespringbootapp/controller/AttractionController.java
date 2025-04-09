package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.model.AttractionType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST API for managing attractions.
 * Provides endpoints to perform CRUD operations and search/filtering.
 */
public interface AttractionController {

    /**
     * Get attraction by its unique ID.
     *
     * @param id UUID of the attraction.
     * @return the attraction details.
     */
    @GetMapping("/{id}")
    AttractionResponseDTO getAttractionById(@PathVariable UUID id);

    /**
     * Get a paginated list of attractions, optionally filtered by parameters.
     *
     * @param pageNumber page number for pagination (default is 0).
     * @param pageSize page size for pagination (default is Integer.MAX_VALUE).
     * @param id filter by attraction ID (optional).
     * @param name filter by name (optional).
     * @param description filter by description (optional).
     * @param type filter by type (optional).
     * @return list of matching attractions.
     */
    @GetMapping
    List<AttractionResponseDTO> getAllAttractions(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) AttractionType type
    );

    /**
     * Get total count of attractions in the system.
     *
     * @return total attraction count.
     */
    @GetMapping("/count")
    long getAttractionsCount();

    /**
     * Create a new attraction.
     *
     * @param attractionRequestDTO DTO containing attraction data.
     * @return created attraction.
     */
    @PostMapping
    AttractionResponseDTO createAttraction(@RequestBody AttractionRequestDTO attractionRequestDTO);

    /**
     * Update an existing attraction by ID.
     *
     * @param id UUID of the attraction to update.
     * @param attractionRequestDTO new attraction data.
     * @return updated attraction.
     */
    @PutMapping("/{id}")
    AttractionResponseDTO updateAttraction(@PathVariable UUID id, @RequestBody AttractionRequestDTO attractionRequestDTO);

    /**
     * Delete an attraction by ID.
     *
     * @param id UUID of the attraction to delete.
     */
    @DeleteMapping("/{id}")
    void deleteAttraction(@PathVariable UUID id);

}
