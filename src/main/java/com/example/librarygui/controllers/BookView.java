package com.example.librarygui.controllers;

import com.example.librarygui.Book;
import com.example.librarygui.Loan;
import com.example.librarygui.Main;
import com.example.librarygui.interfaces.Banner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class BookView extends Controller{

    public Book book;
    public Text titleText;
    public Text authorText;
    public Text categoryText;
    public Text yearText;
    public Text isbnText;
    public Text publisherText;
    public Text copiesText;
    @FXML
    Button lendButton;


    public void init() {
        book = (Book) highlightedObject;

        titleText.setText(book.title);
        authorText.setText(book.author);
        yearText.setText(book.year);
        isbnText.setText(book.isbn);
        publisherText.setText(book.publisher);
        categoryText.setText(library.getBooksCategory(book));
    }

    public void addBook() {
        if(library.isAdmin())
            Main.loadFXML("admin_add_book_page.fxml", book);
    }

    public void lendBook() {
        if (Banner.showConfirmationDialog("Lend Book", "Are you sure you want to lend this book?") && library.loggedUser != null) {
            library.addLoan(new Loan(library.getNewLoanId(), book, library.loggedUser));
            Banner.showInformationDialog("Success", "Book lent successfully");
        }
        else
            Banner.showErrorBanner("Error", "Couldn't lend the book");
    }
}
