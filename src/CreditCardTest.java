import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    @Test
    void getCardNnumber() {
        CreditCard crd = new CreditCard("1111111111111111", "11/11" ,"111", "Ned Stark",39800);
        assertEquals(crd.getCardNnumber(),"1111111111111111");
    }

    @Test
    void getExpireDate() {
        CreditCard crd = new CreditCard("1111111111111111", "11/11" ,"111", "Ned Stark",39800);
        assertEquals(crd.getExpireDate(),"11/11");
    }

    @Test
    void getCvvCode() {
        CreditCard crd = new CreditCard("1111111111111111", "11/11" ,"111", "Ned Stark",39800);
        assertEquals(crd.getCvvCode(),"111");
    }

    @Test
    void getCardHolderName() {
        CreditCard crd = new CreditCard("1111111111111111", "11/11" ,"111", "Ned Stark",39800);
        assertEquals(crd.getCardHolderName(),"Ned Stark");
    }

    @Test
    void getAmount() {
        CreditCard crd = new CreditCard("1111111111111111", "11/11" ,"111", "Ned Stark",39800);
        assertEquals(crd.getAmount(),39800);
    }

    @Test
    void deduct() {
        CreditCard crd = new CreditCard("1111111111111111", "11/11" ,"111", "Ned Stark",39800);
        Approval apr = crd.deduct(1000);

        assertEquals(crd.getAmount(),38800);
        assertNotNull(apr);

    }

    @Test
    void equalityCheck() {
        CreditCard crd1 = new CreditCard("1111111111111111", "11/11" ,"111", "Ned Stark",39800);
        CreditCard crd2 = new CreditCard("1111111111111111", "11/11" ,"111", "Ned Stark",39800);
        assertTrue(crd1.equalityCheck(crd2.getCardNnumber(),crd2.getCvvCode(),crd2.getExpireDate(),crd2.getCardHolderName()));
    }
}