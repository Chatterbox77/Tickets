import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ApprovalStorage {
    private ArrayList<Approval> storage;
//    Returns the size of ArrayList<Approval> storage;
    public int size(){ return storage.size(); }
//    Constructor
    public ApprovalStorage(){
        storage = parseStorageFile();
    }
//    Adds element to ArrayList<Approval> storage
//    If an element with the same transactionNo is already present remove it first
    public void add(Approval a){
        for(int i = 0;i<storage.size();i++){
            if(storage.get(i).getTransactionNo().equals(a.getTransactionNo())){
                storage.remove(i);
                break;
            }
        }
        storage.add(a);
    }
//  if ArrayList<Approval> storage contains an Approval with the same id as provided in the argument
//  then return this Approval
//  else return null
    public Approval findById(String id){
        for(Approval a:storage){
            if(a.getTransactionNo().equals(id))
                return a;
        }
        return null;
    }
//    Returns true if Approval with the same id as provided in the argument was sucsesfully removed from
//    ArrayList<Approval> storage
//    else return false
    public boolean removeById(String id){
        for(int i=0;i<storage.size();i++){
            if(storage.get(i).getTransactionNo().equals(id)){
                storage.remove(i);
                return true;
            }
        }
        return false;
    }
    //    parse Approvals from txt file
    private ArrayList<Approval> parseStorageFile(){
        ArrayList<Approval> approvals = new ArrayList<>();
        try {
            Scanner in = new Scanner(new File("Approvals.txt"));
            while (in.hasNextLine()){
                Scanner lineScanner = new Scanner(in.nextLine());
                String approvalNo = lineScanner.next();
                String cardNo = lineScanner.next();
                String cvv = lineScanner.next();
                String expireDate = lineScanner.next();
                String holderNamePartA = lineScanner.next();
                String holderName = holderNamePartA + " " + lineScanner.next();
                int amount = lineScanner.nextInt();
                Approval apr = new Approval(approvalNo,cardNo,cvv,expireDate,holderName,amount);
                approvals.add(apr);

            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        return approvals;
    }
    //Saves the content of ArrayList<Approval> storage into txt file
    public void dumpToTxt(){
        try {
            PrintWriter writer = new PrintWriter(new File("Approvals.txt"));
            writer.write("");

            for(Approval a:storage){
                writer.write(a.getTransactionNo() + " " + a.getCardNo() + " " + a.getCvv() +" " + a.getExpireDate() + " " + a.getCardHolderName() + " " + a.getTransactionAmount() +"\n");
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}
