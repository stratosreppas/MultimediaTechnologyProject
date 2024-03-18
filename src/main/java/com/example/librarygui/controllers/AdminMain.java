package com.example.librarygui.controllers;

import com.example.librarygui.*;
import com.example.librarygui.interfaces.Banner;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.List;

public class AdminMain extends MainPageController{

    public void init() {
        UserTilePane.getChildren().subList(1, UserTilePane.getChildren().size()).clear();
        BookTilePane.getChildren().subList(1, BookTilePane.getChildren().size()).clear();
        LoanVBox.getChildren().clear();
        CategoryVBox.getChildren().clear();

        List<User> users = library.users; // Assuming you have a method to retrieve users
        List<Book> books = library.books; // Assuming you have a method to retrieve books
        List<Loan> loans = library.loans; // Assuming you have a method to retrieve loans
        List<Category> categories = library.categories; // Assuming you have a method to retrieve categories
        List<Category> newCategory = new ArrayList<>();
        newCategory.addFirst(null);

        displayBooks(books);
        displayUsers(users);
        displayLoans(loans);
        displayCategories(newCategory);
        displayCategories(categories);
    }

    // Method to retrieve books, replace with your implementation

    private List<User> getUsers() {
        // Dummy implementation for demonstration purposes
        return List.of(
                new User("Username 1", "Password 1", "First Name 1", "Last Name 1", "adt 1", "email 1"),
                new User("Username 2", "Password 2", "First Name 2", "Last Name 2", "adt 2", "email 2"),
                new User("Username 3", "Password 3", "First Name 3", "Last Name 3", "adt 3", "email 3"),
                new User("Username 4", "Password 4", "First Name 4", "Last Name 4", "adt 4", "email 4")
        );
    }

    public void addBook() {
        Main.loadFXML("admin_add_book_page.fxml", null);
    }
}
