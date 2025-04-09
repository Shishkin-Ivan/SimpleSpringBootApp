package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.model.Attraction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper component for converting between {@link Attraction} entities and their DTO representations.
 * <p>
 * Isolates the transformation logic between the API layer (DTOs) and the persistence layer (entities).
 */
@RequiredArgsConstructor
@Component
public class AttractionMapper {

    /**
     * Converts an {@link AttractionRequestDTO} to an {@link Attraction} entity.
     *
     * @param attractionRequestDTO the DTO containing attraction data from the client
     * @return a new {@link Attraction} entity with values mapped from the DTO
     */
    public Attraction mapToAttraction(AttractionRequestDTO attractionRequestDTO) {
        Attraction attraction = new Attraction();
        attraction.setName(attractionRequestDTO.name());
        attraction.setDescription(attractionRequestDTO.description());
        attraction.setType(attractionRequestDTO.type());
        return attraction;
    }

    /**
     * Converts an {@link Attraction} entity to an {@link AttractionResponseDTO}.
     * <p>
     * Also formats nested fields like address and ticket info into human-readable strings.
     *
     * @param attraction the entity representing persisted attraction data
     * @return a DTO with values mapped from the attraction entity
     */
    public AttractionResponseDTO mapToAttractionResponseDTO(Attraction attraction) {

        String strAddress = (attraction.getAddress() != null) ?
                attraction.getAddress().getBuilding() + " " +
                        attraction.getAddress().getStreet() + ", " +
                        attraction.getAddress().getCity() :
                "No Address";

        String strTicketInfo = (attraction.getTicketInfo() != null) ?
                attraction.getTicketInfo().getPrice() + " " +
                        attraction.getTicketInfo().getCurrency() :
                "No Ticket Info";

        List<String> serviceNames = new ArrayList<>();

        if(attraction.getServices() != null && !attraction.getServices().isEmpty()) {
            serviceNames = attraction.getServices().stream().map(s -> s.getName()).collect(Collectors.toList());
        }

        return AttractionResponseDTO.builder()
                .id(attraction.getId())
                .name(attraction.getName())
                .description(attraction.getDescription())
                .type(attraction.getType())
                .address(strAddress)
                .ticketInfo(strTicketInfo)
                .services(serviceNames)
                .build();
    }

}
