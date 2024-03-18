module com.example.librarygui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.librarygui to javafx.fxml;
    exports com.example.librarygui;
    exports com.example.librarygui.controllers;
    opens com.example.librarygui.controllers to javafx.fxml;
    exports com.example.librarygui.interfaces;
    opens com.example.librarygui.interfaces to javafx.fxml;
}