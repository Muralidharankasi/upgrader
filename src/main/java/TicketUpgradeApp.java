import app.Util.StorageUtil;
import app.model.Ticket;
import app.service.CSVFileOperator;
import app.service.UpgradeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketUpgradeApp {
    public static void main(String[] args) throws IOException {
        StorageUtil storageUtil = StorageUtil.getInstance();
        CSVFileOperator fileOperator = new CSVFileOperator();
        fileOperator.readFile("tickets.csv");
        UpgradeService upgradeService = new UpgradeService();
        for (Ticket ticket : storageUtil.ticketList) {
            upgradeService.processTicket(ticket);
        }

        System.out.println("Processing invalid Tickets");
        List<String> validCsvHeader = new ArrayList<>(storageUtil.header);
        validCsvHeader.add("Discount_code");
        fileOperator.createFile("valid.csv", storageUtil.validTickets, validCsvHeader);


        System.out.println("Processing valid Tickets");
        List<String> inValidCsvHeader = new ArrayList<>(storageUtil.header);
        inValidCsvHeader.add("Error");
        fileOperator.createFile("invalid.csv", storageUtil.inValidTickets, inValidCsvHeader);
    }
}
