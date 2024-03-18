package com.example.librarygui.controllers;

import com.example.librarygui.Book;
import com.example.librarygui.Main;
import com.example.librarygui.interfaces.Banner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.util.List;

import static javafx.application.Application.launch;

public class LoginPage extends Controller{
    public TextField usernameField;
    public TextField passwordField;
    @FXML
    private HBox BookHBox;

    public void init() {
        List<Book> top_books = library.sortBooks("Rating", "Descending");
        if (top_books == null) return;
        System.out.println(top_books);
        for (int i = 0; i < 5; i++) {
            if (i >= top_books.size()) return;
            Parent bookItem = Main.loadItem("book_view.fxml", top_books.get(i));
            // Customize the user item if necessary
            BookHBox.getChildren().add(bookItem);
        }
    }

    public void goToSignUp(ActionEvent event) throws Exception {
        Main.loadFXML("signup_page.fxml", null);
    }

    public void login(ActionEvent event) throws Exception {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("") || password.equals("")) {
            Banner.showErrorBanner("Error", "Please fill in all fields");
            return;
        }
        for (int i = 0; i < library.users.size(); i++) {
            if (library.users.get(i).username.equals(username) && library.users.get(i).password.equals(password)) {
                Banner.showInformationDialog("Success", "Logged in successfully");
                library.loggedUser = library.users.get(i);
                if(library.isAdmin()) Main.loadFXML("admin_main_page.fxml");
                else
                    Main.loadFXML("user_main_page.fxml");
                return;
            }
        }
        Banner.showErrorBanner("Error", "Incorrect username or password");
    }
}
