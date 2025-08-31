package com.aston.intensive.simplespringbootapp.address.repository;

import com.aston.intensive.simplespringbootapp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for {@link Address} entities.
 * <p>
 * Provides basic CRUD operations via {@link JpaRepository} and custom
 * filtering functionality using optional parameters.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    /**
     * Retrieves an address by its unique identifier.
     *
     * @param id the UUID of the address
     * @return an {@link Optional} containing the address if found
     */
    Optional<Address> findById(UUID id);

    /**
     * Searches for addresses that match the given optional filter parameters.
     * <p>
     * Any parameter can be {@code null}, and will be ignored in the filtering.
     * This allows for flexible querying (e.g., search by city only, or by street and building).
     *
     * @param id        the UUID of the address (nullable)
     * @param city      the city name (nullable)
     * @param street    the street name (nullable)
     * @param building  the building number (nullable)
     * @param region    the region name (nullable)
     * @param latitude  the latitude coordinate (nullable)
     * @param longitude the longitude coordinate (nullable)
     * @return a list of addresses matching the non-null filter criteria
     */
    @Query("SELECT a FROM Address a " +
            "WHERE (:id IS NULL OR a.id = :id) " +
            "AND (:city IS NULL OR a.city = :city) " +
            "AND (:street IS NULL OR a.street = :street) " +
            "AND (:building IS NULL OR a.building = :building) " +
            "AND (:region IS NULL OR a.region = :region) " +
            "AND (:latitude IS NULL OR a.latitude = :latitude) " +
            "AND (:longitude IS NULL OR a.longitude = :longitude)")
    List<Address> findByFilters(
            @Param("id") UUID id,
            @Param("city") String city,
            @Param("street") String street,
            @Param("building") Integer building,
            @Param("region") String region,
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude
    );

}
