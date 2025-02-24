package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.mapper.AttractionMapper;
import com.aston.intensive.simplespringbootapp.model.Attraction;
import com.aston.intensive.simplespringbootapp.repository.AttractionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;
    private final AttractionMapper attractionMapper;

    @Override
    public AttractionRequestDTO findById(UUID id) {
        log.debug("Find attraction by id: {}", id);

        Optional<Attraction> optionalAttraction = attractionRepository.findById(id);
        if (optionalAttraction.isPresent()) {
            Attraction attraction = optionalAttraction.get();
            return attractionMapper.mapToAttractionRequestDTO(attraction);
        }
        log.debug("No attraction found with id: {}", id);
        return null;
    }
}