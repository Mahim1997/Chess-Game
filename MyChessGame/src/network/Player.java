package network;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.shape.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import static network.PlayerInitialiser.*;
import static network.NormalFunctions.*;

public class Player extends Application implements PieceCodeValues, PieceImageIndex, PieceImagePosition {

    WindowScreens windows = new WindowScreens();
    public static long delChange = 2 * 60 * 1000;//= 15000;    //15s

    public static double initialTimer;
    public static double finalTimer;

    public static boolean isInitialMoveOfTheGame;
    public static AnchorPane pane = new AnchorPane();
    public static ImageView boardImage;
    public static int height = 500, width = 500, initX = 20, initY = -120, del = 33, delXOneUnit = 55, delYOneUnit = 55;
    public static ImageView[] whitePawn = new ImageView[8];
    public static ImageView[] blackPawn = new ImageView[8];
    public static Hashtable<ImageView, Integer> table = new Hashtable<>();
    public static Hashtable<Integer, ImageView> pieceCodetoImageTable = new Hashtable<>();
//    static boolean wPMove = true;

    public static boardRepresentation myBoard = new boardRepresentation();

    public static ImageView[] whitePieces = new ImageView[8];//{wR, wN, wB, wK, wQ, wR2, wN2, wB2};
    public static ImageView[] blackPieces = new ImageView[8];//{bR, bN, bB, bK, bQ, bR2, bN2, bB2};

    public static ImageView[] allPiecesImages = new ImageView[16];
    static boolean isWhiteMove = true;
    static boolean isBlackMove = false;
    public static String[] infoOfPeople = new String[4];
    public static boolean whiteMoveNow;
    public static boolean blackMoveNow;
    public static String senderName;

    public static Rectangle checkerSquares[][] = new Rectangle[64][2]; // 0th is normal , 1st is transparent

    public static String MOVE_MESSAGE;
    private static int moveCounter = 0;

    public static void placeTheCheckerSquaresBack() {
        for (int i = 0; i < 64; i++) {
            setSquareRelativeToBoard(checkerSquares[i][0], 11, 2);
            setSquareRelativeToBoard(checkerSquares[i][1], 11, 3);
        }
    }
    SenderThread sender1;//
    ReceiverThread receiver;

    public Player() {
//        sender1 = new SenderThread(s);
//        receiver = new ReceiverThread(s);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setImage(ImageView img, int posX, int posY) {
        if (img == null) {
            System.out.println("NULL IMAGE...");
            return;
        } else {
            img.setX(arrayCheckerX[posX][0] + 7);
            img.setY(arrayCheckerY[posY][1] - 42);
        }
    }

    public static void moveTimerInitialiser() {
        sendMove.setOnAction((event) -> {
//            delChange = 5000;
            clickedTime = System.currentTimeMillis();
            waitingForReceiverMoveStopWatch.cancel();
            waitingForMyMoveStopWatch = new Timer();
            waitingForMyMoveStopWatch.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        public void run() {
//                        timer2.cancel();
//                            label2.setText("(Do After Send MyMove) (waiting for opponentMove) ..." + String.valueOf(Math.ceil((System.currentTimeMillis() - clickedTime))));//+ (System.currentTimeMillis() - initialTimer));
                            label2.setText("                              Waiting for Opponent Move) .." + String.valueOf(Math.ceil((System.currentTimeMillis() - clickedTime)) / 1000) + " s");//+ (System.currentTimeMillis() - initialTimer));

                        }
                    });
                }
            }, 0, 1000);
//            System.out.println("Button2 .. delTimer .. " + delTimer + "  .. delChange  .. " + delChange);
            toDoTaskTimer.cancel();
            toDoTaskTimer = new Timer();
            toDoTaskTimer.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            waitingForMyMoveStopWatch.cancel();
//                            backgroundStopWatch.cancel();
//                            label3.setText("I didn't make move ... YOU LOSE AFTER (time) (to do)10s ");//+ (System.currentTimeMillis() - initialTimer));
                            label2.setText("(Move not received in due time) (time) (to do)10s ");//+ (System.currentTimeMillis() - initialTimer));
                            ClientGUI.myWindowStage.setScene(new Scene(new WindowScreens().createFinalWinnerScreen()));
                        }
                    });
                }
            }, delChange);
        });

        receiveMove.setOnAction((event) -> {
//            delChange += delTimer;
//            delChange = 5000;
            clickedTime = System.currentTimeMillis();

            waitingForMyMoveStopWatch.cancel();
            waitingForReceiverMoveStopWatch = new Timer();
//            clickedTime = System.currentTimeMillis();

            waitingForReceiverMoveStopWatch.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        public void run() {
//                        timer2.cancel();
//                            label3.setText("(Do after receiving Move) My Move timer ... (my Move) ..." + String.valueOf(Math.ceil((System.currentTimeMillis() - clickedTime))));//+ (System.currentTimeMillis() - initialTimer));
                            label3.setText("                                                   My Move in:  ..." + String.valueOf(Math.ceil((System.currentTimeMillis() - clickedTime)) / 1000) + " s");//+ (System.currentTimeMillis() - initialTimer));

                        }
                    });
                }
            }, 0, 1000);
