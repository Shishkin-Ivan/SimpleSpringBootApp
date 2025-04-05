package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AddressResponseDTO;
import com.aston.intensive.simplespringbootapp.mapper.AddressMapper;
import com.aston.intensive.simplespringbootapp.model.Address;
import com.aston.intensive.simplespringbootapp.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressServiceImpl addressService;

    private UUID addressId;
    private Address address;
    private AddressRequestDTO addressRequestDTO;
    private AddressResponseDTO addressResponseDTO;

    @BeforeEach
    void setUp() throws Exception {
        addressId = UUID.randomUUID();
        address = new Address();
        address.setId(addressId);
        address.setCity("Test City");

        addressRequestDTO = AddressRequestDTO.builder()
                .city("Updated City")
                .build();

        addressResponseDTO = AddressResponseDTO.builder()
                .id(addressId)
                .city("Test City")
                .build();
    }

    @Test
    void testGetAddressById_Found() {
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));
        when(addressMapper.mapToAddressResponseDTO(address)).thenReturn(addressResponseDTO);

        AddressResponseDTO result = addressService.getAddressById(addressId);
        assertNotNull(result);
        assertEquals(addressId, result.id());
        verify(addressRepository).findById(addressId);
    }

    @Test
    void testGetAddressById_NotFound() {
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> addressService.getAddressById(addressId));
    }

    @Test
    void testGetAllAddresses() {
        List<Address> addresses = List.of(address);
        when(addressRepository.findByFilters(any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(addresses);
        when(addressMapper.mapToAddressResponseDTO(address)).thenReturn(addressResponseDTO);

        List<AddressResponseDTO> result = addressService.getAllAddresses(0, 10, null, null, null, null, null, null, null);
        assertEquals(1, result.size());
        verify(addressRepository).findByFilters(any(), any(), any(), any(), any(), any(), any());
    }

    @Test
    void testGetAddressesCount() {
        when(addressRepository.count()).thenReturn(5L);
        assertEquals(5L, addressService.getAddressesCount());
    }

    @Test
    void testCreateAddress() {
        when(addressMapper.mapToAddress(addressRequestDTO)).thenReturn(address);
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.mapToAddressResponseDTO(address)).thenReturn(addressResponseDTO);

        AddressResponseDTO result = addressService.createAddress(addressRequestDTO);
        assertNotNull(result);
        assertEquals(addressId, result.id());
    }

    @Test
    void testUpdateAddress_Found() {
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));
        when(addressMapper.mapToAddress(addressRequestDTO)).thenReturn(address);
        when(addressRepository.save(address)).thenReturn(address);
        when(addressMapper.mapToAddressResponseDTO(address)).thenReturn(addressResponseDTO);

        AddressResponseDTO result = addressService.updateAddress(addressId, addressRequestDTO);
        assertNotNull(result);
        assertEquals(addressId, result.id());
    }

    @Test
    void testUpdateAddress_NotFound() {
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> addressService.updateAddress(addressId, addressRequestDTO));
    }

    @Test
    void testDeleteAddress_Found() {
        when(addressRepository.existsById(addressId)).thenReturn(true);
        doNothing().when(addressRepository).deleteById(addressId);

        addressService.deleteAddress(addressId);

        verify(addressRepository).existsById(addressId);
        verify(addressRepository).deleteById(addressId);
    }

    @Test
    void testDeleteAddress_NotFound() {
        when(addressRepository.existsById(addressId)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> addressService.deleteAddress(addressId));
    }

}
