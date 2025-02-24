package com.aston.intensive.simplespringbootapp.controller;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api/attractions")
public interface AttractionController {

    @GetMapping("/{id}")
    AttractionRequestDTO getAttractionById(@PathVariable UUID id);
}