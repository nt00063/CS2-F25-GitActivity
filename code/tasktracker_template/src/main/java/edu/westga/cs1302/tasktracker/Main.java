package edu.westga.cs1302.tasktracker;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * Main entry point for the Task Tracker application.
 */
public class Main extends Application {

    private static final String GUI_RESOURCE = "edu/westga/cs1302/tasktracker/views/MainWindow.fxml";
    private static final String WINDOW_TITLE = "Task Tracker";

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(GUI_RESOURCE));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle(WINDOW_TITLE);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
