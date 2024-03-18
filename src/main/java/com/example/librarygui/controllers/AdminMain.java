package com.example.librarygui.controllers;

import com.example.librarygui.Book;
import com.example.librarygui.Loan;
import com.example.librarygui.Main;
import com.example.librarygui.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

import java.util.List;

public class AdminMain extends MainPageController{

    public void init() {
        UserTilePane.getChildren().subList(1, UserTilePane.getChildren().size()).clear();
        BookTilePane.getChildren().subList(1, BookTilePane.getChildren().size()).clear();
        LoanVBox.getChildren().clear();

        List<User> users = library.users; // Assuming you have a method to retrieve users
        List<Book> books = library.books; // Assuming you have a method to retrieve books
        List<Loan> loans = library.loans; // Assuming you have a method to retrieve loans
        displayBooks(books);
        displayUsers(users);
        displayLoans(loans);
    }

    // Method to retrieve books, replace with your implementation
    private List<Book> getBooks() {
        // Dummy implementation for demonstration purposes
        return List.of(
                new Book("Title 1", "Author 1", "Category 1", "Year 1", "ISBN 1", "Publisher 1", "Copies 1", "Rating 1"),
                new Book("Title 2", "Author 2", "Category 2", "Year 2", "ISBN 2", "Publisher 2", "Copies 2", "Rating 2"),
                new Book("Title 3", "Author 3", "Category 3", "Year 3", "ISBN 3", "Publisher 3", "Copies 3", "Rating 3"),
                new Book("Title 4", "Author 4", "Category 4", "Year 4", "ISBN 4", "Publisher 4", "Copies 4", "Rating 4"),
                new Book("Title 5", "Author 5", "Category 5", "Year 5", "ISBN 5", "Publisher 5", "Copies 5", "Rating 5"),
                new Book("Title 6", "Author 6", "Category 6", "Year 6", "ISBN 6", "Publisher 6", "Copies 6", "Rating 6"),
                new Book("Title 7", "Author 7", "Category 7", "Year 7", "ISBN 7", "Publisher 7", "Copies 7", "Rating 7"),
                new Book("Title 8", "Author 8", "Category 8", "Year 8", "ISBN 8", "Publisher 8", "Copies 8", "Rating 8"),
                new Book("Title 9", "Author 9", "Category 9", "Year 9", "ISBN 9", "Publisher 9", "Copies 9", "Rating 9"),
                new Book("Title 10", "Author 10", "Category 10", "Year 10", "ISBN 10", "Publisher 10", "Copies 10", "Rating 10")
        );
    }

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
    public void addCategory() {
        Main.loadFXML("admin_add_category_page.fxml", null);
    }
}
