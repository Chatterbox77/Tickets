import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RailTicketTest {

    @Test
    void getTicketType() {
        RailTicket tck = new RailTicket("mWlOhsJblyNoNMRpAL", "2019/04/17 13:19:57", "City1", "City2", JourneyType.OneWay, TicketType.Adult ,20);
        assertEquals(tck.getTicketType(),TicketType.Adult);
    }

    @Test

    void getJourneyType() {
        RailTicket tck = new RailTicket("mWlOhsJblyNoNMRpAL", "2019/04/17 13:19:57", "City1", "City2", JourneyType.OneWay, TicketType.Adult ,20);
        assertEquals(tck.getJourneyType(),JourneyType.OneWay);
    }

    @Test
    void getPrice() {
        RailTicket tck = new RailTicket("mWlOhsJblyNoNMRpAL", "2019/04/17 13:19:57", "City1", "City2", JourneyType.OneWay, TicketType.Adult ,20);
    assertEquals(tck.getPrice(),20);
    }

    @Test
    void getTicketNo() {
        RailTicket tck = new RailTicket("mWlOhsJblyNoNMRpAL", "2019/04/17 13:19:57", "City1", "City2", JourneyType.OneWay, TicketType.Adult ,20);
        assertEquals(tck.getTicketNo(),"mWlOhsJblyNoNMRpAL");
    }

    @Test
    void getDateIssued() {
        RailTicket tck = new RailTicket("mWlOhsJblyNoNMRpAL", "2019/04/17 13:19:57", "City1", "City2", JourneyType.OneWay, TicketType.Adult ,20);
        assertEquals(tck.getDateIssued(),"2019/04/17 13:19:57");
    }

    @Test
    void getSourceStation() {
        RailTicket tck = new RailTicket("mWlOhsJblyNoNMRpAL", "2019/04/17 13:19:57", "City1", "City2", JourneyType.OneWay, TicketType.Adult ,20);
        assertEquals(tck.getSourceStation(),"City1");
    }

    @Test
    void getDestinationStation() {
        RailTicket tck = new RailTicket("mWlOhsJblyNoNMRpAL", "2019/04/17 13:19:57", "City1", "City2", JourneyType.OneWay, TicketType.Adult ,20);
        assertEquals(tck.getDestinationStation(),"City2");
    }

}