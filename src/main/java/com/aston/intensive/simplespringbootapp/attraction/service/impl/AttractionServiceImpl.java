package com.aston.intensive.simplespringbootapp.attraction.service.impl;

import com.aston.intensive.simplespringbootapp.attraction.service.AttractionService;
import com.aston.intensive.simplespringbootapp.attraction.controller.dto.request.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.attraction.controller.dto.response.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.mapper.AttractionMapper;
import com.aston.intensive.simplespringbootapp.model.*;
import com.aston.intensive.simplespringbootapp.address.repository.AddressRepository;
import com.aston.intensive.simplespringbootapp.attraction.repository.AttractionRepository;
import com.aston.intensive.simplespringbootapp.service.repository.ServiceRepository;
import com.aston.intensive.simplespringbootapp.ticket_info.repository.TicketInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of the AttractionService interface.
 * This class provides functionality for managing attractions, including retrieving, creating, updating, and deleting attractions.
 */
@Slf4j
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;
    private final AttractionMapper attractionMapper;
    private final AddressRepository addressRepository;
    private final TicketInfoRepository ticketInfoRepository;
    private final ServiceRepository serviceRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public AttractionResponseDTO getAttractionById(UUID id) {
        log.debug("Find attraction by id: {}", id);

        Attraction attraction = attractionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attraction with id " + id + " not found"));

        return attractionMapper.mapToAttractionResponseDTO(attraction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AttractionResponseDTO> getAllAttractions(int pageNumber, int pageSize, UUID id, String name, String description, AttractionType type) {
        log.debug("Find all attractions with filters");

        List<Attraction> attractions = attractionRepository.findByFilters(id, name, description, type);

        return attractions.stream().skip(pageNumber * pageSize).limit(pageSize)
                .map(attractionMapper::mapToAttractionResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long getAttractionCount() {
        log.debug("Counting all attractions");
        return attractionRepository.count();
    }

    /**
     * {@inheritDoc}
     */
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
        if (attractionRequestDTO.serviceIds() != null && !attractionRequestDTO.serviceIds().isEmpty()) {
            Set<Service> services = new HashSet<>();
            List<String> serviceIds = attractionRequestDTO.serviceIds();
            for (String serviceId : serviceIds) {
                Service service = serviceRepository.findById(UUID.fromString(serviceId))
                        .orElseThrow(() -> new EntityNotFoundException("Service with id " + serviceId + " not found"));

                services.add(service);
            }
            if (!services.isEmpty()) {
                attraction.setServices(services);
            }
        }
        attractionRepository.save(attraction);
        return attractionMapper.mapToAttractionResponseDTO(attraction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public AttractionResponseDTO updateAttraction(UUID id, AttractionRequestDTO attractionRequestDTO) {
        Optional<Attraction> optionalAttraction = attractionRepository.findById(id);
        if (optionalAttraction.isPresent()) {
            Attraction attraction = optionalAttraction.get();
            Attraction updatedAttraction = attractionMapper.mapToAttraction(attractionRequestDTO);
            if (!updatedAttraction.getName().isBlank()) {
                attraction.setName(updatedAttraction.getName());
            }
            if (!updatedAttraction.getDescription().isBlank()) {
                attraction.setDescription(updatedAttraction.getDescription());
            }
            if (updatedAttraction.getType() != null) {
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

            if (attractionRequestDTO.serviceIds() != null && !attractionRequestDTO.serviceIds().isEmpty()) {
                Set<Service> services = new HashSet<>();
                List<String> serviceIds = attractionRequestDTO.serviceIds();
                for (String serviceId : serviceIds) {
                    if (serviceRepository.findById(UUID.fromString(serviceId)).isPresent()) {
                        Service service = serviceRepository.findById(UUID.fromString(serviceId))
                                .orElseThrow(() -> new EntityNotFoundException("Service with id " + serviceId + " not found"));
                        services.add(service);
                    }
                }
                if (!services.isEmpty()) {
                    attraction.setServices(services);
                }
            }

            Attraction result = attractionRepository.save(attraction);
            return attractionMapper.mapToAttractionResponseDTO(result);
        }
        throw new EntityNotFoundException("Attraction with id " + id + " not found");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteAttraction(UUID id) {
        if (!attractionRepository.existsById(id)) {
            throw new EntityNotFoundException("Attraction with id " + id + " not found");
        }
        attractionRepository.deleteById(id);
    }

}
