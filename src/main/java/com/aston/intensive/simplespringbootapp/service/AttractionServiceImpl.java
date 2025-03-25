package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.mapper.AttractionMapper;
import com.aston.intensive.simplespringbootapp.model.Address;
import com.aston.intensive.simplespringbootapp.model.Attraction;
import com.aston.intensive.simplespringbootapp.model.AttractionType;
import com.aston.intensive.simplespringbootapp.model.TicketInfo;
import com.aston.intensive.simplespringbootapp.repository.AddressRepository;
import com.aston.intensive.simplespringbootapp.repository.AttractionRepository;
import com.aston.intensive.simplespringbootapp.repository.TicketInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;
    private final AttractionMapper attractionMapper;
    private final AddressRepository addressRepository;
    private final TicketInfoRepository ticketInfoRepository;

    @Override
    public AttractionResponseDTO getAttractionById(UUID id) {
        log.debug("Find attraction by id: {}", id);

        Optional<Attraction> optionalAttraction = attractionRepository.findById(id);
        if (optionalAttraction.isPresent()) {
            Attraction attraction = optionalAttraction.get();
            return attractionMapper.mapToAttractionResponseDTO(attraction);
        }
        log.debug("No attraction found with id: {}", id);
        return null;
    }

    @Override
    public List<AttractionResponseDTO> getAllAttractions(int pageNumber, int pageSize, UUID id, String name, String description, AttractionType type) {
        log.debug("Find all attractions with filters");

        List<Attraction> attractions = attractionRepository.findByFilters(id, name, description, type);
        return attractions.stream().skip(pageNumber * pageSize).limit(pageSize)
                .map(attractionMapper::mapToAttractionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public long getAttractionCount() {
        return attractionRepository.count();
    }

    @Override
    @Transactional
    public AttractionResponseDTO createAttraction(AttractionRequestDTO attractionRequestDTO) {
        log.debug("Saving attraction: {}", attractionRequestDTO);

        Attraction attraction = attractionMapper.mapToAttraction(attractionRequestDTO);

        if (attractionRequestDTO.addressId() != null) {

            Address address = addressRepository.findById(UUID.fromString(attractionRequestDTO.addressId()))
                    .orElseThrow(() -> new EntityNotFoundException("Address with id " + attractionRequestDTO.addressId() + " not found"));
            attraction.setAddress(address);
        }

        if (attractionRequestDTO.ticketInfoId() != null) {
            TicketInfo ticketInfo = ticketInfoRepository.findById(UUID.fromString(attractionRequestDTO.ticketInfoId()))
                    .orElseThrow(() -> new EntityNotFoundException("Ticket Info with id " + attractionRequestDTO.ticketInfoId() + " not found"));
            attraction.setTicketInfo(ticketInfo);
        }


        attractionRepository.save(attraction);
        return attractionMapper.mapToAttractionResponseDTO(attraction);
    }

    @Override
    @Transactional
    public AttractionResponseDTO updateAttraction(UUID id, AttractionRequestDTO attractionRequestDTO) {
        Optional<Attraction> optionalAttraction = attractionRepository.findById(id);
        if (optionalAttraction.isPresent()) {
            Attraction attraction = optionalAttraction.get();
            Attraction updatedAttraction = attractionMapper.mapToAttraction(attractionRequestDTO);
            if(!updatedAttraction.getName().isBlank()) {
                attraction.setName(updatedAttraction.getName());
            }
            if(!updatedAttraction.getDescription().isBlank()) {
                attraction.setDescription(updatedAttraction.getDescription());
            }
            if(updatedAttraction.getType() != null) {
                attraction.setType(updatedAttraction.getType());
            }

            if (attractionRequestDTO.addressId() != null) {
                Address address = addressRepository.findById(UUID.fromString(attractionRequestDTO.addressId()))
                        .orElseThrow(() -> new EntityNotFoundException("Address with id " + attractionRequestDTO.addressId() + " not found"));
                attraction.setAddress(address);
            }

            if (attractionRequestDTO.ticketInfoId() != null) {
                TicketInfo ticketInfo = ticketInfoRepository.findById(UUID.fromString(attractionRequestDTO.ticketInfoId()))
                        .orElseThrow(() -> new EntityNotFoundException("Ticket Info with id " + attractionRequestDTO.ticketInfoId() + " not found"));
                attraction.setTicketInfo(ticketInfo);
            }

            attractionRepository.save(attraction);
            return attractionMapper.mapToAttractionResponseDTO(attraction);
        }
        throw new EntityNotFoundException("Attraction with id " + id + " not found");
    }

    @Override
    @Transactional
    public void deleteAttraction(UUID id) {
        if(!attractionRepository.existsById(id)) {
            throw new EntityNotFoundException("Attraction with id " + id + " not found");
        }
        attractionRepository.deleteById(id);
    }
}
