import static org.junit.jupiter.api.Assertions.*;

class ApprovalTest {


    @org.junit.jupiter.api.Test
    void setTransactionNo() {
        Approval apr = new Approval("L8n5vIqLPIudum0nXC", "1111111111111111", "111" ,"11/11" ,"Jon Snow", 180);
        apr.setTransactionNo("L8n5vIqLPIudum0nXX");
        assertEquals(apr.getTransactionNo(),"L8n5vIqLPIudum0nXX");
    }

    @org.junit.jupiter.api.Test
    void getTransactionNo() {
        Approval apr = new Approval("L8n5vIqLPIudum0nXC", "1111111111111111", "111" ,"11/11" ,"Jon Snow", 180);
        assertEquals(apr.getTransactionNo(),"L8n5vIqLPIudum0nXC");
    }

    @org.junit.jupiter.api.Test
    void getCardNo() {
        Approval apr = new Approval("L8n5vIqLPIudum0nXC", "1111111111111111", "111" ,"11/11" ,"Jon Snow", 180);

        assertEquals(apr.getCardNo(),"1111111111111111");
    }

    @org.junit.jupiter.api.Test
    void getTransactionAmount() {
        Approval apr = new Approval("L8n5vIqLPIudum0nXC", "1111111111111111", "111" ,"11/11" ,"Jon Snow", 180);

        assertEquals(apr.getTransactionAmount(),180);
    }

    @org.junit.jupiter.api.Test
    void getCvv() {
        Approval apr = new Approval("L8n5vIqLPIudum0nXC", "1111111111111111", "111" ,"11/11" ,"Jon Snow", 180);
        assertEquals(apr.getCvv(),"111");
    }

    @org.junit.jupiter.api.Test
    void getExpireDate() {
        Approval apr = new Approval("L8n5vIqLPIudum0nXC", "1111111111111111", "111" ,"11/11" ,"Jon Snow", 180);
        assertEquals(apr.getExpireDate(),"11/11");
    }

    @org.junit.jupiter.api.Test
    void getCardHolderName() {
        Approval apr = new Approval("L8n5vIqLPIudum0nXC", "1111111111111111", "111" ,"11/11" ,"Jon Snow", 180);
        assertEquals(apr.getCardHolderName(),"Jon Snow");
    }
}