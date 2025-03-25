package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.TicketInfoResponseDTO;
import com.aston.intensive.simplespringbootapp.mapper.TicketInfoMapper;
import com.aston.intensive.simplespringbootapp.model.TicketInfo;
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
public class TicketInfoServiceImpl implements TicketInfoService {

    private final TicketInfoRepository ticketInfoRepository;
    private final TicketInfoMapper ticketInfoMapper;

    @Override
    public TicketInfoResponseDTO getTicketInfoById(UUID id) {
        log.debug("Find ticket info by id: {}", id);

        Optional<TicketInfo> optionalTicketInfo = ticketInfoRepository.findById(id);
        if (optionalTicketInfo.isPresent()) {
            TicketInfo ticketInfo = optionalTicketInfo.get();
            return ticketInfoMapper.mapToTicketInfoResponseDTO(ticketInfo);
        }
        log.debug("No ticket info found with id: {}", id);
        return null;
    }

    @Override
    public List<TicketInfoResponseDTO> getAllTicketsInfo(int pageNumber, int pageSize, UUID id, Double price, String currency, Boolean availability) {
        log.debug("Find all tickets-info with filters");

        List<TicketInfo> ticketsInfo = ticketInfoRepository.findByFilters(id, price, currency, availability);

        return ticketsInfo.stream().skip(pageNumber * pageSize).limit(pageSize)
                .map(ticketInfoMapper::mapToTicketInfoResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public long getTicketsInfoCount() {
        log.debug("Counting all tickets-info");
        return ticketInfoRepository.count();
    }

    @Override
    @Transactional
    public TicketInfoResponseDTO createTicketInfo(TicketInfoRequestDTO ticketInfoRequestDTO) {
        TicketInfo ticketInfo = ticketInfoMapper.mapToTicketInfo(ticketInfoRequestDTO);
        ticketInfoRepository.save(ticketInfo);
        return ticketInfoMapper.mapToTicketInfoResponseDTO(ticketInfo);
    }

    @Override
    @Transactional
    public TicketInfoResponseDTO updateTicketInfo(UUID id, TicketInfoRequestDTO ticketInfoRequestDTO) {
        Optional<TicketInfo> optionalTicketInfo = ticketInfoRepository.findById(id);
        if (optionalTicketInfo.isPresent()) {
            TicketInfo ticketInfo = optionalTicketInfo.get();
            TicketInfo updateTicketInfo = ticketInfoMapper.mapToTicketInfo(ticketInfoRequestDTO);

            if (updateTicketInfo.getPrice() != null) {
                ticketInfo.setPrice(updateTicketInfo.getPrice());
            }
            if (updateTicketInfo.getCurrency() != null) {
                ticketInfo.setCurrency(updateTicketInfo.getCurrency());
            }
            if (updateTicketInfo.getAvailability() != null) {
                ticketInfo.setAvailability(updateTicketInfo.getAvailability());
            }
            ticketInfoRepository.save(ticketInfo);
            return ticketInfoMapper.mapToTicketInfoResponseDTO(updateTicketInfo);
        }
        throw new EntityNotFoundException("Ticket-info with id " + id + " not found");
    }

    @Override
    @Transactional
    public void deleteTicketInfo(UUID id) {
        ticketInfoRepository.deleteById(id);
    }
}
