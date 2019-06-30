package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.*;
import static network.ClientGUI.choiceBox;
import static network.ClientGUI.listOfNamesOfClients;
import static network.ClientGUI.requestServerButton;

public class WindowScreens implements MessageCodes {

//    public ChoiceBox<String> choiceBox;
    SenderNormal sender;
    boardRepresentation myCalculatingBoard;
//    public Button requestServerButton = new Button();

    public WindowScreens() {
        //NOTHING DONE HERE
        sender = new SenderNormal(ClientGUI.serverPortNo, ClientGUI.myServerIPAdress);
        myCalculatingBoard = new boardRepresentation();
    }

    public Parent createFinalWinnerScreen() {
        StackPane stackPane = new StackPane();
        Label label = new Label();
        String text = null;
        text = "YOU WON !!! \nCONGRATS!!";

        label.setTextFill(Color.CHOCOLATE);
        label.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 50));
//        text = String.valueOf(sum);
        label.setText(text);
//        Button button = new Button("Haa");
        stackPane.getChildren().addAll(label);//, button);
        return stackPane;
    }

    public Parent createFinalTIEScreen() {
        StackPane stackPane = new StackPane();
        Label label = new Label();
        String text = null;
        text = "TIE!! \nTake rest and \nPlay again Tomorrow!!";

        label.setTextFill(Color.BLUE);
        label.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 50));
//        text = String.valueOf(sum);
        label.setText(text);
//        Button button = new Button("Haa");
        stackPane.getChildren().addAll(label);//, button);
        return stackPane;
    }

    public Parent createFinalLoserScreen() {
        StackPane stackPane = new StackPane();
        Label label = new Label();
        String text = null;
        text = "OOOOPS YOU LOST... \nBetter Luck Next Time!";

        label.setTextFill(Color.RED);
        label.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 50));
//        text = String.valueOf(sum);
        label.setText(text);
//        Button button = new Button("Haa");
        stackPane.getChildren().addAll(label);//, button);
        return stackPane;
    }

    public Parent createSecondScreen() {
        Pane grid = new Pane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setMinSize(500, 500);
//        grid.setVgap(5);
//        grid.setHgap(5);

        grid.setStyle("-fx-background-color: #87CEFA;");

//        requestServerButton.setText("Sec Scren");
        requestServerButton.setText("REQUEST SERVER");
        requestServerButton.setScaleX(3);
        requestServerButton.setScaleY(3);
        requestServerButton.setAlignment(Pos.CENTER_RIGHT);
        requestServerButton.setLayoutX(150);
        requestServerButton.setLayoutY(150);

//        requestServerButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
////                displayPopUpWindow("TITLE", "MESSAGE");
//                System.out.println("SECOND IS CLIEKCKECKD ... sending reuquest..");
////                primaryStage.setScene(createDropDownList());
//
//                new SenderNormal(ClientGUI.portNo, ClientGUI.myServerIPAdress).sendRequestMessageToServer();
////                b3.setOnAction((e) -> {
//                ClientGUI.myWindowStage.setScene(new Scene(new WindowScreens().createDropDownList()));
////                });
//
//            }
//        });
        Label textLabel = new Label();
        textLabel.setLayoutX(150);
        textLabel.setLayoutY(300);
        textLabel.setFont((Font.font("Verdana", FontWeight.BOLD, 30)));
        textLabel.setTextFill(Color.CORAL);// TODO
        textLabel.setText("Click Above \nTo Request Server");
//        pane.setLeft(requestServerButton);
//        grid.setBottom(requestServerButton);
        Text text = new Text();
        text.setFill(javafx.scene.paint.Color.MAGENTA);
        text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 21));
//        text.setFont(Font.font("Verdana"));
//        text.setStyle();
        text.setText("REQUEST SERVER");
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().add(text);
//        grid.setCenter(text4);
        grid.getChildren().addAll(requestServerButton, textLabel);//, 5, 3);
//        b3 = new Button("After Sending request");
//        grid.add(b3, 0, 3);
//        grid.add(textFlow, 0, 2);
        grid.setStyle("-fx-background-color: DAE6F3;");
        return grid;
