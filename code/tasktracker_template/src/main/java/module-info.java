module edu.westga.cs1302.tasktracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens edu.westga.cs1302.tasktracker.views to javafx.fxml;
    exports edu.westga.cs1302.tasktracker;
    exports edu.westga.cs1302.tasktracker.views;
    exports edu.westga.cs1302.tasktracker.model;
}
