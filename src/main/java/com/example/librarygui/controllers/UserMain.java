package com.example.librarygui.controllers;

import com.example.librarygui.Book;
import com.example.librarygui.Loan;
import com.example.librarygui.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class UserMain extends MainPageController{

    String username;

    @FXML
    private Text welcomeText;

    public void init() {
        BookTilePane.getChildren().clear();
        LoanVBox.getChildren().clear();

        username = getCurrentUser();
        welcomeText.setText("Welcome, " + username + "!");

        displayBooks(library.books);
        displayLoans(library.getUsersLoans(username));
    }

}