//        Scene scene = new Scene(pane, 300, 250);
    }

    public Parent createDropDownList() {
        Stage stage = new Stage();
        stage.setTitle("DROP DOWN");
        Button button = new Button("Invite !");
//        button.setFont();
        button.setTextFill(Color.CRIMSON);
        button.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 25));
        Label label = new Label();
        label.setText("Click To Get The List Of Clients");//e
        label.setTextFill(Color.DARKGREEN);
        label.setLayoutX(50);
        label.setLayoutY(10);
        label.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 25));
        ClientGUI.choiceBox.getItems().addAll(listOfNamesOfClients);
        Button prevButton = new Button("GO BACK");
        Button refreshButton = new Button("Refresh List");

        AnchorPane layout = new AnchorPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
//        layout.setSpacing(20);
        prevButton.setText("BACK");
        prevButton.setTextFill(Color.DARKBLUE);
        prevButton.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 30));

        button.setLayoutX(50);
        button.setLayoutY(400);
        prevButton.setLayoutX(50);
        prevButton.setLayoutY(475);
        refreshButton.setLayoutX(50);
        refreshButton.setLayoutY(550);
        refreshButton.setTextFill(Color.CRIMSON);
        refreshButton.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 30));

        refreshButton.setOnMouseClicked((event) -> {
            requestServerButton.fire();
        });

        choiceBox = new ChoiceBox(FXCollections.observableArrayList());
        choiceBox.setScaleX(2);
        choiceBox.setScaleY(2);
        choiceBox.setLayoutX(100);
        choiceBox.setLayoutY(100);
//        ClientGUI.choiceBox.setFocusTraversable;
        layout.getChildren().addAll(choiceBox, button, prevButton, label, refreshButton);
