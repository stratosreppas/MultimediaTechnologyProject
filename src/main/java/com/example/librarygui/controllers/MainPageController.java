package com.example.librarygui.controllers;

import com.example.librarygui.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.List;

public class MainPageController extends Controller {

    public TextField BookSearchField;
    public TextField UserSearchField;
    public TextField LoanSearchField;
    public TextField CategorySearchField;

    public TilePane BookTilePane;
    public TilePane UserTilePane;
    public VBox LoanVBox;
    public VBox CategoryVBox;

    public void displayUsers(List<User> users) {
        if (users == null) return;
        for (User user : users) {
            Parent userItem = Main.loadItem("user_view.fxml", user);
            // Customize the user item if necessary
            UserTilePane.getChildren().add(userItem);
        }
    }

    public void displayBooks(List<Book> books) {
        if (books == null) return;
        for (Book book : books) {
            Parent bookItem = Main.loadItem("book_view.fxml", book);
            // Customize the book item if necessary
            BookTilePane.getChildren().add(bookItem);
        }
    }

    public void displayLoans(List<Loan> loans) {
        if (loans == null) return;
        for (Loan loan : loans) {
            Parent loanItem = Main.loadItem("loan_view.fxml", loan);
            // Customize the loan item if necessary
            LoanVBox.getChildren().add(loanItem);
        }
    }

    public void displayCategories(List<Category> categories) {
        if (categories == null) return;
        for (Category category : categories) {
            Parent categoryItem = Main.loadItem("category_view.fxml", category);
            // Customize the category item if necessary
            CategoryVBox.getChildren().add(categoryItem);
        }
    }

    @FXML
    public void searchBooks(KeyEvent event) {
        String query = BookSearchField.getText();
        ObservableList<Node> contents = BookTilePane.getChildren();

        // Search for books matching the query
        List<Book> searchBookResults = library.searchBooks(query);
        //clear the tile pane. If the logged user is an admin, do not remove the first book
        if (library.isAdmin()) {
            contents.subList(1, contents.size()).clear();
        } else BookTilePane.getChildren().clear();
        displayBooks(searchBookResults);
    }

    @FXML
    public void searchUsers(KeyEvent event) {
        String query = UserSearchField.getText();
        ObservableList<Node> contents = UserTilePane.getChildren();
        if (library.isAdmin()) {
            contents.subList(1, contents.size()).clear();
        } else UserTilePane.getChildren().clear();
        // Search for users matching the query
        List<User> searchUserResults = library.searchUsers(query);
        displayUsers(searchUserResults);
    }
}

