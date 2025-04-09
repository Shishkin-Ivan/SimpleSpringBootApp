package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AddressResponseDTO;
import com.aston.intensive.simplespringbootapp.mapper.AddressMapper;
import com.aston.intensive.simplespringbootapp.model.Address;
import com.aston.intensive.simplespringbootapp.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of the AddressService interface.
 * This class provides functionality for managing addresses, including retrieving, creating, updating, and deleting addresses.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public AddressResponseDTO getAddressById(UUID id) {
        log.debug("Request to get address by id: {}", id);

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Address with id {} not found", id);
                    return new EntityNotFoundException("Address with id " + id + " not found");
                });

        log.debug("Successfully found address: {}", address);
        return addressMapper.mapToAddressResponseDTO(address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AddressResponseDTO> getAllAddresses(int pageNumber, int pageSize, UUID id, String city, String street, Integer building, String region, Double latitude, Double longitude) {
        log.debug("Request to get all addresses with filters: id={}, city={}, street={}, building={}, region={}, latitude={}, longitude={}",
                id, city, street, building, region, latitude, longitude);

        List<Address> addresses = addressRepository.findByFilters(id, city, street, building, region, latitude, longitude);

        log.debug("Found {} addresses", addresses.size());

        return addresses.stream().skip(pageNumber * pageSize).limit(pageSize)
                .map(addressMapper::mapToAddressResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public long getAddressesCount() {
        log.debug("Request to count all addresses");
        long count = addressRepository.count();
        log.debug("Total addresses count: {}", count);
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public AddressResponseDTO createAddress(AddressRequestDTO addressRequestDTO) {
        log.debug("Request to create new address: {}", addressRequestDTO);

        Address address = addressMapper.mapToAddress(addressRequestDTO);
        addressRepository.save(address);

        log.info("Address created with id: {}", address.getId());
        return addressMapper.mapToAddressResponseDTO(address);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public AddressResponseDTO updateAddress(UUID id, AddressRequestDTO addressRequestDTO) {
        log.debug("Request to update address with id: {}", id);

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();

            Address updateAddress = addressMapper.mapToAddress(addressRequestDTO);

            if (updateAddress.getBuilding() != null) {
                address.setBuilding(updateAddress.getBuilding());
            }
            if (updateAddress.getCity() != null) {
                address.setCity(updateAddress.getCity());
            }
            if (updateAddress.getLatitude() != null) {
                address.setLatitude(updateAddress.getLatitude());
            }
            if (updateAddress.getLongitude() != null) {
                address.setLongitude(updateAddress.getLongitude());
            }
            if (updateAddress.getRegion() != null) {
                address.setRegion(updateAddress.getRegion());
            }
            if (updateAddress.getStreet() != null) {
                address.setStreet(updateAddress.getStreet());
            }

            Address result = addressRepository.save(address);
            log.info("Address with id {} updated successfully", id);
            return addressMapper.mapToAddressResponseDTO(result);
        }
        log.warn("Address with id {} not found for update", id);
        throw new EntityNotFoundException("Address with id " + id + " not found");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteAddress(UUID id) {
        log.debug("Request to delete address with id: {}", id);

        if (!addressRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent address with id {}", id);
            throw new EntityNotFoundException("Address with id " + id + " not found");
        }

        addressRepository.deleteById(id);
        log.info("Address with id {} deleted successfully", id);
    }

}
