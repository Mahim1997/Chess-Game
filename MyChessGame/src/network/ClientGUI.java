package network;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClientGUI extends Application implements Runnable, MessageCodes {

    WindowScreens windowDisplayer = new WindowScreens();
    public static Button requestServerButton = new Button();

    public static List<String> listOfNamesOfClients = new ArrayList<>();
    public static String senderClientName;
    public static Stage myWindowStage = new Stage();

    public static boolean isPlayingCurrently = false;
    public static String PLAYER_COLOR;
    public static boolean isWhiteMoveNow;
    public static boolean isBlackMoveNow;
    Thread tr;

    public static int serverPortNo = 5050;
    public static String myName;
    public static String myServerIPAdress;
    public static String[] informationOfClient;
    public static int portNo;

    public static ChoiceBox<String> choiceBox = new ChoiceBox<>();
    static Button clickToSendRequestToServer = new Button();

    SenderThread sender;
    ReceiverThread receiver;

    private final String serverName = "myChessGameServer";

    private final TextField text1 = new TextField();
    private final TextField text2 = new TextField();
    private final TextField text3 = new TextField();

    private final int windowHeight = 750;
    private final int windowWidth = 800;
    private boolean value;

    public static void main(String[] args) {
        launch(args);
    }

    public ClientGUI() {
        this.tr = new Thread(this);//
        tr.start();
    }

    public Parent createInitialScreen() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setMinSize(500, 500);
        grid.setVgap(5);
        grid.setHgap(5);

        Text name = new Text("Enter name:");
        name.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 25));

        grid.add(name, 0, 0);
//        TextField text1 = new TextField();
//        TextField text2 = new TextField();
//        TextField text3 = new TextField();

        text1.setPromptText("<One word>");
        text1.setPrefColumnCount(15);
        grid.add(text1, 1, 0);
        text1.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 25));

        Text password = new Text("Enter Port no:");
        password.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 25));
        grid.add(password, 0, 1);

//        TextField text2 = new TextField();
        text2.setPrefColumnCount(15);
        text2.setPromptText("<Enter your portNo>");

        grid.add(text2, 1, 1);

//        label.setText("Click To Get The List Of Clients");//e
//        (Color.DARKGREEN);
//        label.setLayoutX(240);
//        label.setLayoutY(100);
        text2.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 25));
        Text serverName = new Text("Enter Server IP:");
        serverName.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 25));

        grid.add(serverName, 0, 2);
        text3.setPrefColumnCount(15);
        text3.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 25));
        text3.setPromptText("<Enter serverIP>");
        grid.add(text3, 1, 2);
        clickToSendRequestToServer.setText("Click here");
        clickToSendRequestToServer.setTextFill(Color.DARKRED);
        clickToSendRequestToServer.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 25));

//        clickToSendRequestToServer = new Button("Click her");
        grid.add(clickToSendRequestToServer, 0, 6);

        grid.setStyle("-fx-background-color: #A9A9A9;");

        return grid;

    }

    @Override
