/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import static network.PopUpWindow.btn;

/**
 *
 * @author esfs
 */
public class PopUpWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static void displayInCheck(String title, String messageToDisplay) {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL); // Block Input events with other windows until this closes
        stage.setTitle(title);

        stage.setMinWidth(300);

        Label label = new Label();
        label.setText(messageToDisplay);
        Button btn = new Button();
        btn = new Button("Close");
        btn.setOnAction((event) -> {
            stage.close();
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(label, btn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();    //DISPLAY ALERT BOX and before returning it should be cloes
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        displayInCheck("TITLE","msg");
        StackPane layout = new StackPane();
        Button bttt = new Button("CLICK HERE");
        primaryStage.setOnCloseRequest((event) -> {
            System.out.println("CLOSE");
        });
        bttt.setOnAction((event) -> {
            System.out.println("Hello");
        });
        layout.getChildren().add(bttt);
        primaryStage.setScene(new Scene(layout,500,500));
        primaryStage.show();
    }
}

//public class PopUpWindow extends Application {
//    public static void main(String[] args) {
//        launch(args);
//    }
//    static Button btn;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
////        Test.displayInCheck("TITLE", "HELLO");
//    }
//
//}