//        listOfNamesOfClients.add("CLICK LIST");
//        choiceBox.setValue("CLICK LIST");
        String accessibleText = choiceBox.getAccessibleText();

        prevButton.setOnAction((ActionEvent e) -> {
            System.out.println("GO BACK");
//            choiceBox.getItems().removeAll(listOfNamesOfClients);
            choiceBox.getItems().removeAll(ClientGUI.listOfNamesOfClients);
            for (String x : ClientGUI.listOfNamesOfClients) {
                System.out.println(">>>>> INSIDE BUTTON .....  " + x);
            }
//            listOfNamesOfClients.removeAll();
            ClientGUI.myWindowStage.setScene(new Scene(createSecondScreen()));

        });
        button.setOnAction((ActionEvent event) -> {
            System.out.println(choiceBox.getValue());
            Player.senderName = choiceBox.getValue();
            ClientGUI.senderClientName = choiceBox.getValue();
            sender.sendClientInvitation(ClientGUI.senderClientName);
        });
        return layout;

    }

    public void displayEndAcceptedGameOverAndTransferToFinalScreen() {
        Stage stage = new Stage();

        //Block events to other stages
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Game Over");
        stage.setMinWidth(250);
        stage.setHeight(300);
        Label label = new Label();
        String message = "Opponent also wants game over";
        label.setText(message);
        Button closeButton = new Button("Calculate Winner");
        int sumWhite = myCalculatingBoard.sumOfWhitePiecesAfterGameOver();
        int sumBlack = myCalculatingBoard.sumOfBlackPiecesAfterGameOver();

        if ((sumWhite > sumBlack) && ClientGUI.PLAYER_COLOR.equals("WHITE")) {
            closeButton.setOnAction(e -> {
                stage.close();
                ClientGUI.myWindowStage.setScene(new Scene(createFinalWinnerScreen()));
            });
        } else if ((sumBlack > sumWhite) && (ClientGUI.PLAYER_COLOR.equals("BLACK"))) {
            closeButton.setOnAction(e -> {
                stage.close();
                ClientGUI.myWindowStage.setScene(new Scene(createFinalWinnerScreen()));
            });
        } else if ((sumBlack < sumWhite) && (ClientGUI.PLAYER_COLOR.equals("BLACK"))) {
            closeButton.setOnAction(e -> {
                stage.close();
                ClientGUI.myWindowStage.setScene(new Scene(createFinalLoserScreen()));
            });
        } else if ((sumBlack > sumWhite) && (ClientGUI.PLAYER_COLOR.equals("WHITE"))) {
            closeButton.setOnAction(e -> {
                stage.close();
                ClientGUI.myWindowStage.setScene(new Scene(createFinalLoserScreen()));
            });
        } else { //TIE
            closeButton.setOnAction(e -> {
                stage.close();
                ClientGUI.myWindowStage.setScene(new Scene(createFinalTIEScreen()));
            });
        }

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display windowDisplayer and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        stage.setScene(scene);
//        return stage;
//        return scene ;
//        ClientGUI.windowDisplayer.setScene(scene);
        stage.showAndWait();
    }
    public Button popUpYes = new Button("ACCEPT Game Over");
    public Button popUpNo = new Button("Decline Game Over");

    public void displayEndPopUp(String message, String opponentName) {
        Stage stage = new Stage();
//        myWindowStage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Block Input events with other windows until this closes
        stage.setTitle("Window of " + ClientGUI.myName);

        stage.setMinWidth(350);
        stage.setHeight(350);

        Label label = new Label();
//        popUplabel = new Label();
        //FONTS AND COLORS
        label.setText(message);

//        popUpButtonYes = new Button("ACCTEPT");
        popUpYes.setOnAction((event) -> {
            System.out.println("ACCEPTED Game Over");
//            displayEndAcceptedGameOverAndTransferToFinalScreen();
//            sendAcceptedMessage();
            try {
                byte[] data = new byte[5000];
                DatagramPacket sendingPack = new DatagramPacket(data, data.length);
                DatagramSocket sendingSock = new DatagramSocket();
                String msg = "Via: " + "myChessGameServer" + "\nTo: " + opponentName + "\nFrom: " + ClientGUI.myName + "\nBody: " + "AcceptGameOver_AcceptedGameOver";
                System.out.println("------*******_-----------");

                System.out.println("<><><><>SENDING REQUEST lin 167 ClientGUI ........" + msg);
                System.out.println("------*******_-----------");
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
            } catch (IOException e) {
                System.out.println("INSIDE ACCEPTING MESSAGE ........ ");
                e.printStackTrace();
            }

            System.out.println("popUp Yes .............. lin180 .. ClientGUi");

//            ClientGUI.windowDisplayer.setScene(new Scene(createFinalScreen()));
//            int sumWhite = boardRepresentation.sumOfWhitePiecesAfterGameOver();
//            int sumBlack = boardRepresentation.sumOfBlackPiecesAfterGameOver();
            int sumWhite = myCalculatingBoard.sumOfWhitePiecesAfterGameOver();
            int sumBlack = myCalculatingBoard.sumOfBlackPiecesAfterGameOver();
            
            if ((sumWhite > sumBlack) && ClientGUI.PLAYER_COLOR.equals("WHITE")) {

                ClientGUI.myWindowStage.setScene(new Scene(createFinalWinnerScreen()));
                stage.close();
                System.out.println("lin 188 1st if .... inside ClientGUI");
            } else if ((sumBlack > sumWhite) && (ClientGUI.PLAYER_COLOR.equals("BLACK"))) {
                ClientGUI.myWindowStage.setScene(new Scene(createFinalWinnerScreen()));

                stage.close();
                System.out.println("lin 193 2nd if .... inside ClientGUI");

            } else if ((sumBlack < sumWhite) && (ClientGUI.PLAYER_COLOR.equals("BLACK"))) {
                ClientGUI.myWindowStage.setScene(new Scene(createFinalLoserScreen()));
                stage.close();
                System.out.println("lin 198 3rd if .... inside ClientGUI");

            } else if ((sumBlack > sumWhite) && (ClientGUI.PLAYER_COLOR.equals("WHITE"))) {
                ClientGUI.myWindowStage.setScene(new Scene(createFinalLoserScreen()));
                stage.close();
                System.out.println("lin 203 3rd if .... inside ClientGUI");

            } else if (sumBlack == sumWhite) { //TIE
                ClientGUI.myWindowStage.setScene(new Scene(createFinalTIEScreen()));
                stage.close();
                System.out.println("lin 208 4th if .... inside ClientGUI");

            }
            //// divert to another screen ..... winner or loser screen
//            stage.close();
        });
        popUpNo = new Button("DECLINE");
        popUpNo.setOnAction((e) -> {
            System.out.println("DECLIEND");
//            sender.sendDeclineMessage();
            try {
                byte[] data = new byte[5000];
                DatagramPacket sendingPack = new DatagramPacket(data, data.length);
                DatagramSocket sendingSock = new DatagramSocket();
                String msg = "Via: " + "myChessGameServer" + "\nTo: " + opponentName + "\nFrom: " + ClientGUI.myName + "\nBody: " + "GameOverRejectedRejectedRejected";
                System.out.println("<><><><>SENDING REQUEST......" + msg);
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
            } catch (IOException ex) {
                System.out.println("INSIDE DECLINING MESSAGE ........ ");
                ex.printStackTrace();
            }
            stage.close();

            ///DECLINING STUFF
        });

        VBox layout = new VBox();
        layout.setSpacing(40);
        layout.getChildren().addAll(label, popUpYes, popUpNo);
        layout.setAlignment(Pos.CENTER);
//        return  ;
        Scene scene = new Scene(layout);
        stage.setScene(scene);
//        return stage;
//        return scene ;
//        ClientGUI.windowDisplayer.setScene(scene);
        stage.showAndWait();
    }

    public void displayPopUpWindow(String title, String messageToDisplay, String opponentClient) {
        Stage stage = new Stage();
//        ClientGUI.myWindowStage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Block Input events with other windows until this closes
        stage.setTitle(title);

        stage.setMinWidth(350);
        stage.setHeight(350);

//        Label label = new Label();
        Label popUplabel;
        popUplabel = new Label();
        popUplabel.setText(messageToDisplay);

        Button popUpButtonYes;
        Button popUpButtonNo;

        popUpButtonYes = new Button("ACCTEPT");
        popUpButtonYes.setOnAction((event) -> {
            System.out.println("ACCEPTED PLAYING INVITATION");
            stage.close();
            String msg = "Via: " + "myChessGameServer" + "\nTo: " + opponentClient + "\nFrom: " + ClientGUI.myName + "\nBody: " + acceptClient;
            sender.sendMessageToServer(msg);
//            sender.sendAcceptedMessage();
            ClientGUI.myWindowStage.setTitle("(AS WHITE) " + ClientGUI.myName + " VSS  " + opponentClient + " (BLACK)");
            ClientGUI.senderClientName = opponentClient;
            Player.senderName = opponentClient;
            // ??

            ClientGUI.myWindowStage.setScene(new Scene(PlayerInitialiser.createWhiteStandardBoard()));
            ClientGUI.PLAYER_COLOR = "WHITE";
            ClientGUI.isPlayingCurrently = true;
//            ClientGUI.isWhiteMoveNow = true ;
//            ClientGUI.isBlackMoveNow = false;

            Player.isBlackMove = false;
            Player.isWhiteMove = true;
            Player.isInitialMoveOfTheGame = true; //?
            //UPDATED SATURDAY
            Player.moveTimerInitialiser(); //?
//            PlayerInitialiser.sendMove.fire();
            PlayerInitialiser.labelForWhoseMoveNow.setText("To Move: WHITE");
//            Player.blackPieceMover();
            Player.whitePieceMover();

            //TO DO 
            Player.updateAllPiecesAFTER_receivedMessage();

        });
        popUpButtonNo = new Button("DECLINE");
        popUpButtonNo.setOnAction((e) -> {
            System.out.println("DECLIEND");
//            sender.sendDeclineMessage();
            String msg = "Via: " + "myChessGameServer" + "\nTo: " + opponentClient + "\nFrom: " + ClientGUI.myName + "\nBody: " + declineClient;
            sender.sendMessageToServer(msg);
            stage.close();
            ClientGUI.myWindowStage.setScene(new Scene(createSecondScreen()));
            ///DECLINING STUFF
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(popUplabel, popUpButtonYes, popUpButtonNo);
        layout.setAlignment(Pos.CENTER);
//        return  ;
        Scene scene = new Scene(layout);
        stage.setScene(scene);

        stage.showAndWait();    //DISPLAY ALERT BOX and before returning it should be cloes
    }

    public void display(String title, String message) {
        Stage stage = new Stage();

        //Block events to other stages
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        stage.setHeight(300);
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display windowDisplayer and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        stage.setScene(scene);
//        return stage;
//        return scene ;
//        ClientGUI.windowDisplayer.setScene(scene);
        stage.showAndWait();
    }
}
