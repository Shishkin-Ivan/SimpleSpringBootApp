package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.model.Attraction;
import org.springframework.stereotype.Component;

@Component
public class AttractionMapper {

    public AttractionRequestDTO mapToAttractionRequestDTO(Attraction attraction) {
        return AttractionRequestDTO.builder()
                .name(attraction.getName())
                .description(attraction.getDescription())
                .type(attraction.getType())
                .build();
    }
}