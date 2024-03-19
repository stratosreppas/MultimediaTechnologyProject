/**
 * The Main class serves as the entry point for the Library Management GUI application.
 * It extends the Application class provided by JavaFX and contains methods for initializing
 * the JavaFX application, loading FXML files, and handling the primary stage.
 */
package com.example.librarygui;

import com.example.librarygui.controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage; // Declare a static stage variable
    private static Library library; // Declare a static Library variable

    /**
     * The main method instantiates the Library and launches the JavaFX application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        library = new Library(); // Instantiate Library only once
        launch(args); // Launch JavaFX application
    }

    /**
     * Overrides the start method of the Application class.
     * Initializes the primary stage and loads the initial FXML page (login_page.fxml).
     *
     * @param primaryStage The primary stage of the JavaFX application
     * @throws Exception if an error occurs during initialization
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage; // Set the primary stage

        // Load initial FXML page
        String initialFXMLPath = "login_page.fxml";
        loadFXML(initialFXMLPath);
    }

    /**
     * Overrides the stop method of the Application class.
     * Saves data before the application closes.
     */
    public void stop() {
        library.saveUsersAndAdmins();
        library.saveBooks();
        library.saveCategories();
        library.saveLoans();
        library.saveRatings();
    }

    /**
     * Loads the specified FXML file and sets the scene.
     * Initializes the controller and passes the Library instance.
     *
     * @param fxmlPath The path to the FXML file to be loaded
     */
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

    /**
     * Overloaded method to load FXML file with a highlighted object.
     * Initializes the controller and passes the Library instance.
     * The highlighted object is the object that is selected, if there is one (e.g. when editing a book, user, etc.).
     *
     * @param fxmlPath          The path to the FXML file to be loaded
     * @param highlightedObject The object to be highlighted
     */
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

    /**
     * Loads an item from the specified FXML file with a highlighted object. This is for all the views
     * that are on the main page (e.g. book_view.fxml, user_view.fxml, etc.).
     *
     * @param fxmlPath          The path to the FXML file to be loaded
     * @param highlightedObject The object to be highlighted
     * @return The loaded item as a Parent node
     */
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
