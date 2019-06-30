package network;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import static network.Player.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayerInitialiser implements PieceCodeValues, PieceImagePosition, PieceImageIndex{

    public static Label labelForMovesCounter = new Label();
    public static Label labelForWhoseMoveNow = new Label();
    public static Button endButton = new Button("End Game");
    public static Label labelForEnd = new Label();
    public static HBox hBox;

    public PlayerInitialiser() {

    }

    public static void initialiseBlackImages() {
//        wN = new ImageView(new File("C:\\Users\\esfs\\Desktop\\ChessProject\\src\\BinaryContent\\whiteKnight.png").toURI().toString());
        allPiecesImages[blackrook1ImagePos] = new ImageView("BinaryContent\\blackRook.png");
        allPiecesImages[blackrook2ImagePos] = new ImageView("BinaryContent\\blackRook.png");
        allPiecesImages[blackknight2ImagePos] = new ImageView("BinaryContent\\blackKnight.png");
        allPiecesImages[blackknight1ImagePos] = new ImageView("BinaryContent\\blackKnight.png");
        allPiecesImages[blackbishop1ImagePos] = new ImageView("BinaryContent\\blackBishop.png");
        allPiecesImages[blackbishop2ImagePos] = new ImageView("BinaryContent\\blackBishop.png");
        allPiecesImages[blackQueenImagePos] = new ImageView("BinaryContent\\blackQueen.png");
        allPiecesImages[blackKingImagePos] = new ImageView("BinaryContent\\blackKing.png");

        for (int i = 0; i < 8; i++) {
            blackPawn[i] = new ImageView("BinaryContent\\blackPawn.png");
        }

    }

    public static void initialiseWhiteImages() {
        allPiecesImages[whiteknight1ImagePos] = new ImageView("BinaryContent\\whiteKnight.png");
        allPiecesImages[whiteknight2ImagePos] = new ImageView("BinaryContent\\whiteKnight.png");
        allPiecesImages[whitebishop1ImagePos] = new ImageView("BinaryContent\\whiteBishop.png");
        allPiecesImages[whitebishop2ImagePos] = new ImageView("BinaryContent\\whiteBishop.png");

        allPiecesImages[whiteQueenImagePos] = new ImageView("BinaryContent\\whiteQueen.png");
        allPiecesImages[whiterook1ImagePos] = new ImageView("BinaryContent\\whiteRook.png");
        allPiecesImages[whiterook2ImagePos] = new ImageView("BinaryContent\\whiteRook.png");
        allPiecesImages[whiteKingImagePos] = new ImageView("BinaryContent\\whiteKing.png");

        for (int i = 0; i < 8; i++) {
            whitePawn[i] = new ImageView("BinaryContent\\whitePawn.png");
        }
    }

    public static void initialiseCodesToImagesTable() {
//        pieceCodetoImageTable.put(wNC2, allPiecesImages[whiteknight2ImagePos]);
        pieceCodetoImageTable.put(wNC, allPiecesImages[whiteknight1ImagePos]);
        pieceCodetoImageTable.put(wNC2, allPiecesImages[whiteknight2ImagePos]);
        pieceCodetoImageTable.put(wBC, allPiecesImages[whitebishop1ImagePos]);
        pieceCodetoImageTable.put(wBC2, allPiecesImages[whitebishop2ImagePos]);
        pieceCodetoImageTable.put(wQC, allPiecesImages[whiteQueenImagePos]);
        pieceCodetoImageTable.put(wKC, allPiecesImages[whiteKingImagePos]);
        pieceCodetoImageTable.put(wRC, allPiecesImages[whiterook1ImagePos]);
        pieceCodetoImageTable.put(wRC2, allPiecesImages[whiterook2ImagePos]);

        pieceCodetoImageTable.put(bNC, allPiecesImages[blackknight1ImagePos]);
        pieceCodetoImageTable.put(bNC2, allPiecesImages[blackknight2ImagePos]);
        pieceCodetoImageTable.put(bBC, allPiecesImages[blackbishop1ImagePos]);
        pieceCodetoImageTable.put(bBC2, allPiecesImages[blackbishop2ImagePos]);
        pieceCodetoImageTable.put(bQC, allPiecesImages[blackQueenImagePos]);
        pieceCodetoImageTable.put(bKC, allPiecesImages[blackKingImagePos]);
        pieceCodetoImageTable.put(bRC, allPiecesImages[blackrook1ImagePos]);
        pieceCodetoImageTable.put(bRC2, allPiecesImages[blackrook2ImagePos]);

        pieceCodetoImageTable.put(wPC1, whitePawn[0]);
        pieceCodetoImageTable.put(wPC2, whitePawn[1]);
        pieceCodetoImageTable.put(wPC3, whitePawn[2]);
        pieceCodetoImageTable.put(wPC4, whitePawn[3]);
        pieceCodetoImageTable.put(wPC5, whitePawn[4]);
        pieceCodetoImageTable.put(wPC6, whitePawn[5]);
        pieceCodetoImageTable.put(wPC7, whitePawn[6]);
        pieceCodetoImageTable.put(wPC8, whitePawn[7]);

        pieceCodetoImageTable.put(bPC1, blackPawn[0]);
        pieceCodetoImageTable.put(bPC2, blackPawn[1]);
        pieceCodetoImageTable.put(bPC3, blackPawn[2]);
        pieceCodetoImageTable.put(bPC4, blackPawn[3]);
        pieceCodetoImageTable.put(bPC5, blackPawn[4]);
        pieceCodetoImageTable.put(bPC6, blackPawn[5]);
        pieceCodetoImageTable.put(bPC7, blackPawn[6]);
        pieceCodetoImageTable.put(bPC8, blackPawn[7]);

    }

    public static void initialiseTable() {
        //TESTING ONLYE

        table.put(allPiecesImages[whiteknight1ImagePos], wNC);
        table.put(allPiecesImages[whiteknight2ImagePos], wNC2);
        table.put(allPiecesImages[whitebishop1ImagePos], wBC);
        table.put(allPiecesImages[whitebishop2ImagePos], wBC2);
        table.put(allPiecesImages[whiteQueenImagePos], wQC);
        table.put(allPiecesImages[whiteKingImagePos], wKC);
        table.put(allPiecesImages[whiterook1ImagePos], wRC);
        table.put(allPiecesImages[whiterook2ImagePos], wRC2);

        table.put(allPiecesImages[blackknight1ImagePos], bNC);
        table.put(allPiecesImages[blackknight2ImagePos], bNC2);
        table.put(allPiecesImages[blackbishop1ImagePos], bBC);
        table.put(allPiecesImages[blackbishop2ImagePos], bBC2);
        table.put(allPiecesImages[blackQueenImagePos], bQC);
        table.put(allPiecesImages[blackKingImagePos], bKC);
        table.put(allPiecesImages[blackrook1ImagePos], bRC);
        table.put(allPiecesImages[blackrook2ImagePos], bRC2);

        table.put(whitePawn[0], wPC1);
        table.put(whitePawn[1], wPC2);
        table.put(whitePawn[2], wPC3);
        table.put(whitePawn[3], wPC4);
        table.put(whitePawn[4], wPC5);
        table.put(whitePawn[5], wPC6);
        table.put(whitePawn[6], wPC7);
        table.put(whitePawn[7], wPC8);

        table.put(blackPawn[0], bPC1);
        table.put(blackPawn[1], bPC2);
        table.put(blackPawn[2], bPC3);
        table.put(blackPawn[3], bPC4);
        table.put(blackPawn[4], bPC5);
        table.put(blackPawn[5], bPC6);
        table.put(blackPawn[6], bPC7);
        table.put(blackPawn[7], bPC8);

//        for (int i = 0; i < 8; i++) {
//            table.put(whitePawn[i], wPC);
//        }
//        for (int i = 0; i < 8; i++) {
//            table.put(blackPawn[i], bPC);
//        }
    }
    public static VBox box;
//    public static Label labelForTime = new Label();
    public static HBox buttonBox = new HBox();
    public static Button sendMove = new Button("Send Move");
    public static Button receiveMove = new Button("Wait for Receive Move");
    public static VBox vBoxNew = new VBox();

    public static void initialiseImages() {
//                ImageView img = new ImageView("BinaryContent/blackRook.png");
        boardImage = new ImageView("BinaryContent\\chessBoard.png");//.toURI().toString());
        initialiseBlackImages();
        initialiseWhiteImages();

        labelForMovesCounter.setLayoutX(650);
        labelForMovesCounter.setLayoutY(boardImage.getY() + 100);
        labelForMovesCounter.setText("Move no: ");
        labelForMovesCounter.setTextFill(Color.LIMEGREEN);
        labelForMovesCounter.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 17));

        labelForWhoseMoveNow.setLayoutX(750);
        labelForWhoseMoveNow.setLayoutY(boardImage.getY() + 100);
        labelForWhoseMoveNow.setText("Whose Move: ");
        labelForWhoseMoveNow.setTextFill(Color.INDIGO);
        labelForWhoseMoveNow.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 17));

        labelForEnd.setLayoutX(680);
        labelForEnd.setLayoutY(100);
        hBox = new HBox();
        hBox.getChildren().addAll(labelForMovesCounter, endButton);
        hBox.setSpacing(175);
        box = new VBox(hBox, labelForWhoseMoveNow);
        box.setLayoutX(0);
        box.setLayoutY(-200);

        for (int i = 0; i < 64; i++) {
            int size = 50;
            checkerSquares[i][0] = new Rectangle(size, size, Color.MAGENTA);
            checkerSquares[i][1] = new Rectangle(size, size, Color.TRANSPARENT);
            setSquareRelativeToBoard(checkerSquares[i][0], 11, 2);
            setSquareRelativeToBoard(checkerSquares[i][1], 11, 3);  //INITIALISE POSITIONS FOR SQUARES
        }
        System.out.println(">>inside (Initialiser)initialiseImages .. white board representation is made lin 209");
        boardRepresentation.createWhiteBoardRepresentation();
        boardRepresentation.printWhiteBoard();
        System.out.println(">>inside (Initialiser)initialiseImages .. white board representation is made lin 209");
    }

    public static Pane createBlackStandardBoard() {
        initialiseImages();
        initialiseTable();
        initialiseCodesToImagesTable();
        pane.setLayoutX(12);
        pane.setLayoutY(222);
        pane.setPrefWidth(1026);
        pane.setPrefHeight(500);

        boardImage.setFitHeight(height);
        boardImage.setFitWidth(width);
        boardImage.setX(initX);
        boardImage.setY(initY);

        allPiecesImages[whiterook1ImagePos].setX(boardImage.getX() + del);
        allPiecesImages[whiterook1ImagePos].setY(boardImage.getY() + del);
        allPiecesImages[whiterook2ImagePos].setX(boardImage.getX() + del + delXOneUnit * 7);
        allPiecesImages[whiterook2ImagePos].setY(boardImage.getY() + del);

        allPiecesImages[whiteknight1ImagePos].setX(boardImage.getX() + del + delXOneUnit * 1);
        allPiecesImages[whiteknight1ImagePos].setY(boardImage.getY() + del);

        allPiecesImages[whiteknight2ImagePos].setX(boardImage.getX() + del + delXOneUnit * 6);
        allPiecesImages[whiteknight2ImagePos].setY(boardImage.getY() + del);

        allPiecesImages[whitebishop1ImagePos].setX(boardImage.getX() + del + delXOneUnit * 2);
        allPiecesImages[whitebishop1ImagePos].setY(boardImage.getY() + del);

        allPiecesImages[whitebishop2ImagePos].setX(boardImage.getX() + del + delXOneUnit * 5);
        allPiecesImages[whitebishop2ImagePos].setY(boardImage.getY() + del);

        allPiecesImages[whiteKingImagePos].setX(boardImage.getX() + del + delXOneUnit * 3);
        allPiecesImages[whiteKingImagePos].setY(boardImage.getY() + del);

        allPiecesImages[whiteQueenImagePos].setX(boardImage.getX() + del + delXOneUnit * 4);
        allPiecesImages[whiteQueenImagePos].setY(boardImage.getY() + del);

        for (int i = 0; i < 8; i++) {
            whitePawn[i].setX(boardImage.getX() + del + delXOneUnit * i);
            whitePawn[i].setY(boardImage.getY() + del + delYOneUnit * 1);
        }

        for (int i = 0; i < 8; i++) {
            blackPawn[i].setX(boardImage.getX() + del + delXOneUnit * i);
            blackPawn[i].setY(boardImage.getY() + del + delYOneUnit * 6);
        }
        //WHITE PICES

        allPiecesImages[blackrook1ImagePos].setX(boardImage.getX() + del);
        allPiecesImages[blackrook1ImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);
        allPiecesImages[blackrook2ImagePos].setX(boardImage.getX() + del + delXOneUnit * 7);
        allPiecesImages[blackrook2ImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

//        file = new File("C:\\Users\\esfs\\Desktop\\ChessProject\\src\\BinaryContent\\blackKnight.png");
//        wN = new ImageView(file.toURI().toString());
        allPiecesImages[blackknight1ImagePos].setX(boardImage.getX() + del + delXOneUnit * 1);
        allPiecesImages[blackknight1ImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

        allPiecesImages[blackknight2ImagePos].setX(boardImage.getX() + del + delXOneUnit * 6);
        allPiecesImages[blackknight2ImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

        allPiecesImages[blackbishop1ImagePos].setX(boardImage.getX() + del + delXOneUnit * 2);
        allPiecesImages[blackbishop1ImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

        allPiecesImages[blackbishop2ImagePos].setX(boardImage.getX() + del + delXOneUnit * 5);
        allPiecesImages[blackbishop2ImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

        allPiecesImages[blackKingImagePos].setX(boardImage.getX() + del + delXOneUnit * 3);
        allPiecesImages[blackKingImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

        allPiecesImages[blackQueenImagePos].setX(boardImage.getX() + del + delXOneUnit * 4);
        allPiecesImages[blackQueenImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

//        //        //RECENTLY ADDED
//        pane.getChildren().addAll(labelForMovesCounter, labelForWhoseMoveNow);
//                buttonBox.getChildren().addAll(sendMove, receiveMove);
        pane.getChildren().addAll(boardImage);
        pane.getChildren().addAll(labelForEnd, vBoxNew, buttonBox);
//        pane.getChildren().addAll(labelForMovesCounter, labelForWhoseMoveNow);
//        ADD NORMAL RECTANGLE IMAGE i.e POSITION 0 
        for (int i = 0; i < 64; i++) {
            pane.getChildren().addAll(checkerSquares[i][0]);
        }
        for (int i = 0; i < 16; i++) {
            pane.getChildren().addAll(allPiecesImages[i]);
        }
        for (int i = 0; i < blackPawn.length; i++) {
            pane.getChildren().addAll(blackPawn[i]);
        }
        for (int i = 0; i < whitePawn.length; i++) {
            pane.getChildren().addAll(whitePawn[i]);
        }

//        ADD THE TRANSPARENT RECTANGLE IMAGE i.e POSITION 1
        for (int i = 0; i < 64; i++) {
            pane.getChildren().addAll(checkerSquares[i][1]);
        }
        //RECENTLY ADDED
//        HBox vBoxNew = new HBox(label1, label2, label3);
        vBoxNew.getChildren().addAll(label1, label2, label3);
        vBoxNew.setSpacing(15);
        vBoxNew.setLayoutX(100);
        vBoxNew.setLayoutY(-250);
        label1.setTextFill(Color.BROWN);
        label2.setTextFill(Color.BLUEVIOLET);
        label3.setTextFill(Color.TEAL);
        label1.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 17));
        label2.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 17));

        label3.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 17));

//        label1.setText("Timer1");
//        label2.setText("Timer2");
//        label3.setText("Concluder");
        buttonBox.setSpacing(10);
        buttonBox.setLayoutX(100);
        buttonBox.setLayoutY(-500);
//        buttonBox.getChildren().addAll(sendMove, receiveMove);
        //        //RECENTLY ADDED
        pane.getChildren().addAll(box);

//        pane.getChildren().addAll(boardImage, bR2, bN2, bB2, wR2, wN2, wB2, bR, bN, bB, bK, bQ, wR, wN, wB, wK,wQ);
//        System.out.println(">>> + " + boardImage.getX() + "  ,  " + boardImage.getY());
        return pane;
//        return new Scene(pane);
    }

    public static Pane createWhiteStandardBoard() {

        initialiseImages();
        initialiseTable();
        initialiseCodesToImagesTable();
        pane.setLayoutX(12);
        pane.setLayoutY(222);
        pane.setPrefWidth(1026);
        pane.setPrefHeight(500);

        boardImage.setFitHeight(height);
        boardImage.setFitWidth(width);
        boardImage.setX(initX);
        boardImage.setY(initY);

        allPiecesImages[blackrook1ImagePos].setX(boardImage.getX() + del);
        allPiecesImages[blackrook1ImagePos].setY(boardImage.getY() + del);
        allPiecesImages[blackrook2ImagePos].setX(boardImage.getX() + del + delXOneUnit * 7);
        allPiecesImages[blackrook2ImagePos].setY(boardImage.getY() + del);

        allPiecesImages[blackknight1ImagePos].setX(boardImage.getX() + del + delXOneUnit * 1);
        allPiecesImages[blackknight1ImagePos].setY(boardImage.getY() + del);

        allPiecesImages[blackknight2ImagePos].setX(boardImage.getX() + del + delXOneUnit * 6);
        allPiecesImages[blackknight2ImagePos].setY(boardImage.getY() + del);

        allPiecesImages[blackbishop1ImagePos].setX(boardImage.getX() + del + delXOneUnit * 2);
        allPiecesImages[blackbishop1ImagePos].setY(boardImage.getY() + del);

        allPiecesImages[blackbishop2ImagePos].setX(boardImage.getX() + del + delXOneUnit * 5);
        allPiecesImages[blackbishop2ImagePos].setY(boardImage.getY() + del);

        allPiecesImages[blackQueenImagePos].setX(boardImage.getX() + del + delXOneUnit * 3);
        allPiecesImages[blackQueenImagePos].setY(boardImage.getY() + del);

        allPiecesImages[blackKingImagePos].setX(boardImage.getX() + del + delXOneUnit * 4);
        allPiecesImages[blackKingImagePos].setY(boardImage.getY() + del);
        for (int i = 0; i < 8; i++) {
            whitePawn[i].setX(boardImage.getX() + del + delXOneUnit * i);
            whitePawn[i].setY(boardImage.getY() + del + delYOneUnit * 6);
        }
        //WHITE PICES

        allPiecesImages[whiterook1ImagePos].setX(boardImage.getX() + del);
        allPiecesImages[whiterook1ImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);
        allPiecesImages[whiterook2ImagePos].setX(boardImage.getX() + del + delXOneUnit * 7);
        allPiecesImages[whiterook2ImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

//        file = new File("C:\\Users\\esfs\\Desktop\\ChessProject\\src\\BinaryContent\\whiteKnight.png");
//        wN = new ImageView(file.toURI().toString());
        allPiecesImages[whiteknight1ImagePos].setX(boardImage.getX() + del + delXOneUnit * 1);
        allPiecesImages[whiteknight1ImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

        allPiecesImages[whiteknight2ImagePos].setX(boardImage.getX() + del + delXOneUnit * 6);
        allPiecesImages[whiteknight2ImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

        allPiecesImages[whitebishop1ImagePos].setX(boardImage.getX() + del + delXOneUnit * 2);
        allPiecesImages[whitebishop1ImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

        allPiecesImages[whitebishop2ImagePos].setX(boardImage.getX() + del + delXOneUnit * 5);
        allPiecesImages[whitebishop2ImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

        allPiecesImages[whiteQueenImagePos].setX(boardImage.getX() + del + delXOneUnit * 3);
        allPiecesImages[whiteQueenImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

        allPiecesImages[whiteKingImagePos].setX(boardImage.getX() + del + delXOneUnit * 4);
        allPiecesImages[whiteKingImagePos].setY(boardImage.getY() + del + delYOneUnit * 7);

        for (int i = 0; i < 8; i++) {
            blackPawn[i].setX(boardImage.getX() + del + delXOneUnit * i);
            blackPawn[i].setY(boardImage.getY() + del + delYOneUnit * 1);
        }
//        //        //RECENTLY ADDED
//        pane.getChildren().addAll(labelForMovesCounter, labelForWhoseMoveNow);

        pane.getChildren().addAll(boardImage);
        pane.getChildren().addAll(labelForEnd, vBoxNew, buttonBox);

//        ADD NORMAL RECTANGLE i.e POSITION 0
        for (int i = 0; i < 64; i++) {
            pane.getChildren().addAll(checkerSquares[i][0]);
        }
        for (int i = 0; i < 16; i++) {
            pane.getChildren().addAll(allPiecesImages[i]);
        }
        for (int i = 0; i < blackPawn.length; i++) {
            pane.getChildren().addAll(blackPawn[i]);
        }
        for (int i = 0; i < whitePawn.length; i++) {
            pane.getChildren().addAll(whitePawn[i]);
        }
        label1.setTextFill(Color.BROWN);
        label2.setTextFill(Color.BLUEVIOLET);
        label3.setTextFill(Color.TEAL);
        label1.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 17));
        label2.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 17));

        label3.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 17));
//        ADD THE TRANSPARENT RECTANGLE IMAGE i.e POSITION 1
        for (int i = 0; i < 64; i++) {
            pane.getChildren().addAll(checkerSquares[i][1]);
        }
        //RECENTLY ADDED
        //        //RECENTLY ADDED
//        HBox vBoxNew = new HBox(label1, label2, label3);
        vBoxNew.getChildren().addAll(label1, label2, label3);
        vBoxNew.setSpacing(15);
        vBoxNew.setLayoutX(100);
        vBoxNew.setLayoutY(-250);
//        label1.setText("Timer1");
//        label2.setText("Timer2");
//        label3.setText("Concluder");
        buttonBox.setSpacing(10);
        buttonBox.setLayoutX(100);
        buttonBox.setLayoutY(-510);
        buttonBox.getChildren().addAll(sendMove, receiveMove);
        pane.getChildren().addAll(box);//, labelForEnd, labelForTime, vBoxNew, buttonBox);


        double layoutX = labelForMovesCounter.getLayoutX();
        double layoutY = labelForMovesCounter.getLayoutY();
        System.out.print(">>>>>>>>IN lin 400         ");
        System.out.println(layoutX + " , " + layoutY);
        return pane;

    }
}
