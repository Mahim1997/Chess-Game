package network;

import java.io.*;
import java.net.*;

public class SenderNormal implements MessageCodes {

    private int serverPort;
    private String serverIpAddress;
    private String serverName;
    private int dataLength;

    public SenderNormal(int serverPort, String serverIpAddress) {
        this.serverPort = serverPort;
        this.serverIpAddress = serverIpAddress;
        this.serverName = "myChessGameServer";
        this.dataLength = 5000;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerIpAddress() {
        return serverIpAddress;
    }

    public void setServerIpAddress(String serverIpAddress) {
        this.serverIpAddress = serverIpAddress;
    }

    public void sendMessageToServer(String msg) {
        try {
            byte[] data = new byte[dataLength];
            DatagramPacket sendingPack = new DatagramPacket(data, data.length);
            DatagramSocket sendingSock = new DatagramSocket();
//            String msg = "Via: " + "myChessGameServer" + "\nTo: " + opponentClient + "\nFrom: " + ClientGUI.myName + "\nBody: " + acceptClient;
            System.out.println("<><><><>SENDING REQUEST......" + msg);
//                byte data[];
            InetAddress add = InetAddress.getByName(serverIpAddress); ///the 3rd argument is the server IP Address
            data = msg.getBytes();
//                sendingPack = new DatagramPacket(data, data.length);
            sendingPack.setAddress(add);
            sendingPack.setPort(serverPort);
            sendingPack.setData(data);
            sendingSock = new DatagramSocket();
            sendingSock.send(sendingPack);
            sendingSock.close();
//            stage.close();
        } catch (IOException e) {
            System.out.println("INSIDE ACCEPTING MESSAGE ........ ");
            e.printStackTrace();
        }
    }

    public void sendRequestMessageToServer() {
        String msg = "Via: " + serverName + "\nTo: " + ClientGUI.myName + "\nFrom: " + ClientGUI.myName + "\nBody: " + "Request";
        sendMessageToServer(msg);

    }

    public void sendMessageToServer(String toClient, String fromClient, String body) {
        String msg = "Via: " + serverName + "\nTo: " + toClient + "\nFrom: " + fromClient + "\nBody: " + body;
        sendMessageToServer(msg);
    }

    public void sendToServer() {    //INITIAL MESSAEG
        String msg = "Via: " + serverName + "\nTo: " + serverName + "\nFrom: " + ClientGUI.myName + "\nPort: " + String.valueOf(ClientGUI.portNo);
        sendMessageToServer(msg);
    }

    public void sendClientInvitation(String name) {
        String msg = "Via: " + serverName + "\nTo: " + name + "\nFrom: " + ClientGUI.myName + "\nBody: " + requestClient;
        sendMessageToServer(msg);
    }
}
