package com.aston.intensive.simplespringbootapp.ticket_info.service.impl;

import com.aston.intensive.simplespringbootapp.ticket_info.controller.dto.request.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.ticket_info.controller.dto.response.TicketInfoResponseDTO;
import com.aston.intensive.simplespringbootapp.mapper.TicketInfoMapper;
import com.aston.intensive.simplespringbootapp.model.TicketInfo;
import com.aston.intensive.simplespringbootapp.ticket_info.repository.TicketInfoRepository;
import com.aston.intensive.simplespringbootapp.ticket_info.service.TicketInfoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of the TicketInfoService interface.
 * This class provides functionality for managing ticket info, including retrieving, creating, updating, and deleting ticket info.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TicketInfoServiceImpl implements TicketInfoService {

    private final TicketInfoRepository ticketInfoRepository;
    private final TicketInfoMapper ticketInfoMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public TicketInfoResponseDTO getTicketInfoById(UUID id) {
        log.debug("Request to get ticket info by id: {}", id);

        TicketInfo ticketInfo = ticketInfoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Ticket info with id {} not found", id);
                    return new EntityNotFoundException("Ticket info with id " + id + " not found");
                });

        return ticketInfoMapper.mapToTicketInfoResponseDTO(ticketInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TicketInfoResponseDTO> getAllTicketsInfo(int pageNumber, int pageSize, UUID id, Double price, String currency, Boolean availability) {
        log.debug("Request to get all tickets info with filters: id={}, price={}, currency={}, availability={}",
                id, price, currency, availability);
        List<TicketInfo> ticketsInfo = ticketInfoRepository.findByFilters(id, price, currency, availability);

        log.debug("Found {} tickets info", ticketsInfo.size());

        return ticketsInfo.stream().skip(pageNumber * pageSize).limit(pageSize)
                .map(ticketInfoMapper::mapToTicketInfoResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public long getTicketsInfoCount() {
        log.debug("Request to count all tickets info");
        long count = ticketInfoRepository.count();
        log.debug("Total tickets info count: {}", count);
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public TicketInfoResponseDTO createTicketInfo(TicketInfoRequestDTO ticketInfoRequestDTO) {
        log.debug("Request to create new ticket info: {}", ticketInfoRequestDTO);

        TicketInfo ticketInfo = ticketInfoMapper.mapToTicketInfo(ticketInfoRequestDTO);
        ticketInfoRepository.save(ticketInfo);

        log.info("Ticket info created with id: {}", ticketInfo.getId());
        return ticketInfoMapper.mapToTicketInfoResponseDTO(ticketInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public TicketInfoResponseDTO updateTicketInfo(UUID id, TicketInfoRequestDTO ticketInfoRequestDTO) {
        log.debug("Request to update ticket info with id: {}", id);

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

            TicketInfo result = ticketInfoRepository.save(ticketInfo);
            log.info("Ticket info with id {} updated successfully", id);
            return ticketInfoMapper.mapToTicketInfoResponseDTO(result);
        }
        log.warn("Ticket info with id {} not found for update", id);
        throw new EntityNotFoundException("Ticket info with id " + id + " not found");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteTicketInfo(UUID id) {
        log.debug("Request to delete ticket info with id: {}", id);

        if (!ticketInfoRepository.existsById(id)) {
            throw new EntityNotFoundException("Ticket info with id " + id + " not found");
        }
        ticketInfoRepository.deleteById(id);
        log.info("Ticket info with id {} deleted successfully", id);
    }

}
