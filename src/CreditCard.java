public class CreditCard {
    private String cardNnumber;
    private String expireDate;
    private String cvvCode;
    private String cardHolderName;
    private int amount;


    //    Returns the value stored in String cardNumber
    public String getCardNnumber() {
        return cardNnumber;
    }

    //    Returns the value stored in String expireDate
    public String getExpireDate() {
        return expireDate;
    }

    //    Returns the value stored in String cvvCode
    public String getCvvCode() {
        return cvvCode;
    }

    //    Returns the value stored in String cardHolderName
    public String getCardHolderName() {
        return cardHolderName;
    }


    //    Returns the value stored in int amount
    public int getAmount() {
        return amount;
    }
    //    Deduct given amount from CreditCard and return an Approval
    public Approval deduct(int amount){
        this.amount -= amount;
        return new Approval("",cardNnumber,cvvCode,expireDate,cardHolderName,amount);
    }

//    Constructor
    public CreditCard(String cardNumber, String expireDate, String cvvCode, String cardHolderName,int amount) {


        this.cardNnumber = cardNumber;
        this.expireDate = expireDate;
        this.cvvCode = cvvCode;
        this.cardHolderName = cardHolderName;
        this.amount = amount;
    }
    // Check equality
    boolean equalityCheck(String cardNo,String cvv,String expDate,String holderName){

        return cardNo.equals(cardNnumber) && cvv.equals(cvvCode) && expDate.equals(expireDate) && holderName.equals(cardHolderName);
    }

}
