package com.aston.intensive.simplespringbootapp.repository;

import com.aston.intensive.simplespringbootapp.model.Service;
import com.aston.intensive.simplespringbootapp.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for {@link Service} entities.
 * <p>
 * Provides basic CRUD operations via {@link JpaRepository} and custom
 * filtering functionality using optional parameters.
 */
@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {

    /**
     * Retrieves a service by its unique identifier.
     *
     * @param id the UUID of the service
     * @return an {@link Optional} containing the service if found
     */
    Optional<Service> findById(UUID id);

    /**
     * Searches for services that match the given optional filter parameters.
     * <p>
     * Any parameter can be {@code null}, and will be ignored in the filtering.
     * This allows for flexible querying based on available service data (e.g., by name, description, or type).
     *
     * @param id          the UUID of the service (nullable)
     * @param name        the name of the service (nullable)
     * @param description the description of the service (nullable)
     * @param type        the type of service (nullable)
     * @return a list of services matching the non-null filter criteria
     */
    @Query("SELECT a FROM Service a " +
            "WHERE (:id IS NULL OR a.id = :id) " +
            "AND (:name IS NULL OR a.name = :name) " +
            "AND (:description IS NULL OR a.description = :description) " +
            "AND (:type IS NULL OR a.type = :type)")
    List<Service> findByFilters (
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("type") ServiceType type
    );

}
