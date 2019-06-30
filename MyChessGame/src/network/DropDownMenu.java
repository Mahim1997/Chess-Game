package network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DropDownMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Button button;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("DROP DOWN");
        button = new Button("CLICK");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();//For same data

        //get items retusn the observableList
        List<String> l = new ArrayList<>();
        String[] arr = {"Click for Participents", "APPLES", "BANANA", "NAMES", "FRUITS"};
        l = Arrays.asList(arr);
//        choiceBox.getItems().addAll("APPLES", "BANANA", "NAMES");
        choiceBox.getItems().addAll(l);

        VBox layout = new VBox();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(choiceBox, button);
        choiceBox.setValue("Click for Participents");
        String accessibleText = choiceBox.getAccessibleText();

        button.setOnAction((ActionEvent event) -> {
            System.out.println(choiceBox.getValue());
//                String s =(choiceBox.getSelectionModel().select(0));
        });

        Scene scene = new Scene(layout, 350, 450);
        stage.setScene(scene);

        stage.show();
    }

}
