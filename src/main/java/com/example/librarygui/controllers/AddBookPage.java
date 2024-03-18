package com.example.librarygui.controllers;

import com.example.librarygui.Book;
import com.example.librarygui.Category;
import com.example.librarygui.Main;
import com.example.librarygui.interfaces.Banner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class AddBookPage extends Controller {
    private Book book;
    public Text titleText;
    public Button deleteButton;
    public TextField titleField;
    public TextField authorField;
    public TextField categoryField;
    public TextField yearField;
    public TextField isbnField;
    public TextField publisherField;
    public TextField copiesField;

    public Book editBook(String title, String author, String category, String year, String isbn, String publisher, String copies, String rating) {
        return new Book(title, author, category, year, isbn, publisher, copies, rating);
    }

    @FXML
    private ComboBox<String> comboBox;

    public void init() {

        //load the combo box from categories names
        comboBox.getItems().clear();
        for(int i = 0; i < library.categories.size(); i++)
            comboBox.getItems().add(library.categories.get(i).name);

        if (highlightedObject != null){
            titleText.setText("Edit Book");

            this.book = (Book) highlightedObject;

            comboBox.setValue(book.category);

            titleField.setText(book.title);
            titleField.setPromptText(book.title);

            authorField.setText(book.author);
            authorField.setPromptText(book.author);

            yearField.setText(book.year);
            yearField.setPromptText(book.year);

            isbnField.setText(book.isbn);
            isbnField.setPromptText(book.isbn);

            publisherField.setText(book.publisher);
            publisherField.setPromptText(book.publisher);

            copiesField.setText(book.copies);
            copiesField.setPromptText(book.copies);
        }

        else {
            titleText.setText("Add Book");

            deleteButton.setDisable(true);

            comboBox.setPromptText("Category");

            titleField.setPromptText("Title");
            authorField.setPromptText("Author");
            yearField.setPromptText("Year");
            isbnField.setPromptText("ISBN");
            publisherField.setPromptText("Publisher");
            copiesField.setPromptText("Copies");
        }

        if (library.categories != null)
            for (Category category : library.categories) {
                comboBox.getItems().add(category.name);
            }
    }

        @FXML
        public void save (ActionEvent event) throws Exception {

            String title = titleField.getText();
            String author = authorField.getText();
            String year = yearField.getText();
            String isbn = isbnField.getText();
            String publisher = publisherField.getText();
            String copies = copiesField.getText();

        if(highlightedObject != null) {
            String prev_title = book.title;
            String prev_author = book.author;
            String prev_year = book.year;
            String prev_isbn = book.isbn;
            String prev_publisher = book.publisher;
            String prev_copies = book.copies;


            if (title.equals("") || author.equals("") || year.equals("") || isbn.equals("") || publisher.equals("") || copies.equals("")) {
                Banner.showErrorBanner("Error", "Please fill in all fields");
                return;
            }
            if (!title.equals(prev_title) && library.bookExists(title)) {
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
                    Banner.showInformationDialog("Success", "Book Publisher will be edited to: '" + publisher + "'");
                }

                if (!copies.equals(prev_copies)) {
                    book.copies = copies;
                    Banner.showInformationDialog("Success", "Book Copies will be edited to: '" + copies + "'");
                }

                library.editBook(prev_title, title, author, year, isbn, publisher, copies);
                Banner.showInformationDialog("Success", "Book '" + title + "' edited successfully");
            }
        }

        else{
            if (title.equals("") || author.equals("") || year.equals("") || isbn.equals("") || publisher.equals("") || copies.equals("")) {
                Banner.showErrorBanner("Error", "Please fill in all fields");
                return;
            }
            if (library.bookExists(title)) {
                Banner.showErrorBanner("Error", "Book already exists");
                return;
            }
            else {
                book = new Book(title, author, year, isbn, publisher, copies,"0", "sex");
                library.addBook(book);
                Banner.showInformationDialog("Success", "Book '" + title + "' added successfully");
            }
        }
            Main.loadFXML("admin_main_page.fxml");

        }

        public void deleteBook(ActionEvent event) throws Exception {
            if (library.removeBook(book.title)) {
                Banner.showInformationDialog("Success", "Book deleted successfully");
                Main.loadFXML("user_main_page.fxml");
            }
            else
                Banner.showErrorBanner("Error", "Book not found");
        }
    }

