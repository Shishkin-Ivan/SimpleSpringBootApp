package com.aston.intensive.simplespringbootapp.repository;

import com.aston.intensive.simplespringbootapp.model.TicketInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for {@link TicketInfo} entities.
 * <p>
 * Provides basic CRUD operations via {@link JpaRepository} and custom
 * filtering functionality using optional parameters.
 */
public interface TicketInfoRepository extends JpaRepository<TicketInfo, UUID> {

    /**
     * Retrieves a ticket info by its unique identifier.
     *
     * @param id the UUID of the ticket info
     * @return an {@link Optional} containing the ticket info if found
     */
    Optional<TicketInfo> findById(UUID id);

    /**
     * Searches for ticket information entries that match the given optional filter parameters.
     * <p>
     * Each parameter is optional and will be ignored if {@code null}, allowing flexible querying
     * by any combination of ID, price, currency, or availability.
     *
     * @param id           the UUID of the ticket info (nullable)
     * @param price        the ticket price (nullable)
     * @param currency     the ticket currency (nullable)
     * @param availability the availability status (nullable)
     * @return a list of {@link TicketInfo} entries matching the specified filters
     */
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
