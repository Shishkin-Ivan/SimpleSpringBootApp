package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.TicketInfoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface TicketInfoService {

    TicketInfoResponseDTO getTicketInfoById(UUID id);

    List<TicketInfoResponseDTO> getAllTicketsInfo(int pageNumber, int pageSize, UUID id, Double price, String currency, Boolean availability);

    long getTicketsInfoCount();

    TicketInfoResponseDTO createTicketInfo(TicketInfoRequestDTO ticketInfoRequestDTO);

    TicketInfoResponseDTO updateTicketInfo(UUID id, TicketInfoRequestDTO ticketInfoRequestDTO);

    void deleteTicketInfo(UUID id);
}
