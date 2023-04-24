package app.Util;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpgradeUtil {


    private static final Pattern VALID_EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_MOBILE_NUMBER = Pattern.compile("[7-9]\\d{9}");
    private static final Pattern VALID_PNR = Pattern.compile("[0-9a-zA-Z]{6}");

    public static void validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_PATTERN.matcher(email);
        assertMatch(matcher, "Email invalid");
    }

    public static void validateMobilePhoneNumber(String mobilePhoneNumber) {
        Matcher matcher = VALID_MOBILE_NUMBER.matcher(mobilePhoneNumber);
        assertMatch(matcher, "Phone number invalid");
    }


    public static void checkTravelDateBeforeTicketingDate(LocalDate travelDate, LocalDate ticketingDate) {
        boolean before = ticketingDate.isBefore(travelDate);
        if (!before) throw new RuntimeException("Travel date must be after ticketing date");
    }

    public static void validatePNRNumber(String pnr) {
        Matcher matcher = VALID_PNR.matcher(pnr);
        assertMatch(matcher, "PNR invalid");
    }

    public static void validateCabin(String bookedCabin) {
        try {
            bookedCabin = bookedCabin.replace(" ", "");
            Cabin.valueOf(bookedCabin);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Cabin Invalid");
        }
    }

    private static void assertMatch(Matcher matcher, String error) {
        if (!matcher.matches()) throw new RuntimeException(error);
    }

    public static void validatePassengersCount(Integer pax) {
        if (pax < 1) throw new RuntimeException("Pax invalid");
    }
}
