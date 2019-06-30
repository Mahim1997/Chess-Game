package network;

import java.io.*;
import java.net.*;
import java.util.*;
import static network.MessageCodes.*;

public class ServerGame implements Runnable {
    private boolean flag = true ;
    private String[] ViaColumn;
    private String[] ToColumn;
    private String[] FromColumn;
    private String[] BodyOrPortColumn;
    private String[] fullMessages;

    private DatagramPacket receivePack;
    private DatagramSocket receiveSock;
    private DatagramPacket sendPack;
    private DatagramSocket sendSock;

    private final int serverPortNo;
    private final int maxDataLength = 5000;

    private String senderClientName;
    private final String serverName;
    private String receivedMessage;

    private String player1Name;
    private ClientInformation player1Info;
    private String player2Name;
    private ClientInformation player2Info;

    private String myClientName;
    private ClientInformation myClientInfo;

    private InetAddress senderClientIp;
    public Map<String, ClientInformation> table = new HashMap<>();
    Map<String, ClientInformation> table2 = new HashMap<>();

    Map<String, ClientInformation> playersPlaying = new HashMap<>();

    public ServerGame(String[] serverName) {
        this.serverName = "myChessGameServer";
        this.serverPortNo = 5050;
        new Thread(this).start();
    }

    public static void main(String[] args) throws UnknownHostException {
        String s[] = new String[1];
        s[0] = "myChessGameServer";
        System.out.println(InetAddress.getLocalHost());
        System.out.println("Console Server starts ..... ");
        new ServerGame(s);
    }

    public void warning(int num) {
        if (num == 0) {
            System.out.println("Warning: Server name mismatch. Message dropped.");
        } else if (num == 1) {
            System.out.println("Warning: Unknown recipient. Message dropped.");
        }
    }

    public void receive() {
        try {
            byte[] data = new byte[maxDataLength];
            receivePack = new DatagramPacket(data, data.length);
            receiveSock = new DatagramSocket(serverPortNo);
            receiveSock.receive(receivePack);
            receivedMessage = new String(receivePack.getData());
            receivedMessage = receivedMessage.trim();
            receiveSock.close(); //Auto close in try and catch block
        } catch (IOException ex) {
            System.out.println("---> Error in server ... lin 41");
        }
        System.out.println("INSIDE SERVER (lin 73): " + receivedMessage + "\n\n");
    }

    public void process() {

        fullMessages = receivedMessage.split("\n");
//        for (int i = 0; i < fullMessages.length; i++) {
//            System.out.println("++>" + (i) + ") " + fullMessages[i]);
//        }

        if (fullMessages.length < 4) {
            System.out.println("Format wrong. Message is ACTUALLY dropped  line85.");
            return;
        }
        ViaColumn = fullMessages[0].split(":");
        if (ViaColumn.length <= 1) {
            myWarning(0);
            return;
        } else {
            ViaColumn[1] = ViaColumn[1].trim();
        }

        ToColumn = fullMessages[1].split(":");
        if (ToColumn.length <= 1) {
            myWarning(1);
            return;
        } else {
            ToColumn[1] = ToColumn[1].trim();
        }

        FromColumn = fullMessages[2].split(":");
        if (FromColumn.length <= 1) {
            myWarning(2);
            return;
        } else {
            FromColumn[1] = FromColumn[1].trim();
        }
        BodyOrPortColumn = fullMessages[3].split(":");
        if (BodyOrPortColumn.length <= 1) {
            myWarning(3);
            return;
        } else {
            BodyOrPortColumn[1] = BodyOrPortColumn[1].trim();
        }
    }

    private void printMessage() {

        System.out.println("----****------");
        System.out.println("SERVER NAME: <<++>" + serverName);
        System.out.println("==>" + ViaColumn[0] + "||" + ViaColumn[1]);
        System.out.println("==>" + ToColumn[0] + "||" + ToColumn[1]);
        System.out.println("==>" + FromColumn[0] + "||" + FromColumn[1]);
        System.out.println("==>" + BodyOrPortColumn[0] + "||" + BodyOrPortColumn[1]);
        for (int i = 3; i < fullMessages.length; i++) {
            System.out.println("===> " + fullMessages[i].trim());
        }
        System.out.println("-----***------");
    }

