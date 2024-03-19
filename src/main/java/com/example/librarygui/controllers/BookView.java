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
    public Text ratingText;
    public Text isbnText;
    public Text ratingCountText;
    @FXML
    Button lendButton;


    public void init() {
        book = (Book) highlightedObject;

        titleText.setText(book.getTitle());
        authorText.setText(book.getAuthor());
        ratingText.setText(Double.toString(library.getBookRatingAverage(book)));
        isbnText.setText(book.getIsbn());
        ratingCountText.setText("By "+library.getBookRatingCount(book)+" users");
    }

    public void addBook() {
        if(library.isAdmin())
            Main.loadFXML("admin_add_book_page.fxml", book);
    }

    public void lendBook() {
        if (Banner.showConfirmationDialog("Lend Book", "Are you sure you want to lend this book?") && library.loggedUser != null) {
            if(library.getUsersLoans(library.loggedUser.getUsername()).size() >= 2) {
                Banner.showErrorBanner("Error", "You can't lend more than 2 books");
                return;
            }
            if(Integer.parseInt(book.getCopies()) <= 0) {
                Banner.showErrorBanner("Error", "There are no copies of this book available");
                return;
            }
            library.addLoan(new Loan(library.getNewLoanId(), book, library.loggedUser));
            book.removeCopy();
            library.editBook(book.getIsbn(), book);
            Banner.showInformationDialog("Success", "Book lent successfully");
            Main.loadFXML("user_main_page.fxml");
        }
        else
            Banner.showErrorBanner("Error", "Couldn't lend the book");
    }

    public void presentBook() {
        if(library.loggedUser!=null)
            Main.loadFXML("present_book_page.fxml", book);
    }
}
