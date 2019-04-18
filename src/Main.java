import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    private static Station savedSource = null;
    private static Station savedDestination = null;
    private static TicketType savedTicketType = null;
    private static JourneyType savedJourneyType = null;
    private static String nameOnCard = "";
    private static String expireDateText = "";
    private static String cvv = "";
    private static String cardNo = "";
    private static int calculatedCost = 0;
    private static int changeTicketPrice = 0;
    private static RailTicket ticketFound = null;
    private static TicketStorage ticketStorage;
    private static Bank bank;
    private static boolean inChangeMode = false;
    private static ApprovalStorage approvals;
    private static AcknowledgementStorage acknowledgements;

//    Graphics setup

    private static void graphics(){
        bank = new Bank();
        ticketStorage = new TicketStorage();
        approvals = new ApprovalStorage();
        acknowledgements = new AcknowledgementStorage();
        ArrayList<Station> allStations = parseStationFile();
        String[] stationNames = allStations.stream().map(station -> station.getName()).toArray(String[]::new);


        JFrame frame = new JFrame("DRail");
        JPanel panel = new ImagePanel();
        panel.setBackground(Color.darkGray);
        panel.setLayout(null);
        frame.add(panel);
        JButton buyTicket = new JButton("Buy Ticket");

        buyTicket.setBounds(80,100,150, 40);
        buyTicket.setBorderPainted(false);
        buyTicket.setContentAreaFilled(false);
        buyTicket.setOpaque(true);


        JButton changeTicket = new JButton("Change Ticket");
        changeTicket.setBounds(270,100,150,40);
        changeTicket.setBorderPainted(false);
        changeTicket.setContentAreaFilled(false);
        changeTicket.setOpaque(true);

        panel.add(buyTicket);
        panel.add(changeTicket);


        //        BUY TICKET PAGE1 START
        JLabel departureLabel = new JLabel("Departure");
        departureLabel.setBounds(170,100,80,40);
        JComboBox<String> departureBox = new JComboBox<String>(stationNames);
        departureBox.setBounds(270,100,100,40);

        JLabel destinationLabel = new JLabel("Destination");
        destinationLabel.setBounds(170,150,80,40);
        JComboBox<String> destinationBox = new JComboBox<String>(stationNames);
        destinationBox.setBounds(270,150,100,40);

        JButton buyTicketPage1NextBtn = new JButton("Next");
        buyTicketPage1NextBtn.setBounds(170,200,150,40);
        buyTicketPage1NextBtn.setBorderPainted(false);
        buyTicketPage1NextBtn.setContentAreaFilled(false);
        buyTicketPage1NextBtn.setOpaque(true);

        buyTicketPage1NextBtn.setVisible(false);
        departureBox.setVisible(false);
        departureLabel.setVisible(false);
        destinationBox.setVisible(false);
        destinationLabel.setVisible(false);
        panel.add(buyTicketPage1NextBtn);
        panel.add(departureBox);
        panel.add(departureLabel);
        panel.add(destinationBox);
        panel.add(destinationLabel);

        //        BUY TICKET PAGE1 END

        //        BUY TICKET PAGE2 START
        JLabel ticketType = new JLabel("Ticket Type");
        ticketType.setBounds(125,50,100,40);
        ticketType.setVisible(false);
        panel.add(ticketType);

        JComboBox<String> ticketTypeComboBox = new JComboBox<>(new String[]{"Adult","Child"});
        ticketTypeComboBox.setBounds(120,90,100,40);
        ticketTypeComboBox.setVisible(false);
        panel.add(ticketTypeComboBox);

        JLabel journeyType = new JLabel("Journey Type");
        journeyType.setBounds(290,50,100,40);
        journeyType.setVisible(false);
        panel.add(journeyType);

        JComboBox<String> journeyTypeComboBox = new JComboBox<>(new String[]{"One Way","Return"});
        journeyTypeComboBox .setBounds(280,90,110,40);
        journeyTypeComboBox .setVisible(false);
        panel.add(journeyTypeComboBox );

        JButton buyTicketPage2NextBtn = new JButton("Next");
        buyTicketPage2NextBtn.setBorderPainted(false);
        buyTicketPage2NextBtn.setContentAreaFilled(false);
        buyTicketPage2NextBtn.setOpaque(true);
        buyTicketPage2NextBtn.setBounds(210,210,80,40);
        buyTicketPage2NextBtn.setVisible(false);
        panel.add(buyTicketPage2NextBtn);


        //        BUY TICKET PAGE2 END

        //        BUY TICKET PAGE3 START

        JLabel calculatedCoastLabel = new JLabel();
        calculatedCoastLabel.setBounds(20,120,500,50);
        calculatedCoastLabel.setVisible(false);
        panel.add(calculatedCoastLabel);

        JButton buyBtn = new JButton("Buy");
        buyBtn.setBounds(20,180,100,40);
        buyBtn.setBorderPainted(false);
        buyBtn.setContentAreaFilled(false);
        buyBtn.setOpaque(true);
        buyBtn.setVisible(false);
        panel.add(buyBtn);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBorderPainted(false);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setOpaque(true);
        cancelBtn.setBounds(160,180,100,40);
        cancelBtn.setVisible(false);
        panel.add(cancelBtn);


        //        BUY TICKET PAGE3 END


        //        BUY TICKET PAGE4 START

        JLabel cardNoLabel = new JLabel("Card Number: ");
        cardNoLabel.setBounds(20,25,100,40);
        cardNoLabel.setVisible(false);
        panel.add(cardNoLabel);

        JTextField cardNoTextField = new JTextField();
        cardNoTextField.setBounds(120,25,200,40);
        cardNoTextField.setVisible(false);
        panel.add(cardNoTextField);


        JLabel cvvLabel = new JLabel("CVV: ");
        cvvLabel.setBounds(20,75,100,40);
        cvvLabel.setVisible(false);
        panel.add(cvvLabel );

        JTextField cvvTextField = new JTextField();
        cvvTextField.setBounds(120,75,200,40);
        cvvTextField.setVisible(false);
        panel.add(cvvTextField);

        JLabel expireDate = new JLabel("Expire Date: ");
        expireDate.setBounds(20,125,100,40);
        expireDate.setVisible(false);
        panel.add(expireDate );

        JTextField expireDateTextField = new JTextField();
        expireDateTextField.setBounds(120,125,200,40);
        expireDateTextField.setVisible(false);
        panel.add(expireDateTextField);

        JLabel nameOnCardLabel = new JLabel("Name on Card: ");
        nameOnCardLabel.setBounds(20,175,100,40);
        nameOnCardLabel.setVisible(false);
        panel.add(nameOnCardLabel );

        JTextField nameOnCardTextField = new JTextField();
        nameOnCardTextField.setBounds(120,175,200,40);
        nameOnCardTextField.setVisible(false);
        panel.add(nameOnCardTextField);

        JButton buyTicketPage4NextBtn = new JButton("Next");
        buyTicketPage4NextBtn.setBorderPainted(false);
        buyTicketPage4NextBtn.setContentAreaFilled(false);
        buyTicketPage4NextBtn.setOpaque(true);
        buyTicketPage4NextBtn.setBounds(120,225,200,40);
        buyTicketPage4NextBtn.setVisible(false);
        panel.add(buyTicketPage4NextBtn);



        //        BUY TICKET PAGE4 END

        //        BUY TICKET PAGE5 START


        JLabel approvalTextArea = new JLabel();
        approvalTextArea.setBounds(100,80,300,80);
        approvalTextArea.setVisible(false);
        panel.add(approvalTextArea);




        //        BUY TICKET PAGE5 END


        //        CHANGE TICKET PAGE1 START

        JLabel ticketNumberInputLabel = new JLabel("Ticket Number");
        ticketNumberInputLabel.setBounds(210,100,100,40);
        ticketNumberInputLabel.setVisible(false);
        panel.add(ticketNumberInputLabel);

        JTextField ticketNumberInput = new JTextField("");
        ticketNumberInput.setBounds(0,150,500,50);
        ticketNumberInput.setVisible(false);
        panel.add(ticketNumberInput);

        JButton findTicketBtn = new JButton("Find Ticket");
        findTicketBtn.setBorderPainted(false);
        findTicketBtn.setContentAreaFilled(false);
        findTicketBtn.setOpaque(true);
        findTicketBtn.setBounds(30,50,120,50);
        findTicketBtn.setVisible(false);
        panel.add(findTicketBtn);

        JButton changeTicketPage1NextBtn = new JButton("Next");
        changeTicketPage1NextBtn.setBorderPainted(false);
        changeTicketPage1NextBtn.setContentAreaFilled(false);
        changeTicketPage1NextBtn.setOpaque(true);
        changeTicketPage1NextBtn.setBounds(350,50,120,50);
        changeTicketPage1NextBtn.setVisible(false);
        panel.add(changeTicketPage1NextBtn);

        //        CHANGE TICKET PAGE1 END

        buyTicket.addActionListener((onClick) -> {
            resetStoredValues();
            inChangeMode = false;
            buyTicket.setVisible(false);
            changeTicket.setVisible(false);
            departureLabel.setVisible(true);
            departureBox.setVisible(true);
            destinationBox.setVisible(true);
            destinationLabel.setVisible(true);
            buyTicketPage1NextBtn.setVisible(true);
        });
        buyTicketPage1NextBtn.addActionListener((onClick) -> {
            savedSource = allStations.get(departureBox.getSelectedIndex());
            savedDestination = allStations.get(destinationBox.getSelectedIndex());

            departureBox.setVisible(false);
            destinationBox.setVisible(false);
            destinationLabel.setVisible(false);
            departureLabel.setVisible(false);
            buyTicketPage1NextBtn.setVisible(false);
            if(!inChangeMode) {
                ticketType.setVisible(true);
                ticketTypeComboBox.setVisible(true);
                journeyType.setVisible(true);
                journeyTypeComboBox.setVisible(true);
                buyTicketPage2NextBtn.setVisible(true);
            }else{
                calculatedCoastLabel.setVisible(true);
                buyBtn.setVisible(true);
                cancelBtn.setVisible(true);
                try {
                    calculatedCost = computeCost(savedSource,savedDestination,savedTicketType,savedJourneyType);

                    if(calculatedCost < ticketFound.getPrice()){
                        calculatedCoastLabel.setText("You can change your ticket for free");
                    }else{
                        changeTicketPrice = calculatedCost - ticketFound.getPrice();
                        calculatedCoastLabel.setText("You can change your ticket for:\t\t" + changeTicketPrice + " QR");

                    }

                } catch (UnreachableDestinationException e) {
                    calculatedCoastLabel.setText("Err. :\t\t" + " We don't have routes from " + savedSource.getName()+ " to " + savedDestination.getName());
                    buyBtn.setEnabled(false);

                }
            }


        });

        buyTicketPage2NextBtn.addActionListener((onClick) -> {
            ticketType.setVisible(false);
            ticketTypeComboBox.setVisible(false);
            journeyType.setVisible(false);
            journeyTypeComboBox.setVisible(false);
            buyTicketPage2NextBtn.setVisible(false);
            calculatedCoastLabel.setVisible(true);
            buyBtn.setVisible(true);
            cancelBtn.setVisible(true);
            if(ticketTypeComboBox.getSelectedIndex() == 0)
                savedTicketType = TicketType.Adult;
            else
                savedTicketType = TicketType.Child;
            if(journeyTypeComboBox.getSelectedIndex() == 0)
                savedJourneyType = JourneyType.OneWay;
            else
                savedJourneyType = JourneyType.Return;
            try {
                calculatedCost = computeCost(savedSource,savedDestination,savedTicketType,savedJourneyType);
                calculatedCoastLabel.setText("Calculated Payment:\t\t" + calculatedCost + " QR");

            } catch (UnreachableDestinationException e) {
                calculatedCoastLabel.setText("Err. :\t\t" + " We don't have routes from " +savedSource.getName()+ " to " + savedDestination.getName());
                buyBtn.setEnabled(false);

            }
        });
        buyBtn.addActionListener((onClick) -> {
            calculatedCoastLabel.setVisible(false);
            buyBtn.setVisible(false);
            cancelBtn.setVisible(false);
            cardNoLabel.setVisible(true);
            cardNoTextField.setVisible(true);
            cvvLabel.setVisible(true);
            cvvTextField.setVisible(true);
            expireDate.setVisible(true);
            expireDateTextField.setVisible(true);
            nameOnCardLabel.setVisible(true);
            nameOnCardTextField.setVisible(true);
            buyTicketPage4NextBtn.setVisible(true);
            cardNoTextField.setEditable(true);
            cvvTextField.setEditable(true);
            expireDateTextField.setEditable(true);
            nameOnCardTextField.setEditable(true);


            if(inChangeMode){
                Approval a;
                if((a = approvals.findById(ticketFound.getTicketNo())) != null) {
                    cardNoTextField.setText(a.getCardNo());
                    cvvTextField.setText(a.getCvv());
                    expireDateTextField.setText(a.getExpireDate());
                    nameOnCardTextField.setText(a.getCardHolderName());
                    cardNoTextField.setEditable(false);
                    cvvTextField.setEditable(false);
                    expireDateTextField.setEditable(false);
                    nameOnCardTextField.setEditable(false);
                }

            }
        });
        cancelBtn.addActionListener((onClick) -> {
            calculatedCoastLabel.setVisible(false);
            buyBtn.setVisible(false);
            cancelBtn.setVisible(false);
            buyBtn.setEnabled(true);
            buyTicket.setVisible(true);
            changeTicket.setVisible(true);
            resetStoredValues();



        });
        buyTicketPage4NextBtn.addActionListener((onClick) -> {
            nameOnCard = nameOnCardTextField.getText();
            cvv = cvvTextField.getText();
            expireDateText = expireDateTextField.getText();
            cardNo = cardNoTextField.getText();
            cardNoLabel.setVisible(false);
            cardNoTextField.setVisible(false);
            cvvLabel.setVisible(false);
            cvvTextField.setVisible(false);
            expireDate.setVisible(false);
            expireDateTextField.setVisible(false);
            nameOnCardLabel.setVisible(false);
            nameOnCardTextField.setVisible(false);
            buyTicketPage4NextBtn.setVisible(false);
            approvalTextArea.setVisible(true);

            ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
            approvalTextArea.setText("Communicating with Bank Servers...\n");
            exec.schedule(() -> {

//                        CreditCard creditCard = new CreditCard(cardNo, expireDateText, cvv, nameOnCard);
                CreditCard bankCard = bank.findCreditCard(cardNo,cvv,expireDateText,nameOnCard);
                Runnable runnable = () -> {
                    approvalTextArea.setText("");
                    approvalTextArea.setVisible(false);
                    cardNoLabel.setVisible(true);
                    cardNoTextField.setVisible(true);
                    cvvLabel.setVisible(true);
                    cvvTextField.setVisible(true);
                    expireDate.setVisible(true);
                    expireDateTextField.setVisible(true);
                    nameOnCardLabel.setVisible(true);
                    nameOnCardTextField.setVisible(true);
                    buyTicketPage4NextBtn.setVisible(true);
                };
                if(bankCard == null){
                    approvalTextArea.setText("Card rejected! Aborting...\n");

                    exec.schedule(runnable,1,TimeUnit.SECONDS);

                }else{
                    approvalTextArea.setText("Withdrawing money...\n");
                    if(bankCard.getAmount() < calculatedCost){
                        approvalTextArea.setText("Card rejected! Aborting...\n");
                        exec.schedule(runnable,1,TimeUnit.SECONDS);
                    }else {
                        Approval apr = bankCard.deduct(calculatedCost);

                        bank.dumpToTxt();
                        exec.schedule(() -> {
                            approvalTextArea.setText("Waiting for approval...\n");

                            RailTicket ticket = new RailTicket(savedSource.getName(), savedDestination.getName(),savedJourneyType,savedTicketType, calculatedCost);
                            apr.setTransactionNo(ticket.getTicketNo());
                            approvals.add(apr);
                            if(inChangeMode){
                                approvals.removeById(ticketFound.getTicketNo());
                            }
                            approvals.dumpToTxt();





                            exec.schedule(() -> {
                                approvalTextArea.setText("Cummunicating with RSystem...\n");

                                if(inChangeMode){
                                    ticketStorage.removeById(ticketFound.getTicketNo());
                                }
                                ticketStorage.add(ticket);
                                ticketStorage.dumpToTxt();
                                Acknowledgment ack = new Acknowledgment(ticket.getTicketNo());
                                acknowledgements.add(ack);
                                acknowledgements.dumpToTxt();
                                exec.schedule(() -> {
                                    approvalTextArea.setText("Uploading acknowledgment...\n");
                                    exec.schedule(() -> {
                                        approvalTextArea.setText("Ticket issued, Done!...\n");
                                        approvalTextArea.setText("Your Ticket Number is " + ack.getAckNo() + "\n");
                                        exec.schedule(() -> {

                                            approvalTextArea.setVisible(false);
                                            approvalTextArea.setText("");
                                            buyTicket.setVisible(true);
                                            changeTicket.setVisible(true);
                                            approvalTextArea.setText("");

                                            cardNoTextField.setText("");

                                            cvvTextField.setText("");

                                            expireDateTextField.setText("");

                                            nameOnCardTextField.setText("");

                                        },3,TimeUnit.SECONDS);
                                    }, 500, TimeUnit.MILLISECONDS);
                                }, 500, TimeUnit.MILLISECONDS);
                            }, 500, TimeUnit.MILLISECONDS);
                        }, 500, TimeUnit.MILLISECONDS);
                    }


                }}, 500, TimeUnit.MILLISECONDS);


        });
        changeTicket.addActionListener((onClick) -> {
            resetStoredValues();
            inChangeMode = true;
            buyTicket.setVisible(false);
            changeTicket.setVisible(false);
            ticketNumberInputLabel.setVisible(true);
            ticketNumberInput.setVisible(true);
            findTicketBtn.setVisible(true);
            changeTicketPage1NextBtn.setVisible(true);
            changeTicketPage1NextBtn.setEnabled(false);
        });
        findTicketBtn.addActionListener((onClick) -> {

            changeTicketPage1NextBtn.setEnabled(false);
            String ticketNo = ticketNumberInput.getText();
            ticketFound = ticketStorage.findById(ticketNo);

            if(ticketFound == null){
                ticketNumberInput.setText("Err. No Ticket Found");
                ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
                exec.schedule(() ->  ticketNumberInput.setText("") ,2,TimeUnit.SECONDS);
            }else{
                ticketNumberInput.setText("\nTicket - |" + ticketFound.getSourceStation() + "->" + ticketFound.getDestinationStation() + "|" + ticketFound.getJourneyType()+"|" + ticketFound.getTicketType() + "|Issued " + ticketFound.getDateIssued() + "|" + ticketFound.getPrice() + " QR|\n");
                savedJourneyType = ticketFound.getJourneyType();
                savedTicketType = ticketFound.getTicketType();
                changeTicketPage1NextBtn.setEnabled(true);
            }

        });
        changeTicketPage1NextBtn.addActionListener((onClick) -> {
            ticketNumberInput.setText("");
            ticketNumberInputLabel.setVisible(false);
            ticketNumberInput.setVisible(false);
            findTicketBtn.setVisible(false);
            changeTicketPage1NextBtn.setVisible(false);

            departureLabel.setVisible(true);
            departureBox.setVisible(true);
            destinationBox.setVisible(true);
            destinationLabel.setVisible(true);
            buyTicketPage1NextBtn.setVisible(true);
        });


        frame.setResizable(false);
        frame.setSize(500,300);

        frame.setVisible(true);
    }

    //Main driver method
    public static void main(String[] args) {
        graphics();

    }
