package app.Util;

import app.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class StorageUtil {
    private static StorageUtil storageUtil = null;

    private StorageUtil() {
    }

    public static StorageUtil getInstance() {
        if (storageUtil == null) {
            storageUtil = new StorageUtil();
        }
        return storageUtil;
    }

    public List<String> header = new ArrayList<>();
    public List<Ticket> ticketList = new ArrayList<>();
    public List<Ticket> validTickets = new ArrayList<>();
    public List<Ticket> inValidTickets = new ArrayList<>();
}
