package com.aston.intensive.simplespringbootapp.attraction.service;

import com.aston.intensive.simplespringbootapp.attraction.controller.dto.request.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.attraction.controller.dto.response.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.model.AttractionType;

import java.util.List;
import java.util.UUID;

/**
 * Interface for managing attractions.
 * Provides core operations such as creating, updating, deleting, and retrieving attractions.
 */
public interface AttractionService {

    /**
     * Retrieves an attraction by its unique identifier.
     *
     * @param id the unique identifier of the attraction to retrieve.
     * @return a DTO object containing the attraction information.
     * @throws EntityNotFoundException if no attraction is found with the given ID.
     */
    AttractionResponseDTO getAttractionById(UUID id);

    /**
     * Retrieves a list of attractions with optional filters, paginated.
     *
     * @param pageNumber the page number.
     * @param pageSize the page size.
     * @param id filter by ID.
     * @param name filter by name.
     * @param description filter by description.
     * @param type filter by type.
     * @return a list of attraction DTOs matching the filters, paginated.
     */
    List<AttractionResponseDTO> getAllAttractions(int pageNumber, int pageSize, UUID id, String name, String description, AttractionType type);

    /**
     * Returns the total count of attractions.
     *
     * @return the total number of attractions.
     */
    long getAttractionCount();

    /**
     * Creates a new attraction based on the provided data.
     *
     * @param attractionRequestDTO the DTO containing data for the new attraction.
     * @return a DTO representing the created attraction.
     */
    AttractionResponseDTO createAttraction(AttractionRequestDTO attractionRequestDTO);

    /**
     * Update an existing attraction.
     *
     * @param id the attraction ID to update.
     * @param attractionRequestDTO the DTO object with new data for updating the attraction.
     * @return the DTO object with updated attraction information.
     * @throws EntityNotFoundException if the attraction with the given ID is not found.
     */
    AttractionResponseDTO updateAttraction(UUID id, AttractionRequestDTO attractionRequestDTO);

    /**
     * Delete an attraction by ID.
     *
     * @param id the attraction ID to delete.
     * @throws EntityNotFoundException if the attraction with the given ID is not found.
     */
    void deleteAttraction(UUID id);

}
