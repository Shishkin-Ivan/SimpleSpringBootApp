package com.aston.intensive.simplespringbootapp.dto;

import com.aston.intensive.simplespringbootapp.model.AttractionType;
import lombok.Builder;

@Builder
public record AttractionRequestDTO(
        String name,
        String description,
        AttractionType type
) {}