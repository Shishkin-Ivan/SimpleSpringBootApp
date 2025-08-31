package com.aston.intensive.simplespringbootapp.repository;

import com.aston.intensive.simplespringbootapp.TestcontainersConfiguration;
import com.aston.intensive.simplespringbootapp.address.repository.AddressRepository;
import com.aston.intensive.simplespringbootapp.attraction.repository.AttractionRepository;
import com.aston.intensive.simplespringbootapp.model.*;
import com.aston.intensive.simplespringbootapp.service.repository.ServiceRepository;
import com.aston.intensive.simplespringbootapp.ticket_info.repository.TicketInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfiguration.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class AttractionRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TicketInfoRepository ticketInfoRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private AttractionRepository attractionRepository;

    @Test
    void testFindById_Found() {
        Address address = createAddress("City","Street","Region",11,11.1,11.1);
        TicketInfo ticketInfo = createTicketInfo(123.2, "RUB", true);
        Service service = createService("Test Service", "Test Description", ServiceType.TRANSPORT);
        Service service2 = createService("Test Service 2", "Test Description 2", ServiceType.FOOD);

        Attraction attraction = new Attraction();
        attraction.setName("Test Attraction");
        attraction.setDescription("Test Description");
        attraction.setType(AttractionType.CULTURAL);
        attraction.setAddress(address);
        attraction.setTicketInfo(ticketInfo);
        attraction.setServices(Set.of(service, service2));
        attractionRepository.save(attraction);

        Optional<Attraction> attractionOptional = attractionRepository.findById(attraction.getId());
        assertTrue(attractionOptional.isPresent());
        Attraction result = attractionOptional.get();
        assertEquals("Test Attraction", result.getName());
        assertEquals("Test Description", result.getDescription());
        assertEquals(AttractionType.CULTURAL, result.getType());
        assertEquals(address.getCity(), result.getAddress().getCity());
        assertEquals(address.getStreet(), result.getAddress().getStreet());
        assertEquals(ticketInfo.getPrice(), result.getTicketInfo().getPrice());
        assertEquals(ticketInfo.getCurrency(), result.getTicketInfo().getCurrency());
        assertEquals(ticketInfo.getAvailability(), result.getTicketInfo().getAvailability());
        assertEquals(2, result.getServices().size());
    }

    @Test
    void testFindByFilters_NoFilters_ReturnsAll() {
        Address address1 = addressRepository.save(createAddress("City","Street","Region",11,11.1,11.1));
        Address address2 = addressRepository.save(createAddress("City 2","Street 2", "Region 2", 12, 12.2, 12.2));
        Address address3 = addressRepository.save(createAddress("City 3","Street 3", "Region 3", 13, 13.3, 13.3));
        TicketInfo ticketInfo1 = ticketInfoRepository.save(createTicketInfo(111.11,"RUB",true));
        TicketInfo ticketInfo2 = ticketInfoRepository.save(createTicketInfo(2222.22, "KZT", false));
        TicketInfo ticketInfo3 = ticketInfoRepository.save(createTicketInfo(3333.333, "USD", false));
        Service service1 = serviceRepository.save(createService("Test Service","Test Description", ServiceType.TRANSPORT));
        Service service2 = serviceRepository.save(createService("Test Service 2", "Test Description 2", ServiceType.FOOD));
        Service service3 = serviceRepository.save(createService("Test Service 3", "Test Description 3", ServiceType.EDUCATIONAL));

        Attraction attraction = new Attraction();
        attraction.setName("Test Attraction");
        attraction.setDescription("Test Description");
        attraction.setType(AttractionType.CULTURAL);
        attraction.setAddress(address1);
        attraction.setTicketInfo(ticketInfo1);
        attraction.setServices(Set.of(service1));
        attractionRepository.save(attraction);

        Attraction attraction2 = new Attraction();
        attraction2.setName("Test Attraction 2");
        attraction2.setDescription("Test Description 2");
        attraction2.setType(AttractionType.ENTERTAINMENT);
        attraction2.setAddress(address2);
        attraction2.setTicketInfo(ticketInfo2);
        attraction2.setServices(Set.of(service1, service2));
        attractionRepository.save(attraction2);

        Attraction attraction3 = new Attraction();
        attraction3.setName("Test Attraction 3");
        attraction3.setDescription("Test Description 3");
        attraction3.setType(AttractionType.PALACE);
        attraction3.setAddress(address3);
        attraction3.setTicketInfo(ticketInfo3);
        attraction3.setServices(Set.of(service1, service2, service3));
        attractionRepository.save(attraction3);

        List<Attraction> result = attractionRepository.findByFilters(null, null, null, null);
        assertEquals(3, result.size());
    }

    private Address createAddress(String city, String street, String region, Integer building, Double latitude, Double longitude) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setRegion(region);
        address.setBuilding(building);
        address.setLatitude(latitude);
        address.setLongitude(longitude);
        return addressRepository.save(address);
    }

    private TicketInfo createTicketInfo(Double price, String currency, boolean availability) {
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setPrice(price);
        ticketInfo.setCurrency(currency);
        ticketInfo.setAvailability(availability);
        return ticketInfoRepository.save(ticketInfo);
    }

    private Service createService(String name, String description, ServiceType type) {
        Service service = new Service();
        service.setName(name);
        service.setDescription(description);
        service.setType(type);
        return serviceRepository.save(service);
    }

}
