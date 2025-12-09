module Project3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.westga.cs1302.project3.view to javafx.fxml;

    exports edu.westga.cs1302.project3.view;
    exports edu.westga.cs1302.project3.viewmodel;
    exports edu.westga.cs1302.project3.model;
}
