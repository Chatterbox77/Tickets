import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    ArrayList<CreditCard> cards;
// Constructor
    public Bank() {
        this.cards = parseCreditCards();
    }
//    If ArrayList<CreditCard> cards contains CreditCard with the same values as provided
//    then return it
//    else return null
    CreditCard findCreditCard(String cardNo,String cvv,String expDate,String holderName){
        for(CreditCard c:cards){
            if(c.equalityCheck(cardNo,cvv,expDate,holderName)) {

                return c;
            }
        }
        return null;
    }
    //Saves the content of If ArrayList<CreditCard> storage into txt file
    public void dumpToTxt(){
        try {
            PrintWriter writer = new PrintWriter(new File("Bank.txt"));
            writer.print("");
            for(CreditCard c:cards){
                writer.write(c.getCardNnumber() + " " + c.getExpireDate() + " " + c.getCvvCode() + " " + c.getCardHolderName() + " " + c.getAmount()+"\n");
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    private ArrayList<CreditCard> parseCreditCards(){
        ArrayList<CreditCard> cards = new ArrayList<>();
        try{
            Scanner in = new Scanner(new File("Bank.txt"));
            while(in.hasNextLine()){
                Scanner lineScanner = new Scanner(in.nextLine());
                while(lineScanner.hasNext()){

                    String cardNo = lineScanner.next();
                    String expireDate = lineScanner.next();
                    String cvv = lineScanner.next();
                    String name = lineScanner.next();
                    String surname = lineScanner.next();
                    int amount = lineScanner.nextInt();
                    CreditCard card = new CreditCard(cardNo,expireDate,cvv,name + " " +surname,amount);
                    cards.add(card);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        return cards;
    }

}
