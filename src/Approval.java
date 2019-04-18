public class Approval {
    private String transactionNo;
    private String cardNo;
    private int transactionAmount;
    private String cvv;
    private String expireDate;
    private String cardHolderName;

    //  Stores the value of arguemnt into String transactionNo;
    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }
    //    Returns the value stored in String transactionNo;
    public String getTransactionNo() {
        return transactionNo;
    }

    //    Returns the value stored in String  cardNo;
    public String getCardNo() {
        return cardNo;
    }
    //    Returns the value stored in int transactionAmount
    public int getTransactionAmount() {
        return transactionAmount;
    }

    //    Returns the value stored in String cvv;
    public String getCvv() {
        return cvv;
    }

    //    Returns the value stored in String expireDate
    public String getExpireDate() {
        return expireDate;
    }
    //    Returns the value stored in String cardHolderName
    public String getCardHolderName() {
        return cardHolderName;
    }
    // Constructor
    public Approval(String transactionNo, String cardNo, String cvv, String expireDate, String cardHolderName, int transactionAmount) {
        this.transactionNo = transactionNo;
        this.cardNo = cardNo;
        this.cvv = cvv;
        this.expireDate = expireDate;
        this.cardHolderName = cardHolderName;
        this.transactionAmount = transactionAmount;

    }
}
