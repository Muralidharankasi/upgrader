package app.service;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import app.Util.StorageUtil;
import app.model.Ticket;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class UpgradeServiceTest {

    private UpgradeService upgradeService;
    private Ticket validTicket;
    private Ticket invalidTicket;

    @Before
    public void setUp() {
        upgradeService = new UpgradeService();
        validTicket = new Ticket("John", "Doe", "ABC123", 'M', LocalDate.now().plusDays(1), 2,
                LocalDate.now(), "john.doe@example.com", "9834567890", "Economy");
        invalidTicket = new Ticket("Jane", "Doe", "ABC123", 'E', LocalDate.now().plusDays(1), 4, LocalDate.now(), "janedoeexample.com", "9834567890", "Economy");

    }

    @Test
    public void testProcessValidTicket() {
        // Given
        Ticket ticket = new Ticket();
        ticket.setEmail("john.doe@example.com");
        ticket.setMobilePhone("9834567890");
        ticket.setTravelDate(LocalDate.now().plusDays(1));
        ticket.setTicketingDate(LocalDate.now());
        ticket.setPnr("ABC123");
        ticket.setBookedCabin("Economy");
        ticket.setPax(1);
        ticket.setFareClass('M');

        // When
        upgradeService.processTicket(ticket);

        // Then
        StorageUtil storageUtil = StorageUtil.getInstance();
        assertTrue(storageUtil.validTickets.contains(ticket));
        assertFalse(storageUtil.inValidTickets.contains(ticket));
        assertEquals("OFFER_25", ticket.getDiscountCode());
        assertNull(ticket.getError());
    }

    @Test
    public void testProcessInvalidTicket() {
        // Given
        Ticket ticket = new Ticket();
        ticket.setEmail("jane.doe@example.com");
        ticket.setMobilePhone("1234567890");
        ticket.setTravelDate(LocalDate.now().plusDays(1));
        ticket.setTicketingDate(LocalDate.now());
        ticket.setPnr("ABC123");
        ticket.setBookedCabin("Economy");
        ticket.setPax(1);
        ticket.setFareClass('M');

        // When
        upgradeService.processTicket(ticket);

        // Then
        StorageUtil storageUtil = StorageUtil.getInstance();
        assertTrue(storageUtil.inValidTickets.contains(ticket));
        assertFalse(storageUtil.validTickets.contains(ticket));
        assertNull(ticket.getDiscountCode());
        assertEquals("Phone number invalid", ticket.getError());
    }

    @Test
    public void processTicket_withValidTicket_shouldAddToValidTicketsList() {
        upgradeService.processTicket(validTicket);
        assertTrue(StorageUtil.getInstance().validTickets.contains(validTicket));
    }

    @Test
    public void processTicket_withInvalidTicket_shouldAddToInvalidTicketsList() {
        upgradeService.processTicket(invalidTicket);
        assertTrue(StorageUtil.getInstance().inValidTickets.contains(invalidTicket));
    }

    @Test
    public void processTicket_withInvalidEmail_shouldSetError() {
        validTicket.setEmail("invalidemail@email");
        upgradeService.processTicket(validTicket);
        Assertions.assertNotNull(validTicket.getError());
    }

    @Test
    public void processTicket_withInvalidMobileNumber_shouldSetError() {
        validTicket.setMobilePhone("12345");
        upgradeService.processTicket(validTicket);
        Assertions.assertNotNull(validTicket.getError());
    }

    @Test
    public void processTicket_withTravelDateAfterTicketingDate_shouldSetError() {
        validTicket.setTravelDate(LocalDate.parse("2023-04-01"));
        validTicket.setTicketingDate(LocalDate.parse("2023-05-01"));
        upgradeService.processTicket(validTicket);
        Assertions.assertNotNull(validTicket.getError());
    }

    @Test
    public void processTicket_withInvalidPNRNumber_shouldSetError() {
        validTicket.setPnr("pnr123$");
        upgradeService.processTicket(validTicket);
        Assertions.assertNotNull(validTicket.getError());
    }

    @Test
    public void processTicket_withInvalidCabin_shouldSetError() {
        validTicket.setBookedCabin("invalid cabin");
        upgradeService.processTicket(validTicket);
        Assertions.assertNotNull(validTicket.getError());
    }

    @Test
    public void processTicket_withInvalidPassengersCount_shouldSetError() {
        validTicket.setPax(0);
        upgradeService.processTicket(validTicket);
        Assertions.assertNotNull(validTicket.getError());
    }
}
