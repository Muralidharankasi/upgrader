package app.Util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UpgradeUtilTest {

    @Test
    void validateEmail_withValidEmail() {
        assertDoesNotThrow(() -> UpgradeUtil.validateEmail("john.doe@example.com"));
    }

    @Test
    void validateEmail_withInvalidEmail() {
        assertThrows(RuntimeException.class, () -> UpgradeUtil.validateEmail("invalid_email.com"));
    }

    @Test
    void validateMobilePhoneNumber_withValidPhoneNumber() {
        assertDoesNotThrow(() -> UpgradeUtil.validateMobilePhoneNumber("9876543210"));
    }

    @Test
    void validateMobilePhoneNumber_withInvalidPhoneNumber() {
        assertThrows(RuntimeException.class, () -> UpgradeUtil.validateMobilePhoneNumber("123"));
    }

    @Test
    void checkTravelDateBeforeTicketingDate_withTravelDateBeforeTicketingDate() {
        LocalDate travelDate = LocalDate.of(2023, 5, 1);
        LocalDate ticketingDate = LocalDate.of(2023, 4, 1);
        assertDoesNotThrow(() -> UpgradeUtil.checkTravelDateBeforeTicketingDate(travelDate, ticketingDate));
    }

    @Test
    void checkTravelDateBeforeTicketingDate_withTravelDateAfterTicketingDate() {
        LocalDate travelDate = LocalDate.of(2023, 4, 1);
        LocalDate ticketingDate = LocalDate.of(2023, 5, 1);
        assertThrows(RuntimeException.class, () -> UpgradeUtil.checkTravelDateBeforeTicketingDate(travelDate, ticketingDate));
    }

    @Test
    void validatePNRNumber_withValidPNR() {
        assertDoesNotThrow(() -> UpgradeUtil.validatePNRNumber("AB12CD"));
    }

    @Test
    void validatePNRNumber_withInvalidPNR() {
        assertThrows(RuntimeException.class, () -> UpgradeUtil.validatePNRNumber("abc"));
    }

    @Test
    void validateCabin_withValidCabin() {
        assertDoesNotThrow(() -> UpgradeUtil.validateCabin("Economy"));
    }

    @Test
    void validateCabin_withInvalidCabin() {
        assertThrows(RuntimeException.class, () -> UpgradeUtil.validateCabin("Invalid Cabin"));
    }

    @Test
    void validatePassengersCount_withValidCount() {
        assertDoesNotThrow(() -> UpgradeUtil.validatePassengersCount(2));
    }

    @Test
    void validatePassengersCount_withInvalidCount() {
        assertThrows(RuntimeException.class, () -> UpgradeUtil.validatePassengersCount(-1));
    }
}
