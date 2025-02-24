package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api/ticket-info")
public interface TicketInfoController {
    @GetMapping("/{id}")
    TicketInfoRequestDTO getTicketInfoById(@PathVariable UUID id);
}