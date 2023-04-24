package app.service;

import app.Util.StorageUtil;
import app.model.Ticket;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CSVFileOperator {

    private final String splitBy = ",";

    public void createFile(String file, List<Ticket> list, List<String> headers) throws IOException {
        System.out.println("Writing " + file);
        deleteFile(file);
        String header = headers.toString();
        header = header.substring(1, header.length() - 1);
        try (FileWriter myWriter = new FileWriter(file); BufferedWriter bw = new BufferedWriter(myWriter)) {
            bw.write(header);
            bw.newLine();
            for (Ticket ticket : list) {
                bw.write(csv(ticket));
                bw.newLine();
            }
        }
    }

    private String csv(Ticket ticket) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ticket.getFirstName()).append(splitBy);
        stringBuilder.append(ticket.getLastName()).append(splitBy);
        stringBuilder.append(ticket.getPnr()).append(splitBy);
        stringBuilder.append(ticket.getFareClass()).append(splitBy);
        stringBuilder.append(ticket.getTravelDate()).append(splitBy);
        stringBuilder.append(ticket.getPax()).append(splitBy);
        stringBuilder.append(ticket.getTicketingDate()).append(splitBy);
        stringBuilder.append(ticket.getEmail()).append(splitBy);
        stringBuilder.append(ticket.getMobilePhone()).append(splitBy);
        stringBuilder.append(ticket.getBookedCabin()).append(splitBy);
        stringBuilder.append(ticket.getDiscountCode() != null ? ticket.getDiscountCode() : ticket.getError());
        return stringBuilder.toString();
    }

    public void readFile(String fileLocation) throws IOException {
        System.out.println("Reading " + fileLocation);
        StorageUtil storageUtilInstance = StorageUtil.getInstance();
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (storageUtilInstance.header.isEmpty()) {
                    storageUtilInstance.header.addAll(Arrays.asList(line.split(splitBy)));
                } else {
                    String[] elements = line.split(splitBy);
                    storageUtilInstance.ticketList.add(getTicket(elements));
                }
            }
        }
    }

    public void deleteFile(String file) {
        new File(file).delete();
    }

    private Ticket getTicket(String[] elements) {
        String firstName = elements[0].trim();
        String lastName = elements[1].trim();
        String pnr = elements[2].trim();
        Character fareClass = elements[3].trim().charAt(0);
        LocalDate travelDate = LocalDate.parse(elements[4].trim());
        String pax = elements[5].trim();
        LocalDate ticketingDate = LocalDate.parse(elements[6].trim());
        String email = elements[7].trim();
        String mobilePhone = elements[8].trim();
        String bookedCabin = elements[9].trim();
        return new Ticket(firstName, lastName, pnr, fareClass, travelDate, Integer.valueOf(pax), ticketingDate, email, mobilePhone, bookedCabin);
    }
}
