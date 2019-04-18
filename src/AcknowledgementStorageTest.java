import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcknowledgementStorageTest {

    @Test
    void add() {
        AcknowledgementStorage acks = new AcknowledgementStorage();
        int currentSize = acks.size();
        Acknowledgment ack = new Acknowledgment("mWlOhsJblyNoNMRpAL");
        acks.add(ack);
        assertEquals(acks.size(),currentSize + 1);

    }
    @Test
    void size(){
        AcknowledgementStorage acks = new AcknowledgementStorage();
        int currentSize = acks.size();
        Acknowledgment ack = new Acknowledgment("mWlOhsJblyNoNMRpAL");
        acks.add(ack);
        assertEquals(acks.size(),currentSize + 1);
    }
}