package edu.westga.cs1302.tasktracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application entry point for Task Tracker.
 * 
 * @author NoahG
 * @version Fall 2025
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/MainWindow.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Task Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches the application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
