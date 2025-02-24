package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;

import java.util.UUID;

public interface TicketInfoService {
    TicketInfoRequestDTO findById(UUID id);
}