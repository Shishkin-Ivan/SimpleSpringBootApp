package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.TicketInfoResponseDTO;
import com.aston.intensive.simplespringbootapp.service.TicketInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TicketInfoControllerImpl implements TicketInfoController {

    private final TicketInfoService ticketInfoService;

    @Override
    public TicketInfoResponseDTO getTicketInfoById(@PathVariable UUID id) {
        return ticketInfoService.getTicketInfoById(id);
    }

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

    @Override
    public long getTicketsInfoCount() {
        return ticketInfoService.getTicketsInfoCount();
    }

    @Override
    public TicketInfoResponseDTO createTicketInfo(@RequestBody TicketInfoRequestDTO ticketInfoRequestDTO) {
        return ticketInfoService.createTicketInfo(ticketInfoRequestDTO);
    }

    @Override
    public TicketInfoResponseDTO updateTicketInfo(@PathVariable UUID id, @RequestBody TicketInfoRequestDTO ticketInfoRequestDTO) {
        return ticketInfoService.updateTicketInfo(id, ticketInfoRequestDTO);
    }

    @Override
    public void deleteTicketInfo(@PathVariable UUID id) {
        ticketInfoService.deleteTicketInfo(id);
    }
}
