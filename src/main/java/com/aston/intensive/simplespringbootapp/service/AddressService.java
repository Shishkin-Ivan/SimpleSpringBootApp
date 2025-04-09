package com.aston.intensive.simplespringbootapp.service;

import com.aston.intensive.simplespringbootapp.dto.AddressRequestDTO;
import com.aston.intensive.simplespringbootapp.dto.AddressResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * Interface for managing addresses.
 * Provides core operations such as creating, updating, deleting, and retrieving addresses.
 */
public interface AddressService {

    /**
     * Retrieves an address by its unique identifier.
     *
     * @param id the unique identifier of the address to retrieve.
     * @return a DTO object containing the address information.
     * @throws EntityNotFoundException if no address is found with the given ID.
     */
    AddressResponseDTO getAddressById(UUID id);

    /**
     * Retrieves a list of addresses with optional filters, paginated.
     *
     * @param pageNumber the page number.
     * @param pageSize the page size.
     * @param id filter by ID.
     * @param city filter by city.
     * @param street filter by street.
     * @param building filter by building number.
     * @param region filter by region.
     * @param latitude filter by latitude.
     * @param longitude filter by longitude.
     * @return a list of address DTOs matching the filters, paginated.
     */
    List<AddressResponseDTO> getAllAddresses(int pageNumber, int pageSize, UUID id, String city, String street, Integer building,
                                             String region, Double latitude, Double longitude);

    /**
     * Returns the total count of addresses.
     *
     * @return the total number of addresses.
     */
    long getAddressesCount();

    /**
     * Creates a new address based on the provided data.
     *
     * @param addressRequestDTO the DTO containing data for the new address.
     * @return a DTO representing the created address.
     */
    AddressResponseDTO createAddress(AddressRequestDTO addressRequestDTO);

    /**
     * Update an existing address.
     *
     * @param id the address ID to update.
     * @param addressRequestDTO the DTO object with new data for updating the address.
     * @return the DTO object with updated address information.
     * @throws EntityNotFoundException if the address with the given ID is not found.
     */
    AddressResponseDTO updateAddress(UUID id, AddressRequestDTO addressRequestDTO);

    /**
     * Delete an address by ID.
     *
     * @param id the address ID to delete.
     * @throws EntityNotFoundException if the address with the given ID is not found.
     */
    void deleteAddress(UUID id);

}
