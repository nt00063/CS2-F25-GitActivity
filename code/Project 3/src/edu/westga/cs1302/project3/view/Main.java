package edu.westga.cs1302.project3.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point for the Comic Collection application.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
            this.getClass().getResource("MainWindow.fxml")
        );
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Comic Collection");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches the Comic Collection application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
