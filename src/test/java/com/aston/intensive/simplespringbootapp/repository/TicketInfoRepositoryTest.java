package com.aston.intensive.simplespringbootapp.repository;

import com.aston.intensive.simplespringbootapp.TestcontainersConfiguration;
import com.aston.intensive.simplespringbootapp.model.TicketInfo;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfiguration.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class TicketInfoRepositoryTest {

    @Autowired
    private TicketInfoRepository ticketInfoRepository;

    @Test
    void testFindById_Found() {
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setPrice(456.88);
        ticketInfo.setCurrency("CNY");
        ticketInfo.setAvailability(true);

        ticketInfoRepository.save(ticketInfo);

        Optional<TicketInfo> ticketInfoOptional = ticketInfoRepository.findById(ticketInfo.getId());
        assertTrue(ticketInfoOptional.isPresent());
        assertEquals(456.88, ticketInfoOptional.get().getPrice());
        assertEquals("CNY", ticketInfoOptional.get().getCurrency());
        assertTrue(ticketInfoOptional.get().getAvailability());
    }

    @Test
    void testFindByFilters_NoFilters_ReturnsAll() {
        TicketInfo ticketInfo = new TicketInfo();
        ticketInfo.setPrice(456.88);
        ticketInfo.setCurrency("CNY");
        ticketInfo.setAvailability(true);
        ticketInfoRepository.save(ticketInfo);

        TicketInfo ticketInfo2 = new TicketInfo();
        ticketInfo2.setPrice(1502.34);
        ticketInfo2.setCurrency("RUB");
        ticketInfo2.setAvailability(false);
        ticketInfoRepository.save(ticketInfo2);

        TicketInfo ticketInfo3 = new TicketInfo();
        ticketInfo3.setPrice(23.7);
        ticketInfo3.setCurrency("USD");
        ticketInfo3.setAvailability(true);
        ticketInfoRepository.save(ticketInfo3);

        TicketInfo ticketInfo4 = new TicketInfo();
        ticketInfo4.setPrice(2874.95);
        ticketInfo4.setCurrency("KZT");
        ticketInfo4.setAvailability(false);
        ticketInfoRepository.save(ticketInfo4);

        List<TicketInfo> result = ticketInfoRepository.findByFilters(null, null, null, null);
        assertEquals(4, result.size());
    }

}
