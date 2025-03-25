package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.TicketInfoResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/ticket-info")
public interface TicketInfoController {

    @GetMapping("/{id}")
    TicketInfoResponseDTO getTicketInfoById(@PathVariable UUID id);

    @GetMapping
    List<TicketInfoResponseDTO> getTicketsInfo(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "2147483647") int pageSize,
            @RequestParam(required = false) UUID id,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) Boolean availability
    );

    @GetMapping("/count")
    long getTicketsInfoCount();

    @PostMapping
    TicketInfoResponseDTO createTicketInfo(@RequestBody TicketInfoRequestDTO ticketInfoRequestDTO);

    @PutMapping("/{id}")
    TicketInfoResponseDTO updateTicketInfo(@PathVariable UUID id, @RequestBody TicketInfoRequestDTO ticketInfoRequestDTO);

    @DeleteMapping("/{id}")
    void deleteTicketInfo(@PathVariable UUID id);
}
