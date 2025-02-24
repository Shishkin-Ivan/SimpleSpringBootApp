package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.TicketInfoRequestDTO;
import com.aston.intensive.simplespringbootapp.mapper.TicketInfoMapper;
import com.aston.intensive.simplespringbootapp.model.TicketInfo;
import com.aston.intensive.simplespringbootapp.repository.TicketInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketInfoServiceImpl implements TicketInfoService {
    private final TicketInfoRepository ticketInfoRepository;
    private final TicketInfoMapper ticketInfoMapper;

    @Override
    public TicketInfoRequestDTO findById(UUID id) {
        log.debug("Find ticket info by id: {}", id);
        Optional<TicketInfo> optionalTicketInfo = ticketInfoRepository.findById(id);
        if (optionalTicketInfo.isPresent()) {
            TicketInfo ticketInfo = optionalTicketInfo.get();
            return ticketInfoMapper.mapToTicketInfoRequestDTO(ticketInfo);
        }
        log.debug("No ticket info found with id: {}", id);
        return null;
    }
}