//            clickedTime = System.currentTimeMillis();
//            System.out.println("Button1 .. delTimer .. " + delTimer + "  .. delChange  .. " + delChange);
            toDoTaskTimer.cancel();
            toDoTaskTimer = new Timer();
            toDoTaskTimer.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            waitingForReceiverMoveStopWatch.cancel();
//                            backgroundStopWatch.cancel();
//                            label3.setText("I didn't make move ... YOU LOSE AFTER (time) (to do)10s ");//+ (System.currentTimeMillis() - initialTimer));
                            label3.setText("                       ..  YOU LOSE AFTER (time) (to do)10s ");//+ (System.currentTimeMillis() - initialTimer));
                            ClientGUI.myWindowStage.setScene(new Scene(new WindowScreens().createFinalLoserScreen()));

//                            label3.setText("(Move not received in due time) (time) (to do)10s ");//+ (System.currentTimeMillis() - initialTimer));
                        }
                    });
                }
            }, delChange);
        });

    }

    public static void updateBoardAfterReceivedMessage() {
//        isBlackMove = !isWhiteMove;
//            delChange = 5000;
        receiveMove.fire(); // ??
        int[][] a = boardRepresentation.board;
        System.out.println("<><> INSIDE UPDATE BOARD FROM ARRAY");
        updateAllPiecesAFTER_receivedMessage();
        printArray(a);
    }

    public static void updateAllPiecesAFTER_receivedMessage() {

        removeAllPiecesFromIntgerArray();
        setAllPiecesFromReceivedMessage();
    }

    public static void updateBoardImageAfterMoveIsDone() {
        removeAllPiecesFromIntgerArray();
        setImagesFromIntegerArray();
    }

    public static void setImageRelativeToBoard(ImageView img, int coordY, int coordX) {
        if (img == null) {
            System.out.println("NULL IMAGE");
            return;
        } else {
            img.setX(boardImage.getX() + del + coordX * delXOneUnit);
            img.setY(boardImage.getY() + del + coordY * delYOneUnit);
        }
    }
//THIS WORKS

    public static void setSquareRelativeToBoard(Rectangle img, int coordX, int coordY) {
        if (img == null) {
            System.out.println("NULL IMAGE");
            return;
        } else {
            img.setX(boardImage.getX() + (coordX) * delXOneUnit + del);
            img.setY(boardImage.getY() + (coordY) * delYOneUnit + del);
        }
//        } else {
//            img.setX(boardImage.getX() + del + coordX * delXOneUnit + 20);  //THIS THIS THIS WORKS
//            img.setY(boardImage.getY() - del + (coordY - 1) * delYOneUnit);
//        }
    }

    static int capturedPieceCounter = 0;
    static int zeroCounterInitial = 32;
