package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.mapper.AddressMapper;
import com.aston.intensive.simplespringbootapp.model.Address;
import com.aston.intensive.simplespringbootapp.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressRequestDTO findById(UUID id) {
        log.debug("Find address by id: {}", id);

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            return addressMapper.mapToAddressRequestDTO(address);
        }
        log.debug("No address found with id: {}", id);
        return null;
    }
}