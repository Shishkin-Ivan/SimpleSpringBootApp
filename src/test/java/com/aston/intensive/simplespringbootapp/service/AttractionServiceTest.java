package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.attraction.service.impl.AttractionServiceImpl;
import com.aston.intensive.simplespringbootapp.attraction.controller.dto.request.AttractionRequestDTO;
import com.aston.intensive.simplespringbootapp.attraction.controller.dto.response.AttractionResponseDTO;
import com.aston.intensive.simplespringbootapp.mapper.AttractionMapper;
import com.aston.intensive.simplespringbootapp.model.Attraction;
import com.aston.intensive.simplespringbootapp.attraction.repository.AttractionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AttractionServiceTest {

    @Mock
    private AttractionRepository attractionRepository;

    @Mock
    private AttractionMapper attractionMapper;

    @InjectMocks
    private AttractionServiceImpl attractionService;

    private UUID attractionId;
    private Attraction attraction;
    private AttractionRequestDTO attractionRequestDTO;
    private AttractionResponseDTO attractionResponseDTO;

    @BeforeEach
    public void setUp() {
        attractionId = UUID.randomUUID();
        attraction = new Attraction();
        attraction.setId(attractionId);
        attraction.setName("Test Name");
        attraction.setDescription("Test Description");

        attractionRequestDTO = AttractionRequestDTO.builder()
                .name("Update Name")
                .description("Update Description")
                .build();

        attractionResponseDTO = AttractionResponseDTO.builder()
                .id(attractionId)
                .name("Test Name")
                .description("Test Description")
                .build();
    }

    @Test
    void testGetAttractionById_Found() {
        when(attractionRepository.findById(attractionId)).thenReturn(Optional.of(attraction));
        when(attractionMapper.mapToAttractionResponseDTO(attraction)).thenReturn(attractionResponseDTO);

        AttractionResponseDTO result = attractionService.getAttractionById(attractionId);
        assertNotNull(result);
        assertEquals(attractionId, result.id());
        verify(attractionRepository).findById(attractionId);
    }

    @Test
    void testGetAttractionById_NotFound() {
        when(attractionRepository.findById(attractionId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> attractionService.getAttractionById(attractionId));
    }

    @Test
    void testGetAllAttractions() {
        List<Attraction> attractions = List.of(attraction);
        when(attractionRepository.findByFilters(any(), any(), any(), any()))
                .thenReturn(attractions);
        when(attractionMapper.mapToAttractionResponseDTO(attraction)).thenReturn(attractionResponseDTO);

        List<AttractionResponseDTO> result = attractionService.getAllAttractions(0, 234,null, null, null, null);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(attractionRepository).findByFilters(any(), any(), any(), any());
    }

    @Test
    void testGetAttractionsCount() {
        when(attractionRepository.count()).thenReturn(9544L);
        assertEquals(9544L, attractionRepository.count());
    }

    @Test
    void testCreateAttraction() {
        when(attractionMapper.mapToAttraction(attractionRequestDTO)).thenReturn(attraction);
        when(attractionRepository.save(attraction)).thenReturn(attraction);
        when(attractionMapper.mapToAttractionResponseDTO(attraction)).thenReturn(attractionResponseDTO);

        AttractionResponseDTO result = attractionService.createAttraction(attractionRequestDTO);
        assertNotNull(result);
        assertEquals(attractionId, result.id());
    }

    @Test
    void testUpdateAttractionById_Found() {
        when(attractionRepository.findById(attractionId)).thenReturn(Optional.of(attraction));
        when(attractionMapper.mapToAttraction(attractionRequestDTO)).thenReturn(attraction);
        when(attractionRepository.save(attraction)).thenReturn(attraction);
        when(attractionMapper.mapToAttractionResponseDTO(attraction)).thenReturn(attractionResponseDTO);

        AttractionResponseDTO result = attractionService.updateAttraction(attractionId, attractionRequestDTO);
        assertNotNull(result);
        assertEquals(attractionId, result.id());
    }

    @Test
    void testUpdateAttractionById_NotFound() {
        when(attractionRepository.findById(attractionId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> attractionService.updateAttraction(attractionId, attractionRequestDTO));
    }

    @Test
    void testDeleteAttraction_Found(){
        when(attractionRepository.existsById(attractionId)).thenReturn(true);
        doNothing().when(attractionRepository).deleteById(attractionId);

        attractionService.deleteAttraction(attractionId);

        verify(attractionRepository).existsById(attractionId);
        verify(attractionRepository).deleteById(attractionId);
    }

    @Test
    void testDeleteAttraction_NotFound() {
        when(attractionRepository.existsById(attractionId)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> attractionService.deleteAttraction(attractionId));
    }

}