//    private static final int sum ;
    static Timer waitingForReceiverMoveStopWatch = new Timer();
    static Timer waitingForMyMoveStopWatch = new Timer();
    static Timer toDoTaskTimer = new Timer();
    static Timer backgroundStopWatch = new Timer();
    static long maxTimeForOneMove = 10000; // 10 s .. 1min
    public static Label label1 = new Label();
    public static Label label2 = new Label();
    public static Label label3 = new Label();

    static long delTime = maxTimeForOneMove;
    static long clickedTime;

    public static void setImagesFromIntegerArray() {
        int[][] a = boardRepresentation.board;
        int zeroCounter = 0;
        System.out.println(">>>INSIDE FUNCTION .... printing whiteBoard");
        printArray(boardRepresentation.board);
        System.out.println("**___**");
        int pieceFound;
        int foundPieceCode;
//        int maxNoOfMoves = 1;
        String s1 = "Moves NO: " + String.valueOf(moveCounter) + " (whiteSum) " + String.valueOf(boardRepresentation.sumOfWhitePiecesAfterGameOver()) + "  (blackSum) ";
        s1 = s1 + String.valueOf(boardRepresentation.sumOfBlackPiecesAfterGameOver());
        labelForMovesCounter.setText(s1);

        if (moveCounter >= maxNoOfMoves) {
//            hBox.getChildren().addAll(endButton);
            endButton.setOnAction((event) -> {
//                int sumWhite = boardRepresentation.sumOfWhitePiecesAfterGameOver();
//                System.out.println("====> Sum of pieces is " + sumWhite);
                try {
                    DatagramSocket sock = new DatagramSocket();
                    byte[] data = new byte[5000];
                    String finalMsg = "Via: " + "myChessGameServer" + "\nTo: " + senderName + "\nFrom: " + ClientGUI.myName + "\nBody: " + "Khela Shesh";
                    data = finalMsg.getBytes();
                    DatagramPacket pack = new DatagramPacket(data, data.length, InetAddress.getByName(ClientGUI.myServerIPAdress), 5050);
                    sock.send(pack);
                    System.out.println("---------------------************_-------------------");
                    System.out.println("in lin 169 of black player Sendiung msg is ...... ");
                    System.out.println(finalMsg);
                    System.out.println("---------------------************_-------------------");

                } catch (Exception e) {
                    System.out.println(">>>> ERROR SENDING FINAL MSG .... lin162");
                }

                //ADD TO LABEL
                System.out.println("END BUTTON !!");
            });
        }
//        labelForWhoseMoveNow.setText("To Move: " + ((isWhiteMove==true)?" WHITE" : " BLACK"));
        if (ClientGUI.PLAYER_COLOR.equals("WHITE")) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boardRepresentation.board[i][j] != 0) {
                        pieceFound = boardRepresentation.board[i][j];
                        ImageView getImage = Player.pieceCodetoImageTable.get(pieceFound);
//                    System.out.println("<> +++_+_+ FOUND PIECE .... code is " + pieceFound + " .... at position (transpose) ... " + j + " , " + i);
                        if (getImage != null) {
                            setImageRelativeToBoard(getImage, i, j);
                        } else {
                            System.out.println("NULL IMAGE at .. " + i + " , " + j);
                        }
//                    table.
//                    foundPieceCode = table.get(pieceFound);
                    } else {
                        zeroCounter++;
//                    System.out.println("NOW ZERO COUNTER IS  " + zeroCounter + "  and initial was .. " + zeroCounterInitial);
                    }
                }
            }
        } else if (ClientGUI.PLAYER_COLOR.equals("BLACK")) {
            //INDICATING BLACK BOARD
            int[][] b = boardRepresentation.rotateFullArray(boardRepresentation.board);
//            b = boardRepresentation.rotateFullArray(b);
            System.out.println("line 310 ------******_--------");
            printArray(b);
//            setImageRelativeToBoard(images[18], 10, 10);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (b[i][j] != 0) {
                        pieceFound = b[i][j];
                        ImageView getImage = Player.pieceCodetoImageTable.get(pieceFound);
//                        System.out.println("<> +++_+_+ FOUND PIECE .... code is " + pieceFound + " .... at position (transpose) ... " + j + " , " + i);
                        if (getImage != null) {
                            setImageRelativeToBoard(getImage, i, j);
                        } else {
                            System.out.println("NULL IMAGE at .. " + i + " , " + j);
                        }
//                    table.
//                    foundPieceCode = table.get(pieceFound);
                    } else {
                        zeroCounter++;
//                    System.out.println("NOW ZERO COUNTER IS  " + zeroCounter + "  and initial was .. " + zeroCounterInitial);
                    }
                }
            }
            System.out.println("line 310 ------******_--------");
        }

    }

    public static void setAllPiecesFromReceivedMessage() {
        int[][] a = boardRepresentation.board;
        System.out.println("--------------*****{}{}{}{}{}*********------------");
        System.out.println("RECEIVING BOARD ARRAY ..... \n" + ReceiverThread.receivedMessage.trim());
        String messagesSplitter[] = ReceiverThread.receivedMessage.split("\n");
//        System.out.println("<><><><><><> isolatedMessagesSplitter[3] is ... \n");
        String isolatedSplitBody[] = messagesSplitter[3].split(":"); //AFTER Body: .....
//        System.out.println("RECEIVED MESSAGE IS ....\n");
//        System.out.println(isolatedSplitBody);

        System.out.println("WHITEPLAYER_2 msg is .... ");
        System.out.println(ReceiverThread.MOVE_MESSAGE_FROM_RECEIVER_THREAD);
        String s = ReceiverThread.MOVE_MESSAGE_FROM_RECEIVER_THREAD;
        if (s != null) {
            int idx1 = s.indexOf("e") + 1;
            String sub1 = s.substring(idx1, idx1 + 4);
            int movingPieceCode = Integer.parseInt(sub1.trim());

            int initialPosX, initialPosY, finalPosX, finalPosY;
            int idx2 = s.indexOf("(");
            String sub2 = s.substring(idx2 + 1, idx2 + 3);
            initialPosX = Integer.parseInt(sub2.trim());

            int idx3 = s.indexOf(")");
            String sub3 = s.substring(idx3 - 2, idx3);
            initialPosY = Integer.parseInt(sub3.trim());

            int idx4 = s.indexOf("<");
            String sub4 = s.substring(idx4 + 1, idx4 + 3);
            finalPosX = Integer.parseInt(sub4.trim());

            int idx5 = s.indexOf(">");
            String sub5 = s.substring(idx5 - 2, idx5);
            finalPosY = Integer.parseInt(sub5.trim());

            if ((s.contains("THIS_IS_WHITE"))) {
                boardRepresentation.updateWhiteBoard(movingPieceCode, initialPosX, initialPosY, finalPosX, finalPosY);
            } else {
                boardRepresentation.updateWhiteBoard(movingPieceCode, initialPosX, initialPosY, finalPosX, finalPosY);
            }
//                boardRepresentation.updateWhiteBoard(movingPieceCode, 7-initialPosX, 7-initialPosY, 7-finalPosX, 7-finalPosY);

            System.out.println("<> SHOULD WORK <>");
            System.out.println(" ***** ++++++ ***** ");
            System.out.println("Piece code is : " + movingPieceCode);
            System.out.println("Peice positions are .. " + initialPosX + " , " + initialPosY + "  &&& " + finalPosX + " , " + finalPosY);
            System.out.println(" ***** ++++++ ***** ");

        }

        //TODO ?????
        System.out.println(" ____ ****** ______ ***** ______ ***** ______");

        int zeroCounter = 0;
        System.out.println(">>>INSIDE  (line 303).... printing whiteBoard  .. in setAllPiecesFromReceivedMessage()");
        printArray(a);
        System.out.println(" ____ ****** ______ ***** ______ ***** ______");
        int pieceFound;
        int foundPieceCode;
        int boardNow[][] = boardRepresentation.board;
        updateBoardImageAfterMoveIsDone();

    }

    public static void removeAllPiecesFromIntgerArray() {
        int[][] a = boardRepresentation.board;
        int capturedPieceRowX = 0;
        int capturedPieceColY = 9;
        for (int i = 0; i < 8; i++) {
            setImageRelativeToBoard(whitePawn[i], capturedPieceRowX + i, capturedPieceColY);

        }
//        capturedPieceRowX++;
        capturedPieceColY++;
        for (int i = 0; i < 8; i++) {
            setImageRelativeToBoard(blackPawn[i], i, capturedPieceColY);

        }
        capturedPieceColY++;

        for (int i = 0; i < 8; i++) {
            setImageRelativeToBoard(allPiecesImages[i], i, capturedPieceColY);

        }
        capturedPieceColY++;
//
        for (int i = 8; i < 16; i++) {
            setImageRelativeToBoard(allPiecesImages[i], i - 8, capturedPieceColY);

        }

    }
    static int maxNoOfMoves = 1;

    public static void whitePieceMover() { //FOR WHITE
        for (int i = 8; i < 16; i++) {  //WHITE PIECES
            ImageView x = allPiecesImages[i];
            x.setOnMouseClicked((e1) -> {
//            boolean myflag = true;

                System.out.println("CLICEKD ON WHITE IMAGE !!");
                if ((ClientGUI.PLAYER_COLOR.equals("WHITE") == false) || (isWhiteMove == false) || (isBlackMove == true)) {
                    System.out.println("lin 429 ... TODO NOT YOUR MOVE IN GUI .. THIS IS WHITE's MOVE");
                    e1.consume();
                    return; //?
                }
                int xCorrespondingCode = table.get(x);
//                isWhiteMove = false ;                int xCorrespondingCode = table.get(x);
                int initialPositionImageX = positionX(e1);
                int initialPositionImageY = positionY(e1);

                System.out.println("Only valid for White (Major piece touched)");
                addImagePositionsForValidMoves(xCorrespondingCode, initialPositionImageX, initialPositionImageY);

                for (ImageView z : allPiecesImages) {
                    if (z.equals(x) == false) {
                        z.setOnMouseClicked((event) -> {
                            whitePieceMover();
                        });
                    }
                }
                for (ImageView a : whitePawn) {
                    a.setOnMouseClicked((event) -> {
                        whitePieceMover();
                    });
                }
                for (ImageView a : blackPawn) {
                    a.setOnMouseClicked((event) -> {
                        whitePieceMover();
                    });
                }
                x.setOnMouseClicked((event) -> {
                    placeTheCheckerSquaresBack();
                    whitePieceMover();
                });

//                for (ImageView y : images) {
                for (int counterChecker = 0; counterChecker < 64; counterChecker++) {
                    Rectangle y = checkerSquares[counterChecker][1];
                    y.setOnMouseClicked((event1) -> {
                        if (e1.getSource().equals(x)) {
//                Object x = event.getSource();
                            if ((isWhiteMove == true) && (ClientGUI.PLAYER_COLOR.equals("WHITE") == true)) {
                                System.out.println("After move..... ");
//                            System.out.println("<><>Before move pos.... " + x.getX() + " , " + x.getY() + " ...... " + (x.getX() - boardImage.getX() + del) / delXOneUnit + " , " + (x.getY() - 300) / delYOneUnit);
//                            System.out.println("<><> Mouse pos is ..... " + event1.getX() + " , " + event1.getY() + "   .....  " + (event1.getX() - del) / delXOneUnit + " , " + (event1.getY() - 300) / delYOneUnit);
                                e1.consume();
                                event1.consume();
////                    falg = false;
                                //CHANGING NOW SATURDAY
                                //sendMove.fire();
                                isWhiteMove = false;
                                isBlackMove = true;
                                changeBoard(x, event1);

                            }
                        }

                    });
                }
            });
        }
//        ImageView x = whitePawn[0];
        for (ImageView x : whitePawn) {
            x.setOnMouseClicked((e1) -> {
//            boolean myflag = true;
                System.out.println("WHITE PAWN CLICED");
                if ((ClientGUI.PLAYER_COLOR.equals("WHITE") == false) || (isWhiteMove == false) || (isBlackMove == true)) {
                    System.out.println("lin 496 ... TODO NOT YOUR MOVE IN GUI .. THIS IS WHITE's MOVE");
                    e1.consume();
                    return; //?
                }
                int xCorrespondingCode = table.get(x);

                int initialPositionImageX = positionX(e1);
                int initialPositionImageY = positionY(e1);

                addImagePositionsForValidMoves(xCorrespondingCode, initialPositionImageX, initialPositionImageY);
                System.out.println("Onli valid for white");
                for (ImageView z : allPiecesImages) {
                    if (z.equals(x) == false) {
                        z.setOnMouseClicked((event) -> {
                            whitePieceMover();
                        });
                    }
                }
                for (ImageView a : whitePawn) {
                    a.setOnMouseClicked((event) -> {
                        whitePieceMover();
                    });
                }
                for (ImageView a : blackPawn) {
                    a.setOnMouseClicked((event) -> {
                        whitePieceMover();
                    });
                }

                x.setOnMouseClicked((event) -> {
//                    event.consume();
                    placeTheCheckerSquaresBack();
                    whitePieceMover();
                });
//                for (ImageView y : images) {
                for (int counterChecker = 0; counterChecker < 64; counterChecker++) {
                    Rectangle y = checkerSquares[counterChecker][1];
                    y.setOnMouseClicked((event1) -> {
                        if (e1.getSource().equals(x)) {
//                Object x = event.getSource();
                            if ((isWhiteMove == true) && (ClientGUI.PLAYER_COLOR.equals("WHITE") == true)) {
//                            System.out.println("After move..... ");
//                            System.out.println("<><>Before move pos.... " + x.getX() + " , " + x.getY() + " ...... " + (x.getX() - boardImage.getX() + del) / delXOneUnit + " , " + (x.getY() - 300) / delYOneUnit);
//                            System.out.println("<><> Mouse pos is ..... " + event1.getX() + " , " + event1.getY() + "   .....  " + (event1.getX() - del) / delXOneUnit + " , " + (event1.getY() - 300) / delYOneUnit);
                                e1.consume();
                                event1.consume();
//                    falg = false;
                                //CHANGING NOW SATURDAY
                                //sendMove.fire();

                                isBlackMove = true;
                                isWhiteMove = false;
                                changeBoard(x, event1);

//                    isMyMove = false;
                            }
                        }

                    });
                }
            });
        }
        pane.setOnMouseClicked((event) -> {
            System.out.println("YOU ARE NOT BLACK ... CANT DO THIS");
            event.consume();
            return;
        });
        placeTheCheckerSquaresBack();
    }

    public static void blackPieceMover() {    //CHANGE IMPORT
        boolean flag = true;
        for (int i = 0; i < 8; i++) {   //BLACK MOve
            ImageView x = allPiecesImages[i];
            x.setOnMouseClicked((e1) -> {
                if ((ClientGUI.PLAYER_COLOR.equals("BLACK") == false) || (isWhiteMove == true) || (isBlackMove == false)) {     //ONLY BLACK PLAYER CAN MAKE THIS MOVE ..... blackPieceMover()
                    System.out.println("lin 311 ... TODO NOT YOUR MOVE IN GUI  ... THIS IS BLACK's MOVE");

                    e1.consume();
                    return; //?
                }
//            boolean myflag = true;
                System.out.println("CLICEKD ON BLACK IMAGE !!");
//                thisImageMove = true;
                int nowPieceCode = table.get(x);
//                isWhiteMove = false ;
                int xCorrespondingCode = table.get(x);
                int initialPositionImageX = positionX(e1);
                int initialPositionImageY = positionY(e1);
                addImagePositionsForValidMoves(xCorrespondingCode, initialPositionImageX, initialPositionImageY);
                for (ImageView z : allPiecesImages) {
                    if (z.equals(x) == false) {
                        z.setOnMouseClicked((e2) -> {
                            blackPieceMover();
                        });
                    }
                }
                for (ImageView a : whitePawn) {
                    a.setOnMouseClicked((e3) -> {
                        blackPieceMover();
                    });
                }
                for (ImageView a : blackPawn) {
                    a.setOnMouseClicked((e4) -> {
                        blackPieceMover();
                    });
                }
                x.setOnMouseClicked((event) -> {
                    placeTheCheckerSquaresBack();
                    blackPieceMover();
                });
//for (ImageView y : images)
                for (int counterChecker = 0; counterChecker < 64; counterChecker++) {
                    Rectangle y = checkerSquares[counterChecker][1];
                    y.setOnMouseClicked((event1) -> {
                        if (e1.getSource().equals(x)) {
//                Object x = event.getSource();
                            if ((isBlackMove == true) && (ClientGUI.PLAYER_COLOR.equals("BLACK") == true)) {
                                System.out.println("After move..... ");
//                            System.out.println("<><>Before move pos.... " + x.getX() + " , " + x.getY() + " ...... " + (x.getX() - boardImage.getX() + del) / delXOneUnit + " , " + (x.getY() - 300) / delYOneUnit);
//                            System.out.println("<><> Mouse pos is ..... " + event1.getX() + " , " + event1.getY() + "   .....  " + (event1.getX() - del) / delXOneUnit + " , " + (event1.getY() - 300) / delYOneUnit);

                                isBlackMove = false;
                                isWhiteMove = true;
                                //CHANGING NOW SATURDAY
                                //sendMove.fire();

                                changeBoard(x, event1);
                                e1.consume();
                                event1.consume();

                            }
                        }
                    });
                }
            });
        }

        for (ImageView x : blackPawn) { //BlACK OAWN MOVE
            x.setOnMouseClicked((e1) -> {

                int xCorrespondingCode = table.get(x);
                int initialPositionImageX = positionX(e1);
                int initialPositionImageY = positionY(e1);

                if ((ClientGUI.PLAYER_COLOR.equals("BLACK") == false) || (isWhiteMove == true) || (isBlackMove == false)) {    //ONLY BLACK CAN CALL THIS MOVE
                    System.out.println("lin 367 ... TODO NOT YOUR MOVE IN GUI .. THIS IS BLACK MOVE");

                    e1.consume();
                    return; //?
                }
                addImagePositionsForValidMoves(xCorrespondingCode, initialPositionImageX, initialPositionImageY);
                System.out.println("Clicked On Black Pawn!");
                for (ImageView z : allPiecesImages) {
                    if (z.equals(x) == false) {
                        z.setOnMouseClicked((event) -> {
                            blackPieceMover();
                        });
                    }
                }
                for (ImageView a : whitePawn) {
                    a.setOnMouseClicked((event) -> {
                        blackPieceMover();
                    });
                }
                for (ImageView a : blackPawn) {
                    a.setOnMouseClicked((event) -> {
                        blackPieceMover();
                    });
                }
                x.setOnMouseClicked((event) -> {
                    placeTheCheckerSquaresBack();
                    blackPieceMover();
                });
//                for (ImageView y : images) {
                for (int counterChecker = 0; counterChecker < 64; counterChecker++) {
                    Rectangle y = checkerSquares[counterChecker][1];
                    y.setOnMouseClicked((event1) -> {
                        if (e1.getSource().equals(x)) {
//                Object x = event.getSource();
                            if ((isBlackMove == true) && (ClientGUI.PLAYER_COLOR.equals("BLACK") == true)) {
//                            System.out.println("After move..... ");
//                            System.out.println("<><>Before move pos.... " + x.getX() + " , " + x.getY() + " ...... " + (x.getX() - boardImage.getX() + del) / delXOneUnit + " , " + (x.getY() - 300) / delYOneUnit);
//                            System.out.println("<><> Mouse pos is ..... " + event1.getX() + " , " + event1.getY() + "   .....  " + (event1.getX() - del) / delXOneUnit + " , " + (event1.getY() - 300) / delYOneUnit);

                                isBlackMove = false;
                                isWhiteMove = true;
                                //CHANGING NOW SATURDAY
                                //sendMove.fire();

                                changeBoard(x, event1);
                                e1.consume();
                                event1.consume();
                            }
                        }

                    });
//                    e1.consume();
                }
            });

        }
        pane.setOnMouseClicked((event) -> { // ?
            System.out.println("CANNOT DO THIS SINCE YOU ARE NOT WHITE");
            event.consume();
            return;
        });

        placeTheCheckerSquaresBack();

    }

    public static void printArray(int[][] a) {
        for (int[] b : a) {
            for (int x : b) {
                System.out.printf("% -4d ", x);
            }
            System.out.println("");
            System.out.println("");
        }
    }

    public static void changeBoard(ImageView img, MouseEvent event) {
        sendMove.fire();
        int pieceCode = table.get(img);
        System.out.println("INSIDE changeBoard >>>>>>>>>>>>>> WHITE: " + isWhiteMove + "  BLACK: " + isBlackMove);
        System.out.println("THIS PIECECODE IS >>>> " + pieceCode);
        ImageView x = img;
        int finalX = positionX(event), finalY = positionY(event);
        int initialX = positionX((img.getX())), initialY = positionY(img.getY());

        boolean isLegal = true;
        boardRepresentation f = new boardRepresentation();
        isLegal = f.checkLegality(pieceCode, initialX, initialY, finalX, finalY);
//------------------------------------***********_------------------------------------
//        System.out.println("<><><><><><><>IS IN CHECK <> ..... " + myBoard.isWhiteKingInCheckNow());
        System.out.println("Move legal:..... " + isLegal);
        System.out.println("<><><>INITIALS at " + initialX + " , " + initialY + " .... Final at " + finalX + " , " + finalY);

        if (isLegal == false) {
            ///TODO!!
//            blackPieceMover();
            return;
        }

        if (pieceCode > 0) {    //WHITE PLAYER DOES THIS
            boardRepresentation.updateWhiteBoard(pieceCode, initialX, initialY, finalX, finalY);
//            updateBoardImageAfterMoveIsDone();
        } else {
//            int inX=0 , inY=0, finX=0, finY =0;   /????
//            inX = 
            boardRepresentation.updateWhiteBoard(pieceCode, 7 - initialX, 7 - initialY, 7 - finalX, 7 - finalY);
//            updateBoardImageAfterMoveIsDone();
        }
        labelForWhoseMoveNow.setText("To Move: " + ((isWhiteMove == true) ? " WHITE" : " BLACK"));

//        myBoard.boardImage;
//        updateBoardAfterReceivedMessage();    //DO NOT DO THIS
//        myBoard.updateWhiteBoard(pieceCode, initialX, initialY, finalX, finalY);
        System.out.println(".... sending board array ... ");
        try {
            byte[] data = new byte[4000];
            DatagramSocket sock = new DatagramSocket();
            DatagramPacket pack = new DatagramPacket(data, data.length);
//            String msg = "Via: myChessGameServer" + "\nTo:" + ClientGUI.senderClientName + "\nFrom: " + ClientGUI.myName + "\nBody:  ";
            String msg = "Via: myChessGameServer" + "\nTo: " + ClientGUI.senderClientName + "\nFrom: " + ClientGUI.myName + "\nBody: ";
//            String l = String.valueOf(a);
//            String l = "NOW ABOUT TO MAKE MOVE >>><><<><><><<><";
//            String l = Arrays.deepToString(a);
            String l = new String();
            if (ClientGUI.PLAYER_COLOR.equals("WHITE") == true) {
                l = moveCounter + " Move " + String.valueOf(pieceCode) + " From " + "(" + String.valueOf(initialX) + " , " + String.valueOf(initialY) + " ) ";
                l = l + " To " + "<" + String.valueOf(finalX) + " , " + String.valueOf(finalY) + ">" + "  THIS_IS_WHITE ";
            } else {
                l = moveCounter + " Move " + String.valueOf(pieceCode) + " From " + "(" + String.valueOf(7 - initialX) + " , " + String.valueOf(7 - initialY) + " ) ";
                l = l + " To " + "<" + String.valueOf(7 - finalX) + " , " + String.valueOf(7 - finalY) + ">" + "  THIS_IS_BLACK ";
            }
            moveCounter++;
//            + Player.isBlackPlayerMoveNow + "  isBlack(playr1)  ";
//            l = l + Player.isBlackPlayerMoveNow + "  ...  Of Playr 2 (isWhit): " + Player.isWhiteMove + "  ,  (isBlack):  " + Player.isBlackMove;
//            l = l + " To " + "<" + String.valueOf(finalX) + " , " + String.valueOf(finalY) + ">";
//            String l = a.toString();
            String finalMsg = msg + l;
            System.out.println("**--------------**");
            System.out.println(finalMsg);
            System.out.println("**-------------***");
            data = (finalMsg).getBytes();
            pack.setData(data);
            pack.setAddress(InetAddress.getByName(ClientGUI.myServerIPAdress));
            pack.setPort(5050);
            sock.send(pack);
            sock.close();

        } catch (Exception e) {
            System.out.println(">>> NOT SENDING BOARD DATA ... ");
            //TODO UI 
            e.printStackTrace();
        }

//        updateBoardImageAfterMoveIsDone();
//        updateBoardAfterReceivedMessage();    // DO NOT DO THIS
        removeAllPiecesFromIntgerArray();
        setImagesFromIntegerArray();

//        initialTimer = System.currentTimeMillis();
        if (finalX == -10000 || finalY == -10000) {
            img.setX(img.getX());
            img.setY(img.getY());
            return;
        }
        placeTheCheckerSquaresBack();

        System.out.println("<><><><YOU KNW WHERE<><><");
        printArray(boardRepresentation.board);
        System.out.println("<><><><YOU KNW WHERE<><><");
        return;

    }

    public void testCASES() {
        pane.setOnMouseClicked((e) -> {
            int initX = 0, initY = 0;
            int myCalX = 55, myCalY = 90;
//            System.out.println("Mouse at ... " + e.getX() + " , " + e.getY());
            int posX = (int) e.getX() - myCalX;
            int posY = (int) e.getY() + myCalY;
//            int coX = ;
//            int coY = ;
            System.out.println("Inside test cases .....");
            System.out.println("Normal:.... " + e.getX() + " , " + e.getY());
            System.out.print(">>>> + POS X IS : .. " + positionX(e));
            System.out.println(" and POS Y IS : .. " + positionY(e));
//            System.out.println(">> .....PieceCode " + myBoard.getPieceFromWhiteBoard(positionX(e), positionY(e)));

        });

    }

    public static Stage window;

    public void start(Stage stage) {
        window = stage;
        stage.setScene(new Scene(createWhiteStandardBoard()));
        isWhiteMove = true;
        System.out.println("WHITE : " + isWhiteMove + " BLACK : " + isBlackMove);
        blackPieceMover();
//        testCASES();
        stage.show();
    }

    public static void addImagePositionsForValidMoves(int pieceCode, int initialPosX, int initialPosY) {

        int counterImage = 0;
//        System.out.println("line 964 ----*****------");
        int[][] a = myBoard.board;
        int finX, finY;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                finX = i;
                finY = j;
                if (myBoard.checkLegality(pieceCode, initialPosX, initialPosY, finX, finY) == true) {
//                    System.out.println(">>> INSIDE (lin 964) IF CLAUSE  piece is " + pieceCode + "  >>>> intialposistios are ... " + initialPosX + " , " + initialPosY + "  .. finals are ..  " + finX + " , " + finY);
//                    images[counterImage] = (new ImageView(img));
//                    setImage(images[counterImage], finX, finY);
                    setSquareRelativeToBoard(checkerSquares[counterImage][0], finX, finY);
                    setSquareRelativeToBoard(checkerSquares[counterImage][1], finX, finY);
                    counterImage++;
                }
            }
        }
//        System.out.println(">> counterImage value is  .. " + counterImage);
//        System.out.println("line 967 -------******_---------");
    }

}
