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
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import static network.PlayerInitialiser.createWhiteStandardBoard;

/**
 *
 * @author esfs
 */
class Sender implements Runnable, MessageCodes {

    private String serverIPAddress;
    private final int serverPort = 5050;
    private DatagramSocket sock;
    private DatagramPacket pack;
    byte[] data;
    private Thread t;
    private String messageToBeSent;

    public Sender(String serverIP) {
        this.serverIPAddress = serverIP;
//        this.messageToBeSent = msg ;
        t = new Thread(this, "Sender");
        t.start();
        data = new byte[5000];
    }

    public void sendAlreadyPlayingMessage(String msgToBeSent) {
        messageToBeSent = msgToBeSent;
        try {
            sock = new DatagramSocket();
            data = messageToBeSent.getBytes();
            pack = new DatagramPacket(data, data.length, InetAddress.getByName(serverIPAddress), serverPort);
            sock.send(pack);
            sock.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
//    public void sendMessage(String )

    @Override
    public void run() {
        System.out.println("Do nothing in run of Sender");
    }

}

public class ReceiverThread implements Runnable {

//    SenderThread sender;
    Sender sender;
    DatagramSocket receivingSock;
    DatagramPacket receivingPack;
    private final int maxDataLength = 5000;
    public static String receivedMessage;
    public int listeningPort;
    public static List<String> listOfParticipants = new ArrayList<>();

    private final int serverPort = 5050;
    private String serverIPAdress = "127.0.0.1";

    WindowScreens windowDisplayer;

    public ReceiverThread(String[] s) {
        System.out.println(".... Receiver Thread starts..");
        this.listeningPort = Integer.parseInt(s[1]);
        this.serverIPAdress = s[2];
        System.out.println("THIS IS s[1] (inside receiverThread).... " + s[1]);
        sender = new Sender(s[2]);  //Server IP Address
        windowDisplayer = new WindowScreens();
    }

    public void recieve() {
        byte[] data = new byte[maxDataLength];
        try {

//            receivingSock = new DatagramSocket(listeningPort);
            receivingPack = new DatagramPacket(data, data.length);
            receivingSock = new DatagramSocket(listeningPort);
            receivingSock.receive(receivingPack);
            receivedMessage = new String(receivingPack.getData());
            receivingSock.close();
//            System.out.println("INSIDE RECEIVER .......\n" + receivedMessage.trim());
//            Text newText =new Text(receivedMessage.trim());
//            ClientGUI.text4.getChildren().add(newText);
//            ClientGUI.text4
        } catch (SocketException ex) {
            System.out.println("++> Error in building socket in receive in CLIENT ...lin 61");
        } catch (IOException ex) {
            System.out.println("++> Error in CLIENT ... lin 63");
        }

        process();
    }

//    public void printOnly() {
//        System.out.println("<><>INSIDE RECEIVER NEW FUNCTUION....\n" + receivedMessage.trim());
//    }
    static boolean gameStarted = false;
    public static String forCheckingPurposes;
    public static String MOVE_MESSAGE_FROM_RECEIVER_THREAD;

    public void process() {
        String messages[] = receivedMessage.split("\n");
//        System.out.println(">>> ()() INSIDE void process() .......... \n" + receivedMessage.trim());
        forCheckingPurposes = receivedMessage.trim();
        System.out.println("----****------\n");
        if (messages.length != 4) {
            System.out.println("Format wrong. Message is NOT dropped.");
//            return;
        }

        String isolatedVia[] = messages[0].split(":");
        if (isolatedVia.length <= 1) {
            return;
        } else {
            isolatedVia[1] = isolatedVia[1].trim();
        }

        String isolatedTo[] = messages[1].split(":");
        if (isolatedTo.length <= 1) {
            return;
        } else {
            isolatedTo[1] = isolatedTo[1].trim();
        }

        String isolatedFrom[] = messages[2].split(":");
        if (isolatedFrom.length <= 1) {
            return;
        } else {
            isolatedFrom[1] = isolatedFrom[1].trim();
        }
        String isolatedPortOrBody[] = messages[3].split(":");
        if (isolatedPortOrBody.length <= 1) {
            System.out.println("No body invalid INSIDE RECEIVER THREAD!");
            return;
        } else {
            isolatedPortOrBody[1] = isolatedPortOrBody[1].trim();
        }
//        String newMsg24 = isolatedFrom[1] + " says: " + isolatedPortOrBody[1];
        System.out.println(isolatedFrom[1] + " says: " + isolatedPortOrBody[1] + " ........ INSIDE RECEIVEIREIER");
        String hereNewMsg = isolatedFrom[1] + "says: " + isolatedPortOrBody[1];
        if (isolatedPortOrBody[1].contains("Move")) {
            if ((isolatedPortOrBody[1].contains("THIS_IS_WHITE"))) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        System.out.println("THIS SHOULD WORK ,.,., () ");
                        Player.MOVE_MESSAGE = isolatedPortOrBody[1].trim();
//                    WhitePlayer_1.setAllPiecesFromReceivedMessage();
                        MOVE_MESSAGE_FROM_RECEIVER_THREAD = isolatedPortOrBody[1].trim();
//                        Player.updateBoardAfterReceivedMessage();

                        Player.MOVE_MESSAGE = isolatedPortOrBody[1].trim();
                        Player.updateBoardAfterReceivedMessage();
                        Player.isWhiteMove = false;
                        Player.isBlackMove = true;
                        PlayerInitialiser.labelForWhoseMoveNow.setText("To Move: " + ((Player.isWhiteMove) ? "WHITE" : "BLACK"));
                        System.out.println("inside THIS_IS_WHITE clause ..... " + Player.MOVE_MESSAGE);

                        //UPDATED SATURDAY
                        //BlackPlayerInitialiser.receiveMove.fire();
//                        Player.setImage(Player.indicatorImage, 5, 7);
//                    Player.MOVE_MESSAGE = isolatedPortOrBody[1].trim();
////                    WhitePlayer_1.setAllPiecesFromReceivedMessage();
//                    MOVE_MESSAGE_FROM_RECEIVER_THREAD = isolatedPortOrBody[1].trim();
//                    Player.updateBoardAfterReceivedMessage();
//                    
//                    
//                    Player.MOVE_MESSAGE = isolatedPortOrBody[1].trim();
//                    Player.updateBoardAfterReceivedMessage();
//                    WhitePlayer_1.updateAllPiecesAFTER_receivedMessage();
                    }
                });
            } else if (isolatedPortOrBody[1].contains("THIS_IS_BLACK")) {

                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        System.out.println("THIS SHOULD WORK ,.,., () ");
                        Player.MOVE_MESSAGE = isolatedPortOrBody[1].trim();
//                    WhitePlayer_1.setAllPiecesFromReceivedMessage();
                        MOVE_MESSAGE_FROM_RECEIVER_THREAD = isolatedPortOrBody[1].trim();
//                        Player.updateBoardAfterReceivedMessage();
//                        PlayerInitialiser.labelForWhoseMoveNow.setText("To Move: " + "WHITE");
                        Player.MOVE_MESSAGE = isolatedPortOrBody[1].trim();
                        Player.updateBoardAfterReceivedMessage();
                        Player.isBlackMove = false;
                        Player.isWhiteMove = true;
                        //UPDATED SATURDAY
                        //BlackPlayerInitialiser.receiveMove.fire();
                        PlayerInitialiser.labelForWhoseMoveNow.setText("To Move: " + ((Player.isWhiteMove) ? "WHITE" : "BLACK"));
//                      System.out.println("inside THIS_IS_BLACK clause ..... " + Player.MOVE_MESSAGE);
                    }
                });
            }
        }
        if (isolatedPortOrBody[1].contains("Khela Shesh")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    String fromClient = isolatedFrom[1].trim();
                    windowDisplayer.displayEndPopUp("Want To End The Game Please??", fromClient);
                }

            });
        } else if (isolatedPortOrBody[1].contains("AcceptedGameOver")) { //TODO

            System.out.println("><><><><><><><>< LINE 202 asdasdasdss <>< ><><.");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
//                    ClientGUI.display("TO DO LINE 206 in Receiver THread", "null");
                    boardRepresentation myCalculatingBoard = new boardRepresentation();
                    int sumWhite = myCalculatingBoard.sumOfWhitePiecesAfterGameOver();
                    int sumBlack = myCalculatingBoard.sumOfBlackPiecesAfterGameOver();

                    if ((sumWhite > sumBlack) && ClientGUI.PLAYER_COLOR.equals("WHITE")) {

                        ClientGUI.myWindowStage.setScene(new Scene(windowDisplayer.createFinalWinnerScreen()));
//                        stage.close();
                        System.out.println("lin 188 1st if .... inside ClientGUI");
                    } else if ((sumBlack > sumWhite) && (ClientGUI.PLAYER_COLOR.equals("BLACK"))) {
                        ClientGUI.myWindowStage.setScene(new Scene(windowDisplayer.createFinalWinnerScreen()));

//                        stage.close();
                        System.out.println("lin 193 2nd if .... inside ClientGUI");

                    } else if ((sumBlack < sumWhite) && (ClientGUI.PLAYER_COLOR.equals("BLACK"))) {
                        ClientGUI.myWindowStage.setScene(new Scene(windowDisplayer.createFinalLoserScreen()));
//                        stage.close();
                        System.out.println("lin 198 3rd if .... inside ClientGUI");

                    } else if ((sumBlack > sumWhite) && (ClientGUI.PLAYER_COLOR.equals("WHITE"))) {
                        ClientGUI.myWindowStage.setScene(new Scene(windowDisplayer.createFinalLoserScreen()));
//                        stage.close();
                        System.out.println("lin 203 3rd if .... inside ClientGUI");

                    } else if (sumBlack == sumWhite) { //TIE
                        ClientGUI.myWindowStage.setScene(new Scene(windowDisplayer.createFinalTIEScreen()));
//                        stage.close();
                        System.out.println("lin 208 4th if .... inside ClientGUI");

                    }
                }

            });
        } else if (isolatedPortOrBody[1].contains("GameOverRejected")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    new WindowScreens().display("Rejects End game request..", ClientGUI.senderClientName);
                }

            });
        } else if (isolatedPortOrBody[0].startsWith("Body") && (isolatedPortOrBody[1].startsWith(MessageCodes.acceptClient))) {
            System.out.println("ISOLATED PORT[1]   " + isolatedPortOrBody[1] + "  .....  " + " ..... " + MessageCodes.acceptClient);
            System.out.println(">><><><><><><>< INSIDE ACCPETT CLAUSE");
            String newMsg24 = isolatedPortOrBody[1];

            ClientGUI.choiceBox.getItems().addAll(ClientGUI.listOfNamesOfClients);
            String s = newMsg24.trim();
            System.out.println(">>>\n" + s);
            String fromClient = isolatedFrom[1].trim();
//            ClientGUI.displayPopUpWindow("TITILE", "DISPLA MSG");
//            ClientGUI.windowDisplayer.setScene();
//            ClientGUI.windowDisplayer.setScene(PopUp.displayPopUpWindow(informationOfClient, hereNewMsg));
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
//                    ClientGUI g = new ClientGUI();
//                    ClientGUI.displayPopUpWindow("INVITATION!", fromClient + " wants to play ..");
//                    ClientGUI.myWindowStage.setScene(new Scene(createWhiteStandardBoard()));

//                    ClientGUI.myWindowStage.setScene(new Scene(PlayerInitialiser.createWhiteStandardBoard()));
                    ClientGUI.myWindowStage.setScene(new Scene(PlayerInitialiser.createBlackStandardBoard()));

                    ClientGUI.PLAYER_COLOR = "BLACK";
                    //TO DO
//                    WhitePlayer_1.senderName = ClientGUI.senderClientName;
//                    WhitePlayer_1.blackPieceMover();
//                    WhitePlayer_1.updateAllPiecesAFTER_receivedMessage();
                    Player.senderName = ClientGUI.senderClientName;
                    Player.moveTimerInitialiser();
                    //UPDATED SATURDAY
                    //BlackPlayerInitialiser.receiveMove.fire();
//                    Player.whitePieceMover();

//                    ClientGUI.isWhiteMoveNow = true ;
//                    ClientGUI.isBlackMoveNow = false ;
                    Player.isWhiteMove = true;
                    Player.isBlackMove = false;
                    Player.blackPieceMover();
                    PlayerInitialiser.labelForWhoseMoveNow.setText("To Move: " + ((Player.isWhiteMove) ? "WHITE" : "BLACK"));
//                    Player.updateAllPiecesAFTER_receivedMessage();
//                    gui.WhitePlayer.senderName = ClientGUI.senderClientName;
////                    Player.blackPieceMover();
//                    System.out.println("Player.whitePieceMover() doesn't work ..... ");

                    ClientGUI.isPlayingCurrently = true;
//                    ServerGame.table2.re
//                    gui.WhitePlayer.blackPieceMover();
////                    Player.whitePieceMover();
////                    Player.isWhiteMove = true ;
//
//                    gui.WhitePlayer.updateAllPiecesAFTER_receivedMessage();
//                    WhitePlayer_1.SHOWRECEIVEDMESSAGEFROMRECEIVERTHREAD();
//                    gameStarted = true;
//                    ClientGUI.myWindowStage = WhitePlayer_1.windowDisplayer ;
                    ClientGUI.myWindowStage.setTitle("(BLACK) : === " + ClientGUI.myName + " VS " + fromClient + "  ===(WHITE)");
                    System.out.println("INSIDE RECEIVER THREAd Opponent has ACCEPTED");
//                    ClientGUI.windowDisplayer = PopUp.displayPopUpWindow("INVITATION FROM", isolatedFrom[1]);
//                ClientGUI.textFlow.getChildren().add(text);

                }

            });

        } else if (isolatedPortOrBody[0].startsWith("Body") && (isolatedPortOrBody[1].startsWith(MessageCodes.declineClient))) {
            System.out.println("ISOLATED PORT[1]   " + isolatedPortOrBody[1] + "  .....  " + " ..... " + MessageCodes.declineClient);
            System.out.println(">><><><><><><>< INSIDE DECLINE CLAUSE");
            String newMsg24 = isolatedPortOrBody[1];

//        ClientGUI.choiceBox.getItems().addAll(listClients);
            String s = newMsg24.trim();
            System.out.println(">>>\n" + s);
            String fromClient = isolatedFrom[1].trim();
//            ClientGUI.displayPopUpWindow("TITILE", "DISPLA MSG");
//            ClientGUI.windowDisplayer.setScene();
//            ClientGUI.windowDisplayer.setScene(PopUp.displayPopUpWindow(informationOfClient, hereNewMsg));
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
//                    ClientGUI g = new ClientGUI();
//                    ClientGUI.displayPopUpWindow("INVITATION!", fromClient + " wants to play ..");
                    String declineClientName = isolatedFrom[1].trim();
//ClientGUI.di      //TO DO FOR DECLINED
//                    ClientGUI.displayPopUpWindow("DECLINED", "Opponent has declined Sorry!", fromClient);
                    new WindowScreens().display("DECLINE MESSAGE", "Sorry .. " + declineClientName + "  has declined");
//                    ClientGUI.myWindowStage.setScene(new Scene(ClientGUI.createSecondScreen()));
//                    WhitePlayer_1.blackPieceMover();

                    System.out.println("INSIDE RUNNER OF RUNNER OF RUNNER of DECLINED");
//                    ClientGUI.windowDisplayer = PopUp.displayPopUpWindow("INVITATION FROM", isolatedFrom[1]);
//                ClientGUI.textFlow.getChildren().add(text);

                }

            });

        } else if (isolatedPortOrBody[0].startsWith("Body") && (!isolatedFrom[1].equals(isolatedTo[1])) && (!isolatedFrom[1].equals("Server")) && (isolatedPortOrBody[1].startsWith("CLIENT WH"))) {
            if (ClientGUI.isPlayingCurrently == false) { //already playing .. ?
                System.out.println("ISOLATED PORT[1]   " + isolatedPortOrBody[1] + "  .....  " + MessageCodes.requestClient);
                System.out.println(">><><><><><><>< INSIDE IF CLAUSE");
                String newMsg24 = isolatedPortOrBody[1];

//        ClientGUI.choiceBox.getItems().addAll(listClients);
                String s = newMsg24.trim();
                System.out.println(">>>\n" + s);
                String fromClient = isolatedFrom[1].trim();
//            ClientGUI.displayPopUpWindow("TITILE", "DISPLA MSG");
//            ClientGUI.windowDisplayer.setScene();
//            ClientGUI.windowDisplayer.setScene(PopUp.displayPopUpWindow(informationOfClient, hereNewMsg));
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
//                    ClientGUI g = new ClientGUI();
                        windowDisplayer.displayPopUpWindow("INVITATION of body of  " + ClientGUI.myName, fromClient + " wants to play ..", fromClient);
                        System.out.println("INSIDE RUNNER OF RecievrThraed OF ... wants to play clause");
//                    ClientGUI.windowDisplayer = PopUp.displayPopUpWindow("INVITATION FROM", isolatedFrom[1]);
//                ClientGUI.textFlow.getChildren().add(text);

                    }

                });
            } else if (ClientGUI.isPlayingCurrently == true) {
                String msgToBeSent = "Via: myChessGameServer" + "\nTo: " + isolatedFrom[1].trim() + "\nFrom: " + isolatedTo[1].trim() + "\nBody: " + "ALREADY PLAYING" + "\n";
                sender.sendAlreadyPlayingMessage(msgToBeSent);
//                    String toClient = isolatedFrom[1].trim();
//                    DatagramSocket senderSocket = new DatagramSocket();
//                    byte[] data = new byte[5000];
//                    data = msgToBeSent.getBytes();
//                    DatagramPacket senderPack = new DatagramPacket(data, data.length, InetAddress.getByName(serverIPAdress), serverPort);
//                    senderSocket.send(senderPack);
//                    System.out.println("lin 322 receiver Thread .... sent back request since already playing");
            }

        } else if (isolatedPortOrBody[0].startsWith("Body") && ((isolatedFrom[1].trim().equals(isolatedTo[1].trim())) == false) && (isolatedPortOrBody[1].trim().contains("ALREADY PLAYING") == true)) {

            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    new WindowScreens().display("ALREADY PLAYING", "Sorry Client is already playing .... Try some other Clients");
                    System.out.println(" ........... lin 332 Receiver Thread ....... already playing clause");

                }

            });

        } else if (isolatedPortOrBody[0].startsWith("Body") && isolatedFrom[1].equals("Server")) {
            System.out.println(">>>????>>>  INSIDE 2nd clause REceiverThread .. lin 331");
            String newMsg24 = isolatedPortOrBody[1];

            String newMsg = "\n";
            List<String> listClients = new ArrayList<>();
            if (listClients.size() != 0) {
                listClients.removeAll(listClients);
            }

//        ClientGUI.choiceBox.getItems().removeAll(listClients);
            for (int i = 4; i < messages.length; i++) {
//                    newMsg = newMsg.concat(newMsg);

                newMsg = newMsg.concat(messages[i]);
                newMsg = newMsg.concat("\n");

//            listOfParticipants.add(newMsg);
                if (newMsg != null) {
                    if (listClients.contains(messages[i])) {
                        continue;
                    }
                    listClients.add(messages[i]);
                }
//            System.out.println(">>>> " + newMsg);

            }
//        ClientGUI.choiceBox.getItems().addAll(listClients);
            String s = newMsg.trim();
            System.out.println(">>>\n" + s);
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    Text text;

                    text = new Text();
//                    text.setText('\n' + informationOfClient + '\n');
                    text.setFill(Color.CHOCOLATE);
                    text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 21));
