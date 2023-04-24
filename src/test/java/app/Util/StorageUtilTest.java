package app.Util;

import app.model.Ticket;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class StorageUtilTest {

    @Test
    public void testGetInstance() {
        StorageUtil storageUtil1 = StorageUtil.getInstance();
        StorageUtil storageUtil2 = StorageUtil.getInstance();
        Assert.assertEquals(storageUtil1, storageUtil2);
    }

    @Test
    public void testHeader() {
        StorageUtil storageUtil = StorageUtil.getInstance();
        storageUtil.header = Arrays.asList("Column 1", "Column 2");
        Assert.assertEquals(Arrays.asList("Column 1", "Column 2"), storageUtil.header);
    }

    @Test
    public void testTicketList() {
        StorageUtil storageUtil = StorageUtil.getInstance();
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        storageUtil.ticketList.addAll(Arrays.asList(ticket1, ticket2));
        Assert.assertEquals(Arrays.asList(ticket1, ticket2), storageUtil.ticketList);
    }

    @Test
    public void testValidTickets() {
        StorageUtil storageUtil = StorageUtil.getInstance();
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        storageUtil.validTickets.addAll(Arrays.asList(ticket1, ticket2));
        Assert.assertEquals(Arrays.asList(ticket1, ticket2), storageUtil.validTickets);
    }

    @Test
    public void testInValidTickets() {
        StorageUtil storageUtil = StorageUtil.getInstance();
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        storageUtil.inValidTickets.addAll(Arrays.asList(ticket1, ticket2));
        Assert.assertEquals(Arrays.asList(ticket1, ticket2), storageUtil.inValidTickets);
    }

}
