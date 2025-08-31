package com.aston.intensive.simplespringbootapp.repository;

import com.aston.intensive.simplespringbootapp.TestcontainersConfiguration;
import com.aston.intensive.simplespringbootapp.address.repository.AddressRepository;
import com.aston.intensive.simplespringbootapp.model.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfiguration.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void testFindById_Found() {
        Address address = new Address();
        address.setCity("London");
        address.setStreet("Main Street");
        address.setBuilding(10);
        address.setRegion("LD");
        address.setLatitude(123.2);
        address.setLongitude(123.3);

        addressRepository.save(address);

        Optional<Address> addressOptional = addressRepository.findById(address.getId());
        assertTrue(addressOptional.isPresent());
        assertEquals("London", addressOptional.get().getCity());
        assertEquals("Main Street", addressOptional.get().getStreet());
        assertEquals(10, addressOptional.get().getBuilding());
        assertEquals("LD", addressOptional.get().getRegion());
        assertEquals(123.2, addressOptional.get().getLatitude());
        assertEquals(123.3, addressOptional.get().getLongitude());
    }

    @Test
    void testFindByFilters_NoFilters_ReturnsAll() {
        Address address = new Address();
        address.setCity("London");
        address.setStreet("Main Street");
        address.setBuilding(10);
        address.setRegion("LD");
        address.setLatitude(123.2);
        address.setLongitude(123.3);
        addressRepository.save(address);

        Address address2 = new Address();
        address2.setCity("Saint Petersburg");
        address2.setStreet("Nevsky");
        address2.setBuilding(46);
        address2.setRegion("SPB");
        address2.setLatitude(1232.3);
        address2.setLongitude(322312.3);
        addressRepository.save(address2);

        List<Address> result = addressRepository.findByFilters(null, null, null, null, null, null, null);
        assertEquals(
                2, result.size());
    }

}
