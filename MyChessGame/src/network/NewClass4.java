/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 *
 * @author esfs
 */
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import static network.Player.*;
import javafx.scene.text.*;

public class NewClass4 extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    static long clickedTime;
    static long delTime;
//    static long delTimer = 3000;
    static Timer waitingForReceiverMoveStopWatch = new Timer();
    static Timer waitingForMyMoveStopWatch = new Timer();
    static Timer toDoTaskTimer = new Timer();
    static Timer backgroundStopWatch = new Timer();
    static long delChange = 3000;
//    static long clickedTime ;

    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();
        Button sendMove = new Button("Send Move");
        Button receiveMove = new Button("Wait(Received_Move)");
        Button stopAll = new Button("Stop All");
        Label label2 = new Label();
        Label label1 = new Label();
        Label label3 = new Label();
        Label label4 = new Label();
        VBox hbox = new VBox(label4, label1, label2, label3);
        hbox.setSpacing(15);
        VBox box = new VBox(sendMove, receiveMove, stopAll, hbox);
        box.setSpacing(15);

        pane.getChildren().addAll(box);
        Scene scene = new Scene(pane, 500, 500);

        long veryInitialTime = System.currentTimeMillis();
//        clickedTime = veryInitialTime;
        backgroundStopWatch.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
//                        timer2.cancel();
                        label4.setText(String.valueOf(System.currentTimeMillis() - veryInitialTime));//+ (System.currentTimeMillis() - initialTimer));

                    }
                });
            }
        }, 0, 500);

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
                            label3.setText("(Do after receiving Move)My Move timer ... (my Move) ..." + String.valueOf(System.currentTimeMillis() - clickedTime));//+ (System.currentTimeMillis() - initialTimer));

                        }
                    });
                }
            }, 0, 500);
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
                            label3.setText("I didn't make move ... YOU LOSE AFTER (time) (to do)10s ");//+ (System.currentTimeMillis() - initialTimer));

//                            label3.setText("(Move not received in due time) (time) (to do)10s ");//+ (System.currentTimeMillis() - initialTimer));
                        }
                    });
                }
            }, delChange);
        });

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
                            label2.setText("(Do After Send MyMove) (waiting for opponentMove) ..." + String.valueOf(System.currentTimeMillis() - clickedTime));//+ (System.currentTimeMillis() - initialTimer));

                        }
                    });
                }
            }, 0, 500);
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

                        }
                    });
                }
            }, delChange);
        });

        stopAll.setOnAction((event) -> {
//            toDoTaskTimer.cancel();
//            backgroundStopWatch.cancel();
//            waitingForReceiverMoveStopWatch.cancel();
//            sendMove.fi
        });
        stage.setScene(scene);
        stage.show();

    }

}
