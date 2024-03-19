package com.example.librarygui.controllers;

import com.example.librarygui.Library;
import com.example.librarygui.Main;
import com.example.librarygui.interfaces.Banner;
import javafx.scene.input.KeyEvent;

public class Controller {

    protected Library library;
    protected Object highlightedObject;

    public void setLibrary(Library library) {
        this.library = library;
    }

    public void setHighlightedObject(Object highlightedObject) {
        this.highlightedObject = highlightedObject;
    }

    public String getCurrentUser() {
        return library.loggedUser.getUsername();
    }

    public void init() {
        // Override this method in the child class
    }

    // Handle goBack functionality
    public void back() {
        String fxmlPath = library.isAdmin() ? "admin_main_page.fxml" : "user_main_page.fxml";
        Main.loadFXML(fxmlPath);
    }

    public void refresh() {
        init();
    }

    public void logout() {
        if(Banner.showConfirmationDialog("Logout", "Are you sure you want to logout?")) {
            library.loggedUser = null;
            Main.loadFXML("login_page.fxml");
        }
    }


}