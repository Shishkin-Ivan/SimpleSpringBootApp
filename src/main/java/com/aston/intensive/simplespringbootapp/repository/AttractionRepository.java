package com.aston.intensive.simplespringbootapp.repository;

import com.aston.intensive.simplespringbootapp.model.Attraction;
import com.aston.intensive.simplespringbootapp.model.AttractionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for {@link Attraction} entities.
 * <p>
 * Provides basic CRUD operations via {@link JpaRepository} and custom
 * filtering functionality using optional parameters.
 */
@Repository
public interface AttractionRepository extends JpaRepository<Attraction, UUID> {

    /**
     * Retrieves an attraction by its unique identifier.
     *
     * @param id the UUID of the attraction
     * @return an {@link Optional} containing the attraction if found
     */
    Optional<Attraction> findById(UUID id);

    /**
     * Searches for attractions that match the given optional filter parameters.
     * <p>
     * Each parameter is optional and will be ignored if {@code null}, allowing flexible querying
     * by any combination of ID, name, description, or type.
     *
     * @param id          the UUID of the attraction (nullable)
     * @param name        the name of the attraction (nullable)
     * @param description the description of the attraction (nullable)
     * @param type        the type of the attraction (nullable)
     * @return a list of {@link Attraction} entries matching the specified filters
     */
    @Query("SELECT a FROM Attraction a " +
            "WHERE (:id IS NULL OR a.id = :id) " +
            "AND (:name IS NULL OR a.name = :name) " +
            "AND (:description IS NULL OR a.description = :description) " +
            "AND (:type IS NULL OR a.type = :type)")
    List<Attraction> findByFilters(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("type") AttractionType type
    );

}