    private void sendMessage(String msg, InetAddress UserIP, int userPort) {
        try {
            byte data[];
            data = msg.getBytes();
            sendPack = new DatagramPacket(data, data.length, UserIP, userPort);
            sendPack.setData(data);
            sendSock = new DatagramSocket();
            sendSock.send(sendPack);
            sendSock.close();
        } catch (UnknownHostException ex) {
            System.out.println("==> Error in sending to another client from server... lin 115");
        } catch (IOException ex) {
            System.out.println("===> Error in sending to another client from server .. lin 117");
        }
    }

    public void serverRun() {
        receive();
        process();
        printMessage();
        //TO: FROM: BODY: CHECKING TODO

        if (BodyOrPortColumn[0].contains("Deport")) {
            String nameOfClient = FromColumn[1].trim();
            ClientInformation newUser = table.get(nameOfClient);
//            table.remove(newUser, nameOfClient);
            table.remove(nameOfClient, newUser);

        }

        if (ViaColumn[1].equals(serverName) == false) {
            warning(0);
            return;
        } else {
            if (ToColumn[1].equals(serverName) == false) { //TO DO CONDITION FOR ANOTHER CLIENT CHECKING
                String toClient = ToColumn[1];
                ClientInformation toClientInfo = table.get(toClient);
                if (toClientInfo == null) {
                    warning(1);
//                    return;
                } else {
                    if (FromColumn[1].equals(ToColumn[1]) && (BodyOrPortColumn[1].trim().contains("Request"))) {

                        List<String> allNames;  //SET ???
//                            if (table2.containsValue(player1Info) && table.containsValue(player2Info)) {
//                                table2.remove(player1Info, player1Name);
//                                table2.remove(player2Info, player2Name);
//                            }
                        allNames = new ArrayList<>(table.keySet());
//                            allNames = new UnmodifiableListSet<String>((List<String>) table.keySet());

                        byte data[];
                        String ms = "Via: " + serverName + "\nTo: " + myClientName + "\nFrom: Server" + "\nBody: " + requestAcceptedFromServer + "\n";
                        String msg23;
                        msg23 = ms;
                        Object arr[] = allNames.toArray();
                        String x[] = new String[arr.length + 1];
//                            for (int i = 0; i < arr.length; i++) {
//                                x[i] = arr[i].toString() + "\n";
//                                System.out.println("<><><><><> .... " + x[i]);
//                            }
//                            String []x1 = (String)arr ;
//                            String msg23 = "Via: " + serverName + "\nTo: " + myClientName + "\nFrom: Server" + "\nBody: REQUEST Is received";
//                            table
//                                msg23 = table.get(i);
                        for (String s : allNames) {
                            msg23 = msg23 + s;
                            msg23 = msg23.concat("\n");
                        }
                        data = msg23.getBytes();
                        System.out.println("<><>lin162.. OUTGOING MESSAGE IS >>>>..... \n" + msg23);

                        sendMessage(msg23, table.get(FromColumn[1]).userIpAddress, table.get(FromColumn[1]).port);
                    } else {
                        String msg = receivedMessage.trim();
                        sendMessage(msg, toClientInfo.userIpAddress, toClientInfo.port);
                    }
                }
            }
            if (ToColumn[1].equals(serverName) == true) {
                if (BodyOrPortColumn[0].startsWith("Port")) {
                    String newUser = FromColumn[1];
                    String newUserPortString = BodyOrPortColumn[1];
                    InetAddress newUserAddress = receivePack.getAddress();
                    ClientInformation newUserInfo = new ClientInformation(newUserAddress, Integer.parseInt(newUserPortString));
                    myClientInfo = newUserInfo;
                    myClientName = newUser;

                    table.put(newUser, newUserInfo);
                    table2.put(newUser, newUserInfo);   //FOR THE ANOTHER USER

                    String msg23 = "Via: " + serverName + "\nTo: " + myClientName + "\nFrom: Server" + "\nBody: INTIAL IS RECEIVD INSIDE SEREVER";
                    System.out.println("<><>lin206...OUTGOING MESSAGE IS >>>>..... \n" + msg23);
                    sendMessage(msg23, newUserInfo.userIpAddress, newUserInfo.port);

                    //THIS IS DONE TO STORE NEW CLIENT i.e. PROCESS THE FIRST MSG
                } else {
                    //SEND REQUEST BACK MESSAGES
                    if (BodyOrPortColumn[1].contains(requestClient)) {
//                          

                        String ms = "Via: " + serverName + "\nTo: " + myClientName + "\nFrom: " + "Server" + "\nBody: " + requestClient;
                        String msg23;
                        msg23 = ms;
                        System.out.println("<><>lin237...OUTGOING MESSAGE IS >>>>..... \n" + msg23);
                        sendMessage(msg23, myClientInfo.userIpAddress, myClientInfo.port);

                    } else if (BodyOrPortColumn[1].contains(acceptClient)) {
//                          

                        byte data[] = new byte[maxDataLength];
                        String ms = "Via: " + serverName + "\nTo: " + ToColumn[1].trim() + "\nFrom: " + FromColumn[1].trim() + "\nBody: " + acceptClient;
                        String msg23;
                        msg23 = ms;
                        System.out.println("<><>lin237...OUTGOING MESSAGE IS >>>>..... \n" + msg23);
                        String toClient = ToColumn[1].trim();
                        ClientInformation toClientInfo = table.get(toClient);
                        player1Info = toClientInfo;
                        player1Name = toClient;
                        String fromClient = FromColumn[1].trim();
                        ClientInformation fromClientInfo = table.get(fromClient);
                        player2Info = fromClientInfo;
                        player2Name = fromClient;
//                            table2.remove(toClientInfo, toClient);
//                            table2.remove(fromClientInfo, fromClient);
//                            data = "Server: Request is waited".getBytes();
                        sendMessage(msg23, toClientInfo.userIpAddress, toClientInfo.port);

                    } else if (BodyOrPortColumn[1].contains(declineClient)) {

//                          
                        String ms = "Via: " + serverName + "\nTo: " + ToColumn[1].trim() + "\nFrom: " + FromColumn[1].trim() + "\nBody: " + declineClient;
                        String msg23;
                        msg23 = ms;
                        System.out.println("<><>lin237...OUTGOING MESSAGE IS >>>>..... \n" + msg23);
                        String toClient = ToColumn[1].trim();
                        ClientInformation newUserInfo = table.get(toClient);
//                            data = "Server: Request is waited".getBytes();
                        sendMessage(msg23, newUserInfo.userIpAddress, newUserInfo.port);

                    }
                }
            } else if (FromColumn[1].equals(ToColumn[1]) == false) {
//                    boolean flag = false; 
//                    if((ToColumn[1].trim()== null)||(FromColumn[1].trim()= null)){
//                        flag = true  ;
//                    }
                String ms = "Via: " + serverName + "\nTo: " + ToColumn[1].trim() + "\nFrom: " + FromColumn[1].trim() + "\nBody: " + BodyOrPortColumn[1].trim();
//                    for(int i=0; i<messages.length; i++)
                String msg23;
                msg23 = receivedMessage.trim();
                System.out.println("<><>lin237...OUTGOING MESSAGE for NEEDED THING >>>>..... \n" + msg23);
                String toClient = ToColumn[1].trim();
                ClientInformation newUserInfo = table.get(toClient);
                if (newUserInfo == null) {
                    return;
                }
//                    System.out.println(" ");
                sendMessage(ms, newUserInfo.userIpAddress, newUserInfo.port);

            }
        }
    }

