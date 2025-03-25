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

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, UUID> {

    Optional<Attraction> findById(UUID id);

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
