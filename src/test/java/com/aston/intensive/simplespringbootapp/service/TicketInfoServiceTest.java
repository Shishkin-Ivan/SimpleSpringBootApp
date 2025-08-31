package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.ticket_info.controller.dto.request.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.ticket_info.controller.dto.response.TicketInfoResponseDTO;
import com.aston.intensive.simplespringbootapp.mapper.TicketInfoMapper;
import com.aston.intensive.simplespringbootapp.model.TicketInfo;
import com.aston.intensive.simplespringbootapp.ticket_info.repository.TicketInfoRepository;
import com.aston.intensive.simplespringbootapp.ticket_info.service.impl.TicketInfoServiceImpl;
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
public class TicketInfoServiceTest {

    @Mock
    private TicketInfoRepository ticketInfoRepository;

    @Mock
    private TicketInfoMapper ticketInfoMapper;

    @InjectMocks
    private TicketInfoServiceImpl ticketInfoService;

    private UUID ticketInfoId;
    private TicketInfo ticketInfo;
    private TicketInfoRequestDTO ticketInfoRequestDTO;
    private TicketInfoResponseDTO ticketInfoResponseDTO;

    @BeforeEach
    public void setUp() {
        ticketInfoId = UUID.randomUUID();
        ticketInfo = new TicketInfo();
        ticketInfo.setId(ticketInfoId);
        ticketInfo.setPrice(4600.5);
        ticketInfo.setCurrency("RUB");

        ticketInfoRequestDTO = TicketInfoRequestDTO.builder()
                .price(399.26)
                .currency("CNY")
                .build();

        ticketInfoResponseDTO = TicketInfoResponseDTO.builder()
                .id(ticketInfoId)
                .price(4600.5)
                .currency("RUB")
                .build();
    }

    @Test
    void testGetTicketInfoById_Found() {
        when(ticketInfoRepository.findById(ticketInfoId)).thenReturn(Optional.of(ticketInfo));
        when(ticketInfoMapper.mapToTicketInfoResponseDTO(ticketInfo)).thenReturn(ticketInfoResponseDTO);

        TicketInfoResponseDTO result = ticketInfoService.getTicketInfoById(ticketInfoId);
        assertNotNull(result);
        assertEquals(ticketInfoId, result.id());
        verify(ticketInfoRepository).findById(ticketInfoId);
    }

    @Test
    void testGetTicketInfoById_NotFound() {
        when(ticketInfoRepository.findById(ticketInfoId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> ticketInfoService.getTicketInfoById(ticketInfoId));
    }

    @Test
    void testGetAllTicketsInfo_Found() {
        List<TicketInfo> ticketInfoList = List.of(ticketInfo);
        when(ticketInfoRepository.findByFilters(any(), any(), any(), any()))
                .thenReturn(ticketInfoList);
        when(ticketInfoMapper.mapToTicketInfoResponseDTO(ticketInfo)).thenReturn(ticketInfoResponseDTO);

        List<TicketInfoResponseDTO> result = ticketInfoService.getAllTicketsInfo(0, 35, null, null, null, null);
        assertEquals(1, result.size());
        verify(ticketInfoRepository).findByFilters(any(), any(), any(), any());
    }

    @Test
    void testGetTicketsInfoCount() {
        when(ticketInfoRepository.count()).thenReturn(13453L);
        assertEquals(13453L, ticketInfoService.getTicketsInfoCount());
    }

    @Test
    void testCreateTicketInfo() {
        when(ticketInfoMapper.mapToTicketInfo(ticketInfoRequestDTO)).thenReturn(ticketInfo);
        when(ticketInfoRepository.save(ticketInfo)).thenReturn(ticketInfo);
        when(ticketInfoMapper.mapToTicketInfoResponseDTO(ticketInfo)).thenReturn(ticketInfoResponseDTO);

        TicketInfoResponseDTO result = ticketInfoService.createTicketInfo(ticketInfoRequestDTO);
        assertNotNull(result);
        assertEquals(ticketInfoId, result.id());
    }

    @Test
    void testUpdateTicketInfoById_Found() {
        when(ticketInfoRepository.findById(ticketInfoId)).thenReturn(Optional.of(ticketInfo));
        when(ticketInfoMapper.mapToTicketInfo(ticketInfoRequestDTO)).thenReturn(ticketInfo);
        when(ticketInfoRepository.save(ticketInfo)).thenReturn(ticketInfo);
        when(ticketInfoMapper.mapToTicketInfoResponseDTO(ticketInfo)).thenReturn(ticketInfoResponseDTO);

        TicketInfoResponseDTO result = ticketInfoService.updateTicketInfo(ticketInfoId, ticketInfoRequestDTO);
        assertNotNull(result);
        assertEquals(ticketInfoId, result.id());
    }

    @Test
    void testUpdateTicketInfoById_NotFound() {
        when(ticketInfoRepository.findById(ticketInfoId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> ticketInfoService.updateTicketInfo(ticketInfoId, ticketInfoRequestDTO));
    }

    @Test
    void testDeleteTicketInfo_Found() {
        when(ticketInfoRepository.existsById(ticketInfoId)).thenReturn(true);
        doNothing().when(ticketInfoRepository).deleteById(ticketInfoId);

        ticketInfoService.deleteTicketInfo(ticketInfoId);

        verify(ticketInfoRepository).existsById(ticketInfoId);
        verify(ticketInfoRepository).deleteById(ticketInfoId);
    }

    @Test
    void testDeleteTicketInfo_NotFound() {
        when(ticketInfoRepository.existsById(ticketInfoId)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> ticketInfoService.deleteTicketInfo(ticketInfoId));
    }

}