    public void myWarning(int num) {
        switch (num) {
            case 0:
                System.out.println("Via field wrong format. Message is dropped.");
                break;
            case 1:
                System.out.println("To filed wrong format. Message is dropped.");
                break;
            case 2:
                System.out.println("From field wrong format. Message is dropped.");
                break;
            case 3:
                System.out.println("Body or Port wrong format. Message is dropped");
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        while (flag) {
            serverRun();

        }
    }

    public boolean isFormatCorrect(String messages[]) {
        if (messages[0].startsWith("Via:") == false) {
            myWarning(0);
            return false;
        } else if (messages[1].startsWith("To:") == false) {
            myWarning(1);
            return false;
        } else if (messages[2].startsWith("From:") == false) {
            myWarning(2);
            return false;
        } else if (messages[3].startsWith("Body:") == false) {
            myWarning(3);
            return false;
        }
        return true;
    }
}

class ClientInformation {

    public InetAddress userIpAddress;
    public int port;

    public String thisUserName ;
    
    public ClientInformation(InetAddress userIpAddress, int port) {
        this.userIpAddress = userIpAddress;
        this.port = port;
    }

    public ClientInformation(InetAddress userIpAddress, int port, String thisUserName) {
        this.userIpAddress = userIpAddress;
        this.port = port;
        this.thisUserName = thisUserName;
    }

}
