module edu.westga.cs1302.tasktracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;

    opens edu.westga.cs1302.tasktracker to javafx.fxml;
    opens edu.westga.cs1302.tasktracker.model to javafx.fxml;
}
