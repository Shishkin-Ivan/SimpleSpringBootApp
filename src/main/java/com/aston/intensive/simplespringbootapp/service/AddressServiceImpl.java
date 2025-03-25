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

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    @Transactional
    public AddressResponseDTO getAddressById(UUID id) {
        log.debug("Find address by id: {}", id);

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            return addressMapper.mapToAddressResponseDTO(address);
        }
        log.debug("No address found with id: {}", id);
        return null;
    }


    @Override
    public List<AddressResponseDTO> findAll(int pageNumber, int pageSize, UUID id, String city, String street, Integer building, String region, Double latitude, Double longitude) {
        log.debug("Find all addresses with filters");

        // Получаем список адресов с фильтрами
        List<Address> addresses = addressRepository.findByFilters(id, city, street, building, region, latitude, longitude);

        return addresses.stream().skip(pageNumber * pageSize).limit(pageSize)
                .map(addressMapper::mapToAddressResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public long getAddressesCount() {
        log.debug("Counting all addresses");
        return addressRepository.count();
    }


    @Override
    @Transactional
    public AddressResponseDTO createAddress(AddressRequestDTO addressRequestDTO) {
        Address address = addressMapper.mapToAddress(addressRequestDTO);
        addressRepository.save(address);
        return addressMapper.mapToAddressResponseDTO(address);
    }

    @Override
    @Transactional
    public AddressResponseDTO updateAddress(UUID id, AddressRequestDTO addressRequestDTO) {
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

            addressRepository.save(address);

            return addressMapper.mapToAddressResponseDTO(address);
        }

        throw new EntityNotFoundException("Address with id " + id + " not found");
    }


    @Override
    @Transactional
    public void deleteAddress(UUID id) {
        addressRepository.deleteById(id);
    }
}
