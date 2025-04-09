package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.TicketInfoResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST API for managing tickets info.
 * Provides endpoints to perform CRUD operations and search/filtering.
 */
public interface TicketInfoController {

    /**
     * Get ticket info by its unique ID.
     *
     * @param id UUID of the ticket info.
     * @return the ticket info details.
     */
    @GetMapping("/{id}")
    TicketInfoResponseDTO getTicketInfoById(@PathVariable UUID id);

    /**
     * Get a paginated list of tickets info, optionally filtered by parameters.
     *
     * @param pageNumber page number for pagination (default is 0).
     * @param pageSize page size for pagination (default is Integer.MAX_VALUE).
     * @param id filter by ticket info ID (optional).
     * @param price filter by price (optional).
     * @param currency filter by currency (optional).
     * @param availability filter by availability (optional).
     * @return list of matching tickets info.
     */
    @GetMapping
    List<TicketInfoResponseDTO> getTicketsInfo(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "2147483647") int pageSize,
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) Boolean availability
    );

    /**
     * Get total count of tickets info in the system.
     *
     * @return total ticket info count.
     */
    @GetMapping("/count")
    long getTicketsInfoCount();

    /**
     * Create a new ticket info.
     *
     * @param ticketInfoRequestDTO DTO containing ticket info data.
     * @return created ticket info.
     */
    @PostMapping
    TicketInfoResponseDTO createTicketInfo(@RequestBody TicketInfoRequestDTO ticketInfoRequestDTO);

    /**
     * Update an existing ticket info by ID.
     *
     * @param id UUID of the ticket info to update.
     * @param ticketInfoRequestDTO new ticket info data.
     * @return updated ticket info.
     */
    @PutMapping("/{id}")
    TicketInfoResponseDTO updateTicketInfo(@PathVariable UUID id, @RequestBody TicketInfoRequestDTO ticketInfoRequestDTO);

    /**
     * Delete a ticket info by ID.
     *
     * @param id UUID of the ticket info to delete.
     */
    @DeleteMapping("/{id}")
    void deleteTicketInfo(@PathVariable UUID id);

}
