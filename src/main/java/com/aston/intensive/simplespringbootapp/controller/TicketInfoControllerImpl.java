package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.TicketInfoResponseDTO;
import com.aston.intensive.simplespringbootapp.service.TicketInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller implementation for managing tickets info.
 * Handles HTTP requests and delegates business logic to TicketInfoService.
 */
@RequestMapping("/api/ticket-info")
@RestController
@RequiredArgsConstructor
public class TicketInfoControllerImpl implements TicketInfoController {

    private final TicketInfoService ticketInfoService;

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketInfoResponseDTO getTicketInfoById(@PathVariable UUID id) {
        return ticketInfoService.getTicketInfoById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TicketInfoResponseDTO> getTicketsInfo(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "2147483647") int pageSize,
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) Boolean availability) {

        return ticketInfoService.getAllTicketsInfo(pageNumber, pageSize, id, price, currency, availability);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getTicketsInfoCount() {
        return ticketInfoService.getTicketsInfoCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketInfoResponseDTO createTicketInfo(@RequestBody TicketInfoRequestDTO ticketInfoRequestDTO) {
        return ticketInfoService.createTicketInfo(ticketInfoRequestDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketInfoResponseDTO updateTicketInfo(@PathVariable UUID id, @RequestBody TicketInfoRequestDTO ticketInfoRequestDTO) {
        return ticketInfoService.updateTicketInfo(id, ticketInfoRequestDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTicketInfo(@PathVariable UUID id) {
        ticketInfoService.deleteTicketInfo(id);
    }

}
