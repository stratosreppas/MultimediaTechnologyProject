package com.example.librarygui.controllers;

import com.example.librarygui.Book;
import com.example.librarygui.Category;
import com.example.librarygui.Main;
import com.example.librarygui.interfaces.Banner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class EditLoanPage extends Controller {
    private Book book;
    public TextField titleField;
    public TextField authorField;
    public TextField categoryField;
    public TextField yearField;
    public TextField isbnField;
    public TextField publisherField;
    public TextField copiesField;

    public Book editBook(String title, String author, String category, String year, String isbn, String publisher, String copies, String rating) {
        return new Book(title, author, year, isbn, publisher, copies);
    }

    @FXML
    private ComboBox<String> comboBox;

    public void init() {
    }

        @FXML
        public void editBook (ActionEvent event) throws Exception {

            String title = titleField.getText();
            String prev_title = book.title;
            String author = authorField.getText();
            String prev_author = book.author;
            String year = yearField.getText();
            String prev_year = book.year;
            String isbn = isbnField.getText();
            String prev_isbn = book.isbn;
            String publisher = publisherField.getText();
            String prev_publisher = book.publisher;
            String copies = copiesField.getText();
            String prev_copies = book.copies;

            if (title.equals("") || author.equals("") || year.equals("") || isbn.equals("") || publisher.equals("") || copies.equals("")) {
                Banner.showErrorBanner("Error", "Please fill in all fields");
                return;
            }
            if(!title.equals(prev_title) && library.bookExists(title)){
                Banner.showErrorBanner("Error", "Book already exists");
                return;
            }

            if (library.bookExists(title)) {
                Banner.showErrorBanner("Error", "Book already exists");
                return;
            }

            else {
                if (!title.equals(prev_title)) {
                    book.title = title;
                    Banner.showInformationDialog("Success", "Book Title will be edited to: '" + title + "'");
                }

                if (!author.equals(prev_author)) {
                    book.author = author;
                    Banner.showInformationDialog("Success", "Book Author will be edited to: '" + author + "'");
                }

                if (!year.equals(prev_year)) {
                    book.year = year;
                    Banner.showInformationDialog("Success", "Book Year will be edited to: '" + year + "'");
                }

                if (!isbn.equals(prev_isbn)) {
                    book.isbn = isbn;
                    Banner.showInformationDialog("Success", "Book ISBN will be edited to: '" + isbn + "'");
                }

                if (!publisher.equals(prev_publisher)) {
                    book.publisher = publisher;
                    Banner.showInformationDialog("Success", "Book Publisher will be edited to: " + publisher + "'");
                }

                if (!copies.equals(prev_copies)) {
                    book.copies = copies;
                    Banner.showInformationDialog("Success", "Book Copies will be edited to: " + copies + "'");
                }

                library.editBook(prev_isbn, new Book(title, author, isbn, publisher, year, copies));
                Main.loadFXML("user_main_page.fxml");
            }
        }

        public void deleteBook(ActionEvent event) throws Exception {
            if (library.removeBook(book)) {
                Banner.showInformationDialog("Success", "Book deleted successfully");
                Main.loadFXML("admin_main_page.fxml");
            }
            else
                Banner.showErrorBanner("Error", "Book not found");
        }
    }

