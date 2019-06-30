/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import static network.MessageCodes.requestClient;

/**
 *
 * @author esfs
 */
public class SenderThread implements Runnable {

    Thread t;
    DatagramSocket sendingSock;
    DatagramPacket sendingPack;

    public String userName;
    public String serverIpAddress;
//    public String serverName;
    public static String sendingMessage;
    byte[] data;
    public final int serverPort = 5050; // = 5050;
    public int listeningPort;
    public String serverName = "myChessGameServer";
    public String mySERVERNAME = "myChessGameServer" ;

    public SenderThread(String[] s) throws SocketException, UnknownHostException, IOException {
        this.userName = s[0];
        this.listeningPort = Integer.parseInt(s[1]);
        this.serverIpAddress = s[2];
        String msg = "Via: " + serverName + "\nTo: " + serverName + "\nFrom: " + s[0] + "\nPort: " + String.valueOf(s[1]);
        System.out.println("<><><><>Initialising SenderThread......" + msg);
        byte data[];
        InetAddress add = InetAddress.getByName(s[2]);; ///the 3rd argument is the server IP Address
        data = msg.getBytes();
        sendingPack = new DatagramPacket(data, data.length);
        sendingPack.setAddress(add);
        sendingPack.setPort(serverPort);
        sendingPack.setData(data);
        sendingSock = new DatagramSocket();
        sendingSock.send(sendingPack);
        sendingSock.close();
    }

    public void sendToServer() {    //INITIAL MESSAEG
        try {
            String msg = "Via: " + serverName + "\nTo: " + serverName + "\nFrom: " + this.userName + "\nPort: " + String.valueOf(this.listeningPort);
            System.out.println("<><><><>Initialising SenderThread......" + msg);
            byte data[];
            InetAddress add = InetAddress.getByName(ClientGUI.informationOfClient[2]);; ///the 3rd argument is the server IP Address
            data = msg.getBytes();
            sendingPack = new DatagramPacket(data, data.length);
            sendingPack.setAddress(add);
            sendingPack.setPort(serverPort);
            sendingPack.setData(data);
            sendingSock = new DatagramSocket();
            sendingSock.send(sendingPack);
            sendingSock.close();
        } catch (Exception e) {
            System.out.println("PROBLEM IN SENDING INITIAL MESSAGE ");
            System.out.println(e);
        }

    }

    public void sendClientInvitation(String name) {
        try {
            String msg = "Via: " + serverName + "\nTo: " + name + "\nFrom: " + ClientGUI.myName + "\nBody: " + requestClient;
            System.out.println("<><><><>SENDING INVITATION......" + msg);
            byte data[];
            InetAddress add = InetAddress.getByName(serverIpAddress); ///the 3rd argument is the server IP Address
            data = msg.getBytes();
            sendingPack = new DatagramPacket(data, data.length);
            sendingPack.setAddress(add);
            sendingPack.setPort(serverPort);
            sendingPack.setData(data);
            sendingSock = new DatagramSocket();
            sendingSock.send(sendingPack);
            sendingSock.close();
        } catch (IOException e) {
            System.out.println(">>> " + "INSIDE CLEITN INVITAITON   ....");
            e.printStackTrace();
        }
    }

    public void sendRequest() {
        try {
            String msg = "Via: " + serverName + "\nTo: " + ClientGUI.myName + "\nFrom: " + ClientGUI.myName + "\nBody: Request";
            System.out.println("<><><><>SENDING REQUEST......" + msg);
            byte data[];
            InetAddress add = InetAddress.getByName(serverIpAddress); ///the 3rd argument is the server IP Address
            data = msg.getBytes();
            sendingPack = new DatagramPacket(data, data.length);
            sendingPack.setAddress(add);
            sendingPack.setPort(serverPort);
            sendingPack.setData(data);
            sendingSock = new DatagramSocket();
            sendingSock.send(sendingPack);
            sendingSock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        try {
            while (true) {
//                send();
            }
        } catch (Exception e) {
            System.out.println("+++ INside send()");
            e.printStackTrace();
        }
    }

    public void processMessage() {
        int index = sendingMessage.indexOf("$");
        if (index != -1) {
            String toName = sendingMessage.substring(0, index);
            String messageBody = sendingMessage.substring(index + 1);
            sendingMessage = "Via: " + serverName
                    + "\n" + "To: " + toName
                    + "\n" + "From: " + userName
                    + "\n" + "Body: " + messageBody;

        }
    }

//        public void scanMessage() {
//        Scanner sc = new Scanner(System.in);
//        sendingMessage = sc.nextLine();
//    }
}
