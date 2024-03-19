package com.example.librarygui.controllers;

import com.example.librarygui.*;
import com.example.librarygui.interfaces.Banner;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.List;

public class AdminMain extends MainPageController{
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private Button checkButton;

    public void init() {

        categoryComboBox.getItems().clear();
        for(int i = 0; i < library.categories.size(); i++)
            categoryComboBox.getItems().add(library.categories.get(i).getName());


        UserTilePane.getChildren().clear();
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
    public void addBook() {
        Main.loadFXML("admin_add_book_page.fxml", null);
    }

    @FXML
    public void viewCategorysBooks(ActionEvent ActionEvent) {
        String category = categoryComboBox.getValue();
        if (category != null) {
            BookTilePane.getChildren().subList(1, BookTilePane.getChildren().size()).clear();
            List<Book> books = library.getBooksByCategory(category);
            displayBooks(books);
        }
    }

}
