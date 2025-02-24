package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AttractionControllerImpl implements AttractionController {
    private final AttractionService attractionService;

    @Override
    public AttractionRequestDTO getAttractionById(UUID id) {
        return attractionService.findById(id);
    }
}