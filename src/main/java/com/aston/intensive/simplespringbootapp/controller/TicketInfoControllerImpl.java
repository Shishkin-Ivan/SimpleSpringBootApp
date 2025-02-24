package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.service.TicketInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TicketInfoControllerImpl implements TicketInfoController {
    private final TicketInfoService ticketInfoService;

    @Override
    public TicketInfoRequestDTO getTicketInfoById(UUID id) {
        return ticketInfoService.findById(id);
    }
}