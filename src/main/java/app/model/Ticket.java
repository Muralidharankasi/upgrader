package app.model;

import java.time.LocalDate;

public class Ticket {
    private String firstName;
    private String lastName;
    private String pnr;
    private Character fareClass;
    private LocalDate travelDate;
    private Integer pax;
    private LocalDate ticketingDate;
    private String email;
    private String mobilePhone;
    private String bookedCabin;
    private String discountCode;
    private String error;

    public Ticket(String firstName, String lastName, String pnr, Character fareClass, LocalDate travelDate, Integer pax, LocalDate ticketingDate, String email, String mobilePhone, String bookedCabin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pnr = pnr;
        this.fareClass = fareClass;
        this.travelDate = travelDate;
        this.pax = pax;
        this.ticketingDate = ticketingDate;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.bookedCabin = bookedCabin;
    }

    public Ticket() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public Character getFareClass() {
        return fareClass;
    }

    public void setFareClass(Character fareClass) {
        this.fareClass = fareClass;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public Integer getPax() {
        return pax;
    }

    public void setPax(Integer pax) {
        this.pax = pax;
    }

    public LocalDate getTicketingDate() {
        return ticketingDate;
    }

    public void setTicketingDate(LocalDate ticketingDate) {
        this.ticketingDate = ticketingDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getBookedCabin() {
        return bookedCabin;
    }

    public void setBookedCabin(String bookedCabin) {
        this.bookedCabin = bookedCabin;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pnr='" + pnr + '\'' +
                ", fareClass=" + fareClass +
                ", travelDate=" + travelDate +
                ", pax='" + pax + '\'' +
                ", ticketingDate=" + ticketingDate +
                ", email='" + email + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", bookedCabin='" + bookedCabin + '\'' +
                ", discountCode='" + discountCode + '\'' +
                ", error='" + error + '\'' +
                '}';
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
