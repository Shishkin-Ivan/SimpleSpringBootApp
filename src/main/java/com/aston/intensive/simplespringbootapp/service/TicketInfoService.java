package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.TicketInfoResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * Interface for managing tickets info.
 * Provides core operations such as creating, updating, deleting, and retrieving tickets info.
 */
public interface TicketInfoService {

    /**
     * Retrieves a ticket info by its unique identifier.
     *
     * @param id the unique identifier of the ticket info to retrieve.
     * @return a DTO object containing the ticket info information.
     * @throws EntityNotFoundException if no ticket info is found with the given ID.
     */
    TicketInfoResponseDTO getTicketInfoById(UUID id);

    /**
     * Retrieves a list of tickets info with optional filters, paginated.
     *
     * @param pageNumber the page number.
     * @param pageSize the page size.
     * @param id an optional filter for the ticket info ID.
     * @param price an optional filter for the ticket info price.
     * @param currency an optional filter for the ticket info currency.
     * @param availability an optional filter for the ticket info availability.
     * @return a list of ticket info DTOs matching the filters, paginated.
     */
    List<TicketInfoResponseDTO> getAllTicketsInfo(int pageNumber, int pageSize, UUID id, Double price, String currency, Boolean availability);

    /**
     * Returns the total count of tickets info.
     *
     * @return the total number of tickets info.
     */
    long getTicketsInfoCount();

    /**
     * Creates a new ticket info based on the provided data.
     *
     * @param ticketInfoRequestDTO the DTO containing data for the new ticket info.
     * @return a DTO representing the created ticket info.
     */
    TicketInfoResponseDTO createTicketInfo(TicketInfoRequestDTO ticketInfoRequestDTO);

    /**
     * Update an existing ticket info.
     *
     * @param id the ticket info ID to update.
     * @param ticketInfoRequestDTO the DTO containing updated data for the ticket info.
     * @return the DTO object with updated the ticket info information.
     * @throws EntityNotFoundException if the ticket info with the given ID is not found.
     */
    TicketInfoResponseDTO updateTicketInfo(UUID id, TicketInfoRequestDTO ticketInfoRequestDTO);

    /**
     * Delete a ticket info by ID.
     *
     * @param id the ticket info ID to delete.
     * @throws EntityNotFoundException if the ticket info with the given ID is not found.
     */
    void deleteTicketInfo(UUID id);

}
