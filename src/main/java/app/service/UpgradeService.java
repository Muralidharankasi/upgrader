package app.service;

import app.Util.StorageUtil;
import app.Util.UpgradeUtil;
import app.model.Ticket;

public class UpgradeService {

    private void isValidTicket(Ticket ticket) {
        UpgradeUtil.validateEmail(ticket.getEmail());
        UpgradeUtil.validateMobilePhoneNumber(ticket.getMobilePhone());
        UpgradeUtil.checkTravelDateBeforeTicketingDate(ticket.getTravelDate(), ticket.getTicketingDate());
        UpgradeUtil.validatePNRNumber(ticket.getPnr());
        UpgradeUtil.validateCabin(ticket.getBookedCabin());
        UpgradeUtil.validatePassengersCount(ticket.getPax());
    }

    public void processTicket(Ticket ticket) {
        StorageUtil storageUtil = StorageUtil.getInstance();
        try {
            isValidTicket(ticket);
            ticket.setDiscountCode(getDiscountCode(ticket.getFareClass()));
            storageUtil.validTickets.add(ticket);
        } catch (Exception exception) {
            ticket.setError(exception.getMessage());
            storageUtil.inValidTickets.add(ticket);
        }
    }

    private String getDiscountCode(Character fareClass) {
        if (fareClass >= 'A' && fareClass <= 'Z') {
            if (fareClass > 'R') return "";
            if (fareClass >= 'L') return "OFFER_25";
            if (fareClass >= 'F') return "OFFER_30";
            return "OFFER_20";
        }
        throw new RuntimeException("Fare class Invalid");
    }
}
