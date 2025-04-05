package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.model.Attraction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class AttractionMapper {

    public Attraction mapToAttraction(AttractionRequestDTO dto) {
        Attraction attraction = new Attraction();
        attraction.setName(dto.name());
        attraction.setDescription(dto.description());
        attraction.setType(dto.type());
        return attraction;
    }

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
