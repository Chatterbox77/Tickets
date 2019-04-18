import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AcknowledgementStorage {
    private ArrayList<Acknowledgment> storage;
    public void add(Acknowledgment ack){
        storage.add(ack);
    }
//    Returns the size of ArrayList<Acknowledgment> storage
    public int size(){ return storage.size(); }
//    Constructor
    public AcknowledgementStorage(){
        storage = parseAcknowledgements();
    }

//    Saves content of ArrayList<Acknowledgment> storage into txt file
    public void dumpToTxt(){
        try {
            PrintWriter writer = new PrintWriter(new File("Acknowledgements.txt"));
            writer.print("");
            for(Acknowledgment ack:storage){
                writer.write(ack.getAckNo()+"\n");
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
//    parse Acknowledgment from txt file
    private ArrayList<Acknowledgment> parseAcknowledgements(){

            ArrayList<Acknowledgment> acks = new ArrayList<>();
            try{
                Scanner in = new Scanner(new File("Acknowledgements.txt"));
                while(in.hasNextLine()){
                    Scanner lineScanner = new Scanner(in.nextLine());
                    while(lineScanner.hasNext()){
                        String ackNo = lineScanner.next();
                        Acknowledgment ack = new Acknowledgment(ackNo);
                        acks.add(ack);

                    }
                }

            }catch (Exception e){
                e.printStackTrace();
                System.exit(0);
            }
            return acks;

    }

}