//                    ClientGUI.textFlow.getChildren().addAll(text);
//                    ClientGUI.choiceBox.getItems().addAll(listClients);
//                    new WindowScreens().choiceBox.getItems().addAll(listClients);
                    ClientGUI.choiceBox.getItems().addAll(listClients);

//                ClientGUI.textFlow.getChildren().add(text);
                }

            });

        } else {
//            System.out.println("&*&*&*& ISNIDE ELSE CONDITION ())*((*&(**&");
//            System.out.println("ISOLATED PORT[1]   " + isolatedPortOrBody[1] + "  .....  " + requestClient);
//            System.out.println(">><><><><><><>< INSIDE IF CLAUSE");
            String newMsg24 = isolatedPortOrBody[1];

//        ClientGUI.choiceBox.getItems().addAll(listClients);
            String s = newMsg24.trim();
//            System.out.println(">>>\n" + informationOfClient);
            System.out.println("OFF! at ... " + s);
//            String fromClient = isolatedFrom[1].trim()
//            WhitePlayer_1.SHOWRECEIVEDMESSAGEFROMRECEIVERTHREAD();
//             ClientGUI.myWindowStage.setScene(new Scene(ClientGUI.createSecondScreen()));
        }
    }

    @Override
    public void run() {
        while (true) {
            recieve();
        }
    }

}
