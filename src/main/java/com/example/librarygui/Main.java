package com.example.librarygui;

import com.example.librarygui.controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage; // Declare a static stage variable
    private static Library library; // Declare a static Library variable

    public static void main(String[] args) {
        library = new Library(); // Instantiate Library only once
        launch(args); // Launch JavaFX application
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage; // Set the primary stage

        // Load initial FXML page
        String initialFXMLPath = "login_page.fxml";
        loadFXML(initialFXMLPath);
    }

    public void stop() {
        library.saveUsersAndAdmins();
        library.saveBooks();
        library.saveCategories();
        library.saveLoans();
        library.saveRatings();
    }

    public static void loadFXML(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
            Parent root = loader.load();

            // Get the controller instance and pass the Library instance
            Controller controller = loader.getController();
            controller.setLibrary(library);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            controller.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFXML(String fxmlPath, Object highlightedObject) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
            Parent root = loader.load();

            // Get the controller instance and pass the Library instance
            Controller controller = loader.getController();
            controller.setLibrary(library);
            controller.setHighlightedObject(highlightedObject);

            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            controller.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Parent loadItem(String fxmlPath, Object highlightedObject) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
            Parent item = loader.load();
            Controller controller = loader.getController();
            controller.setLibrary(library);
            controller.setHighlightedObject(highlightedObject);
            controller.init();
            return item;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
