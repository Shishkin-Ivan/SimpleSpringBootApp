package com.aston.intensive.simplespringbootapp.repository;

import com.aston.intensive.simplespringbootapp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    Optional<Address> findById(UUID id);

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
