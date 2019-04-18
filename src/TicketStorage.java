import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketStorage {
    private ArrayList<RailTicket> storage;

    // returns the size of ArrayList<RailTicket> storage;
    public int size() { return storage.size(); }

    //Constructor
    public TicketStorage() {
        storage = parseStorageFile();
    }

    //Saves the content of ArrayList<RailTicket> storage into txt file
    public void dumpToTxt(){
        try {
            PrintWriter writer = new PrintWriter(new File("TicketStorage.txt"));
            writer.print("");
            for(RailTicket c:storage){
                int jourType = c.getJourneyType() == JourneyType.OneWay ? 0 : 1;
                int tickType = c.getTicketType() == TicketType.Adult ? 0 : 1;
                writer.write(c.getTicketNo() + " " + c.getDateIssued() + " " + c.getSourceStation() + " " + c.getDestinationStation() + " " + jourType + " " + tickType + " " + c.getPrice()+"\n");
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    //Add an element of type RailTicket to ArrayList<RailTicket> storage
    public void add(RailTicket t){
        this.storage.add(t);
    }

    //    Returns true if RailTicket with the same id as provided in the argument was sucsesfully removed from
    //    ArrayList<RailTicket> storage
    //    else return false
    public boolean removeById(String id){
        for(int i=0;i<storage.size();i++){
            if(storage.get(i).getTicketNo().equals(id)){
                storage.remove(i);
                return true;
            }
        }
        return false;
    }

    //  if ArrayList<railTicket> storage contains RailTicket with the same id as provided in the argument
    //  then return this RailTicket
    //  else return null
    public RailTicket findById(String id){
        for(RailTicket t:storage){

            if(t.getTicketNo().equals(id))
                return t;
        }
        return null;
    }

    //    parse RailTickets from txt file
    private ArrayList<RailTicket> parseStorageFile(){

        ArrayList<RailTicket> storage = new ArrayList<>();
        try{
            Scanner in = new Scanner(new File("TicketStorage.txt"));
            while(in.hasNextLine()){
                Scanner lineScanner = new Scanner(in.nextLine());
                while (lineScanner.hasNext()){
                    String ticketNumber = lineScanner.next();
                    String dateIssuedPartA = lineScanner.next();
                    String dateIssuedPartB = lineScanner.next();
                    String dateIssued = dateIssuedPartA + " " + dateIssuedPartB;
                    String source = lineScanner.next();
                    String dest = lineScanner.next();
                    JourneyType journeyType = lineScanner.nextInt() == 0 ? JourneyType.OneWay : JourneyType.Return;
                    TicketType tickType = lineScanner.nextInt() == 0 ? TicketType.Adult: TicketType.Child;

                    int price = lineScanner.nextInt();
                    RailTicket ticket = new RailTicket(ticketNumber,dateIssued,source,dest,journeyType,tickType,price);
                    storage.add(ticket);

                }

            }

        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        return storage;
    }

}
