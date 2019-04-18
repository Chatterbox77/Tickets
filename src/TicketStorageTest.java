import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketStorageTest {

    @Test
    void size(){
        TicketStorage ticks = new TicketStorage();
        int currentSize = ticks.size();
        RailTicket tck = new RailTicket("vjbsbevowejdvonveoiwn222222", "2019/04/17 13:19:57", "City1", "City2", JourneyType.OneWay, TicketType.Adult ,20);
        ticks.add(tck);
        assertEquals(ticks.size(),currentSize + 1);
    }

    @Test
    void add() {
        TicketStorage ticks = new TicketStorage();
        int currentSize = ticks.size();
        RailTicket tck = new RailTicket("vjbsbevowejdvonveoiwn222222", "2019/04/17 13:19:57", "City1", "City2", JourneyType.OneWay, TicketType.Adult ,20);
        ticks.add(tck);
        assertEquals(ticks.size(),currentSize + 1);
    }

    @Test
    void removeById() {
        TicketStorage ticks = new TicketStorage();

        RailTicket tck = new RailTicket("vjbsbevowejdvonveoiwn222222", "2019/04/17 13:19:57", "City1", "City2", JourneyType.OneWay, TicketType.Adult ,20);
        ticks.add(tck);
        int currentSize = ticks.size();
        ticks.removeById("vjbsbevowejdvonveoiwn222222");
        assertEquals(ticks.size(),currentSize - 1);
    }

    @Test
    void findById() {
        TicketStorage ticks = new TicketStorage();

        RailTicket tck = new RailTicket("vjbsbevowejdvonveoiwn222222", "2019/04/17 13:19:57", "City1", "City2", JourneyType.OneWay, TicketType.Adult ,20);
        ticks.add(tck);
        RailTicket t = ticks.findById("vjbsbevowejdvonveoiwn222222");
        assertEquals(t.getTicketNo(),"vjbsbevowejdvonveoiwn222222");

    }
}