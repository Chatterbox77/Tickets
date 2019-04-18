import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApprovalStorageTest {

    @Test
    void size() {
        ApprovalStorage aps = new ApprovalStorage();
        int currentSize = aps.size();
        Approval ap = new Approval("vabvjboaibwvowiebviwebfou329yf9", "1111111111111111", "111" ,"11/11" ,"Tyrion Lannister", 180);
        aps.add(ap);
        assertEquals(aps.size(),currentSize + 1);
    }

    @Test
    void add() {
        ApprovalStorage aps = new ApprovalStorage();
        int currentSize = aps.size();
        Approval ap = new Approval("vabvjboaibwvowiebviwebfou329yf9", "1111111111111111", "111" ,"11/11" ,"Tyrion Lannister", 180);
        aps.add(ap);
        assertEquals(aps.size(),currentSize + 1);
    }

    @Test
    void findById() {
        ApprovalStorage aps = new ApprovalStorage();

        Approval ap = new Approval("vabvjboaibwvowiebviwebfou329yf9", "1111111111111111", "111" ,"11/11" ,"Tyrion Lannister", 180);
        aps.add(ap);
        Approval foundAp = aps.findById("vabvjboaibwvowiebviwebfou329yf9");
        assertNotNull(foundAp);
    }

    @Test
    void removeById() {
        ApprovalStorage aps = new ApprovalStorage();

        Approval ap = new Approval("vabvjboaibwvowiebviwebfou329yf9", "1111111111111111", "111" ,"11/11" ,"Tyrion Lannister", 180);
        aps.add(ap);
        int currentSize = aps.size();
        aps.removeById("vabvjboaibwvowiebviwebfou329yf9");
        assertEquals(aps.size(),currentSize - 1);
    }
}