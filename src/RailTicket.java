import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RailTicket {
    private String ticketNo;
    private String dateIssued;
    private String sourceStation;
    private String destinationStation;
    private JourneyType journeyType;
    private TicketType ticketType;
    private int price;

//    Constructor
    public RailTicket(String ticketNo, String dateIssued, String sourceStation, String destinationStation,JourneyType jourType,TicketType tickType,int price) {
        this.ticketNo = ticketNo;
        this.dateIssued = dateIssued;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.price = price;
        this.journeyType = jourType;
        this.ticketType = tickType;
    }

//    Constructor

    public RailTicket(String sourceStation, String destinationStation,JourneyType jourType,TicketType tickType,int price) {
        this.ticketNo = generateTicketNumber(18);
        this.dateIssued = getTimeAndDate();
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.price = price;
        this.journeyType = jourType;
        this.ticketType = tickType;

    }
    //    Returns current date and time as String
    private String getTimeAndDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    //    Generate rundom alphanumeric String of length n
    private String generateTicketNumber(int n)
    {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());


            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
    //  Returns the value stored in int price
    public int getPrice() {
        return price;
    }

    //  Returns the value stored in String ticketNo
    public String getTicketNo() {
        return ticketNo;
    }

    //  Returns the value stored in String dateIssued
    public String getDateIssued() {
        return dateIssued;
    }

    //  Returns the value stored in String sourceStation
    public String getSourceStation() {
        return sourceStation;
    }

    //  Returns the value stored in String destinationStation
    public String getDestinationStation() {
        return destinationStation;
    }

    //  Returns the value stored in JourneyType journeyType
    public JourneyType getJourneyType() {
        return journeyType;
    }

    //  Returns the value stored in TicketType ticketType
    public TicketType getTicketType() {
        return ticketType;
    }


}