//    Compute cost from Station src to Station dest
//    throws UnreachableDestinationException in case route doesn't exist
    private static int computeCost(Station src, Station dest, TicketType tickType,JourneyType jourType) throws UnreachableDestinationException{
        Dijkstra.computePaths(src);
        double distance = dest.getMinDistance();
        if(distance <= 0 || distance == Double.POSITIVE_INFINITY)
            throw new UnreachableDestinationException(dest.getName() + " is not reachable from " + src.getName());

        int totalPrice = (int)(distance * 2);
        if(tickType == TicketType.Child)
            totalPrice = (int)(totalPrice * 0.75);
        if(jourType == JourneyType.Return)
            totalPrice *= 2;
        return totalPrice;
    }


    //    parse Station from txt file
   private static ArrayList<Station> parseStationFile(){
       ArrayList<Station> result = new ArrayList<>();
       HashMap<String,Station> map = new HashMap<>();
       ArrayList<String> stationNames = new ArrayList<>();
        try {
            Scanner in = new Scanner(new File("stations.txt"));
            while(in.hasNextLine()){
                Scanner lineScanner = new Scanner(in.nextLine());
                String stationName = lineScanner.next();

                Station station = new Station(stationName);
                map.put(stationName,station);
                stationNames.add(stationName);
            }


            int stationNameIndex = 0;
            in = new Scanner(new File("stations.txt"));
            while(in.hasNextLine()) {

                Scanner lineScanner = new Scanner(in.nextLine());
                lineScanner.next();
                String[] arrayString = lineScanner.next().split("-");
                ArrayList<Edge> edgeList = new ArrayList<>();
                for(String edge:arrayString){

                    String shortened = edge.substring(1,edge.length() - 1);
                    Scanner edgeScanner = new Scanner(shortened);
                    edgeScanner.useDelimiter(",");
                    String nodeName = edgeScanner.next();
                    int edgeWeight = Integer.parseInt(edgeScanner.next());
                    edgeList.add(new Edge(map.get(nodeName),edgeWeight));


                }
                Station s = map.get(stationNames.get(stationNameIndex));
                Edge[] adj = new Edge[edgeList.size()];
                for(int i=0;i<edgeList.size();i++){
                    adj[i] = edgeList.get(i);
                }
                s.setAdjacencies(adj);
                result.add(s);
                stationNameIndex ++;

            }

        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Err. No stations.txt found");
            System.exit(0);
        }
        return result;

    }

//    reset all stored value
    private static void resetStoredValues(){
        savedSource = null;
        savedDestination = null;
        savedTicketType = null;
        savedJourneyType = null;
        nameOnCard = "";
        expireDateText = "";
        cvv = "";
        cardNo = "";
        calculatedCost = 0;
        ticketFound = null;
        changeTicketPrice = 0;

    }
}
class ImagePanel extends JPanel{

    private BufferedImage image;

    ImagePanel() {
        try {
            image = ImageIO.read(new File("train2.png"));
        } catch (IOException ex) {
            // handle exception...
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,500,300,this);

    }

}
