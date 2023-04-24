package app.service;

import app.model.Ticket;
import org.junit.jupiter.api.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVFileOperatorTest {

    private static final String TEST_FILE_PATH = "test.csv";
    private static final String SPLIT_BY = ",";
    private static CSVFileOperator csvFileOperator;
    private static Ticket validTicket;
    private static Ticket invalidTicket;

    @BeforeAll
    public static void setUp() {
        csvFileOperator = new CSVFileOperator();
        validTicket = new Ticket("John", "Doe", "ABC123", 'M', LocalDate.now().plusDays(1), 2,
                LocalDate.now(), "john.doe@example.com", "9834567890", "Economy");
        invalidTicket = new Ticket("Jane", "Doe", "ABC123", 'E', LocalDate.now().plusDays(1), 4, LocalDate.now(), "janedoeexample.com", "9834567890", "Economy");

    }

    @AfterAll
    public static void tearDown() {
        csvFileOperator.deleteFile(TEST_FILE_PATH);
    }

    @Test
    public void testCreateFile() throws IOException {
        List<String> headers = Arrays.asList("FirstName", "LastName", "PNR", "FareClass",
                "TravelDate", "Pax", "TicketingDate", "Email", "MobilePhone", "BookedCabin");

        csvFileOperator.createFile(TEST_FILE_PATH, Collections.singletonList(validTicket), headers);

        BufferedReader br = new BufferedReader(new FileReader(TEST_FILE_PATH));
        String line;
        List<String> actualLines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            actualLines.add(line);
        }
        br.close();

        assertEquals(headers.toString().substring(1, headers.toString().length() - 1), actualLines.get(0));

        Ticket ticket = validTicket;
        String[] actualElements = actualLines.get(1).split(SPLIT_BY);
        assertEquals(ticket.getFirstName(), actualElements[0]);
        assertEquals(ticket.getLastName(), actualElements[1]);
        assertEquals(ticket.getPnr(), actualElements[2]);
        assertEquals(ticket.getFareClass().toString(), actualElements[3]);
        assertEquals(ticket.getTravelDate().toString(), actualElements[4]);
        assertEquals(Integer.toString(ticket.getPax()), actualElements[5]);
        assertEquals(ticket.getTicketingDate().toString(), actualElements[6]);
        assertEquals(ticket.getEmail(), actualElements[7]);
        assertEquals(ticket.getMobilePhone(), actualElements[8]);
        assertEquals(ticket.getBookedCabin(), actualElements[9]);
    }

    @Test
    void testReadFile() throws IOException {
        List<String> headers = Arrays.asList("FirstName", "LastName", "PNR", "FareClass",
                "TravelDate", "Pax", "TicketingDate", "Email", "MobilePhone", "BookedCabin", "DiscountCode");
        Ticket validTicket = CSVFileOperatorTest.validTicket;
        validTicket.setDiscountCode("validTicket");
        csvFileOperator.createFile(TEST_FILE_PATH, Collections.singletonList(validTicket), headers);

        BufferedReader br = new BufferedReader(new FileReader(TEST_FILE_PATH));
        String line;
        List<String> actualLines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            actualLines.add(line);
        }
        br.close();
        assertEquals(headers.toString().substring(1, headers.toString().length() - 1), actualLines.get(0));

        String[] actualElements = actualLines.get(1).split(SPLIT_BY);
        assertEquals(validTicket.getFirstName(), actualElements[0]);
        assertEquals(validTicket.getLastName(), actualElements[1]);
        assertEquals(validTicket.getPnr(), actualElements[2]);
        assertEquals(validTicket.getFareClass().toString(), actualElements[3]);
        assertEquals(validTicket.getTravelDate().toString(), actualElements[4]);
        assertEquals(Integer.toString(validTicket.getPax()), actualElements[5]);
        assertEquals(validTicket.getTicketingDate().toString(), actualElements[6]);
        assertEquals(validTicket.getEmail(), actualElements[7]);
        assertEquals(validTicket.getMobilePhone(), actualElements[8]);
        assertEquals(validTicket.getBookedCabin(), actualElements[9]);
        assertEquals(validTicket.getDiscountCode(), actualElements[10]);
    }
}
