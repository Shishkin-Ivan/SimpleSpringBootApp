package com.aston.intensive.simplespringbootapp.repository;

import com.aston.intensive.simplespringbootapp.model.TicketInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketInfoRepository extends JpaRepository<TicketInfo, UUID> {

    Optional<TicketInfo> findById(UUID id);

    @Query("SELECT a FROM TicketInfo a " +
            "WHERE (:id IS NULL OR a.id = :id) " +
            "AND (:price IS NULL OR a.price = :price) " +
            "AND (:currency IS NULL OR a.currency = :currency) " +
            "AND (:availability IS NULL OR a.availability = :availability)")
    List<TicketInfo> findByFilters(
            @Param("id") UUID id,
            @Param("price") Double price,
            @Param("currency") String currency,
            @Param("availability") Boolean availability
    );
}