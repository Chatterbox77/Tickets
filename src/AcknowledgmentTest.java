import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcknowledgmentTest {

    @Test
    void getAckNo() {
        Acknowledgment ack = new Acknowledgment("mWlOhsJblyNoNMRpAL");
        assertEquals(ack.getAckNo(),"mWlOhsJblyNoNMRpAL");
    }
}