//    @SuppressWarnings("empty-statement")
    public void start(Stage primaryStage) throws Exception {

        myWindowStage = primaryStage;
        myWindowStage.setScene(new Scene(createInitialScreen()));
        myWindowStage.setHeight(windowHeight);
        myWindowStage.setWidth(windowWidth);
        myWindowStage.show();

        clickToSendRequestToServer.setOnAction(new EventHandler<ActionEvent>() {
            private boolean flag;

            @Override

            public void handle(ActionEvent event) {
                try {
                    myName = text1.getText();

                    portNo = Integer.parseInt(text2.getText());

                    myServerIPAdress = text3.getText();
//                    myServerIPAdress = "127.0.0.1";
                    System.out.println("Name : " + myName + "\nPort: " + portNo + "\nServer: " + myServerIPAdress);
                    String[] s = {myName, String.valueOf(portNo), myServerIPAdress};
//                    receiver = new ReceiverThread(informationOfClient);

                    informationOfClient = s;
                    primaryStage.setTitle(myName);// + myName);
                    sender = new SenderThread(s);
                    System.out.println(">> inasalising sender");
//                    sender.sendToServer();///INITIALISE THE SENDER
                    System.out.println("..line 414 changing value");
                    value = true;
                    if (value == true && informationOfClient != null && myName != null && myServerIPAdress != null) {
                        receiver = new ReceiverThread(informationOfClient);
                        insideRunValue = true;
                        value = false;
                    }
                    myWindowStage.setScene(new Scene(windowDisplayer.createSecondScreen()));

                } catch (NumberFormatException ex) {
                    windowDisplayer.display("Format Error", " Port number not Integer");

                } catch (SocketException ex) {
                    System.out.println("+++++ INSIDE HANDLE....");
                    ex.printStackTrace();
                } catch (UnknownHostException ex) {
                    System.out.println("+++++ INSIDE HANDLe....");
                    ex.printStackTrace();
                } catch (IOException ex) {
                    System.out.println("+++++ INSIDE HANDLe....");
                    ex.printStackTrace();
                } catch (Exception ex) {
                    System.out.println("INSIDE SEND INITIAL REQUEST");
                    ex.printStackTrace();
                }

            }
        });

        requestServerButton.setOnAction((ActionEvent event) -> {
            //                displayPopUpWindow("TITLE", "MESSAGE");
            System.out.println("SECOND IS CLIEKCKECKD ... sending reuquest..");
//                primaryStage.setScene(createDropDownList());
            sender.sendRequest();
//                new SenderNormal(ClientGUI.portNo, ClientGUI.myServerIPAdress).sendRequestMessageToServer();
//                b3.setOnAction((e) -> {
            ClientGUI.myWindowStage.setScene(new Scene(new WindowScreens().createDropDownList()));
//                });
        });

        myWindowStage.setOnCloseRequest((event) -> {
            String msg = "Via: " + serverName + "\nTo: " + serverName + "\nFrom: " + myName + "\nDeport: " + "Remove me ... I am " + myName;
            try {
                byte[] data = new byte[5000];
                DatagramPacket sendingPack = new DatagramPacket(data, data.length);
                DatagramSocket sendingSock = new DatagramSocket();
//            String msg = "Via: " + "myChessGameServer" + "\nTo: " + opponentClient + "\nFrom: " + ClientGUI.myName + "\nBody: " + acceptClient;
//                String msg = fullMessage;
                System.out.println("<><><><>SENDING REMOVAL......" + msg);
//                byte data[];
                InetAddress add = InetAddress.getByName(ClientGUI.myServerIPAdress); ///the 3rd argument is the server IP Address
                data = msg.getBytes();
//                sendingPack = new DatagramPacket(data, data.length);
                sendingPack.setAddress(add);
                sendingPack.setPort(ClientGUI.serverPortNo);
                sendingPack.setData(data);
                sendingSock = new DatagramSocket();
                sendingSock.send(sendingPack);
                sendingSock.close();
//            stage.close();
            } catch (IOException e) {
                System.out.println("INSIDE ACCEPTING MESSAGE ........ ");
                e.printStackTrace();
            }
        });
    }
//    public static String array[] = {"c", "12346", "s"};
    boolean insideRunValue = false;

    @Override
    public void run() {
        System.out.println("Inside run");
//        receiver = new ReceiverThread(array);
        while (true) {
            System.out.println("INSIDE RUN.... value is  " + value);
            if (value == true) {
//                receiver = new ReceiverThread(arr);
                System.out.println("...INISDE RUN SERVER IS INIITAIETED");
                value = false;
//                insideRunValue = true;
            }
            if (insideRunValue == true) {
                System.out.println("+:+_=+ INSIDE RUN .receiev() about to be calleldl ");
                receiver.recieve();
//                System.out.println("<><><><><><>><>...... + " + ReceiverThread.receivedMessage.trim() );
//                newText = new Text(ReceiverThread.receivedMessage.trim());
//                text4.getChildren().add(newText);

            }
        }
    }
}
