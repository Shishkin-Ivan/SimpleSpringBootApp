package com.aston.intensive.simplespringbootapp.mapper;

import com.aston.intensive.simplespringbootapp.dto.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AttractionMapperTest {

    private AttractionMapper attractionMapper;

    @BeforeEach
    void setUp() {
        attractionMapper = new AttractionMapper();
    }

    @Test
    void testMapToAttraction() {
        AttractionRequestDTO attractionRequestDTO = AttractionRequestDTO.builder()
                .name("Name Test")
                .description("Description Test")
                .type(AttractionType.ENTERTAINMENT)
                .build();

        Attraction attraction = attractionMapper.mapToAttraction(attractionRequestDTO);

        assertNotNull(attraction);

        assertEquals(attractionRequestDTO.name(), attraction.getName());
        assertEquals(attractionRequestDTO.description(), attraction.getDescription());
        assertEquals(attractionRequestDTO.type().ordinal(), attraction.getType().ordinal());
    }

    @Test
    void testMapToAttractionResponseDTO() {
        UUID addressId = UUID.randomUUID();
        Address address = new Address();
        address.setId(addressId);
        address.setStreet("street");
        address.setCity("city");
        address.setRegion("region");
        address.setBuilding(12);
        address.setLongitude(55.22);
        address.setLatitude(787.3433);

        UUID ticketInfoId = UUID.randomUUID();
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setId(ticketInfoId);
        ticketInfo.setPrice(999999.9999);
        ticketInfo.setCurrency("CNY");
        ticketInfo.setAvailability(false);

        Service service = new Service();
        service.setId(UUID.randomUUID());
        service.setName("Service Name");
        service.setDescription("Service Description");
        service.setType(ServiceType.TRANSPORT);

        Service service2 = new Service();
        service2.setId(UUID.randomUUID());
        service2.setName("Service Name 2");
        service2.setDescription("Service Description 2");
        service2.setType(ServiceType.ADDITIONAL);

        Attraction attraction = new Attraction();
        attraction.setId(UUID.randomUUID());
        attraction.setName("Name Test");
        attraction.setDescription("Description Test");
        attraction.setType(AttractionType.ENTERTAINMENT);
        attraction.setAddress(address);
        attraction.setTicketInfo(ticketInfo);
        attraction.setServices(Set.of(service, service2));

        AttractionResponseDTO attractionResponseDTO = attractionMapper.mapToAttractionResponseDTO(attraction);

        assertNotNull(attractionResponseDTO);

        assertEquals(attraction.getId(), attraction.getId());
        assertEquals(attraction.getName(), attraction.getName());
        assertEquals(attraction.getDescription(), attraction.getDescription());
        assertEquals(attraction.getType().ordinal(), attraction.getType().ordinal());
        String strAddress = address.getBuilding() + " " + address.getStreet() + ", " + address.getCity();
        assertEquals(strAddress, attractionResponseDTO.address());
        String strTicketInfo = ticketInfo.getPrice() + " " + ticketInfo.getCurrency();
        assertEquals(strTicketInfo, attractionResponseDTO.ticketInfo());
        assertEquals(attraction.getServices().size(), attractionResponseDTO.services().size());
    }

}
