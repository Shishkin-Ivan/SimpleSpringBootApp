package com.aston.intensive.simplespringbootapp.repository;

import com.aston.intensive.simplespringbootapp.model.TicketInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketInfoRepository extends JpaRepository<TicketInfo, UUID> {
    Optional<TicketInfo> findById(UUID id);
}