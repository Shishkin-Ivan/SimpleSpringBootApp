package com.aston.intensive.simplespringbootapp.repository;

import com.aston.intensive.simplespringbootapp.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServiceRepository extends JpaRepository<Service, UUID> {
    Optional<Service> findById(UUID id);
}