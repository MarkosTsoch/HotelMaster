package ui.alertbox;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {

    public static void display(String message) {

        /**
         * Initialize Stage
         */
        Stage stage = new Stage();

        /**
         * Create Window
         */
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("User finder");
        stage.setMinWidth(250);
        stage.setMinHeight(150);

        /**
         * Label
         */
        Label label = new Label();
        label.setText(message);

        /**
         * Button & Action
         */
        Button closeButton = new Button("Continue");
        closeButton.setOnAction(event -> stage.close());

        /**
         * Panel
         */
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(label, closeButton);
        vBox.setAlignment(Pos.CENTER);

        /**
         * Scene
         */
        Scene scene3 = new Scene(vBox);

        /**
         * Windows Settings
         */
        stage.getIcons().add(new Image("/resources/hotel.png"));
        stage.centerOnScreen();
        stage.setScene(scene3);
        stage.showAndWait();
    }
}