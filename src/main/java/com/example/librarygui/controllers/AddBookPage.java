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
    @FXML
    private ComboBox<String> comboBox;
    public TextField yearField;
    public TextField isbnField;
    public TextField publisherField;
    public TextField copiesField;

    public void init() {

        //load the combo box from categories names
        comboBox.getItems().clear();
        for(int i = 0; i < library.categories.size(); i++)
            comboBox.getItems().add(library.categories.get(i).getName());

        if (highlightedObject != null){
            titleText.setText("Edit Book");

            this.book = (Book) highlightedObject;

            comboBox.setValue(library.getBooksCategory(book));

            titleField.setText(book.getTitle());
            titleField.setPromptText(book.getTitle());

            authorField.setText(book.getAuthor());
            authorField.setPromptText(book.getAuthor());

            yearField.setText(book.getYear());
            yearField.setPromptText(book.getYear());

            isbnField.setText(book.getIsbn());
            isbnField.setPromptText(book.getIsbn());

            publisherField.setText(book.getPublisher());
            publisherField.setPromptText(book.getPublisher());

            copiesField.setText(book.getCopies());
            copiesField.setPromptText(book.getCopies());
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
    }

        @FXML
        public void save (ActionEvent event) throws Exception {

            String title = titleField.getText();
            String author = authorField.getText();
            String year = yearField.getText();
            String isbn = isbnField.getText();
            String publisher = publisherField.getText();
            String copies = copiesField.getText();
            String category = comboBox.getValue();

            if (title.equals("") || author.equals("") || year.equals("") || isbn.equals("") || publisher.equals("") || copies.equals("")) {
                Banner.showErrorBanner("Error", "Please fill in all fields");
                return;
            }

        if(highlightedObject != null) {
            String prev_title = book.getTitle();
            String prev_author = book.getAuthor();
            String prev_year = book.getYear();
            String prev_isbn = book.getIsbn();
            String prev_publisher = book.getPublisher();
            String prev_copies = book.getCopies();
            String prev_category = library.getBooksCategory(book);


            if (!isbn.equals(prev_isbn) && library.bookExists(isbn)) {
                Banner.showErrorBanner("Error", "Book already exists");
                return;
            }
            else {
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setYear(year);
                    book.setIsbn(isbn);
                    book.setPublisher(publisher);
                    book.setCopies(copies);

                    library.removeBookFromCategory(prev_category, prev_isbn);
                    library.addBookToCategory(category, isbn);
                }
                Book new_book = new Book(title, author, isbn, publisher, year, copies);
                if(!new_book.isNumeric()){
                    Banner.showErrorBanner("Error", "Copies and Year must be a number");
                    return;
                }
                library.editBook(prev_isbn, new_book);
                Banner.showInformationDialog("Success", "Book '" + title + "' edited successfully");
            }

        else {
            if (library.bookExists(title)) {
                Banner.showErrorBanner("Error", "Book already exists");
                return;
            }
            else {
                book = new Book(title, author, isbn, publisher, year, copies);
                if(!book.isNumeric()){
                    Banner.showErrorBanner("Error", "Copies and Year must be a number");
                    return;
                }
                library.addBook(book);
                library.addBookToCategory(category, isbn);
                Banner.showInformationDialog("Success", "Book '" + title + "' added successfully");
            }
        }
            Main.loadFXML("admin_main_page.fxml");
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

