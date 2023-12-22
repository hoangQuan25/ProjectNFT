package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UI extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Create a BorderPane
            BorderPane root = new BorderPane();

            // Create a Label with the text "binzuy"
            Label label = new Label("binzuy");

            // Set the alignment of the label to the center of the BorderPane
            BorderPane.setAlignment(label, Pos.CENTER);

            // Add the label to the center of the BorderPane
            root.setCenter(label);

            // Create a Scene
            Scene scene = new Scene(root, 400, 400);

            // Set the scene to the stage
            primaryStage.setScene(scene);

            // Show the stage
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}