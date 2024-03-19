package com.example.librarygui.controllers;

import com.example.librarygui.Loan;
import com.example.librarygui.Main;
import com.example.librarygui.interfaces.Banner;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LoanView extends Controller{

    @FXML
    private Label titleLabel;
    @FXML
    private Label isbnLabel;
    @FXML
    private Text loanDateText;
    @FXML
    private Text returnDateText;
    @FXML
    private VBox rateVBox;


    public void init() {
        Loan loan = (Loan) highlightedObject;
        titleLabel.setText(loan.book.title);
        isbnLabel.setText(loan.book.isbn);
        loanDateText.setText(loan.loanDate);
        returnDateText.setText(loan.returnDate);
        if(library.isAdmin()) {
            rateVBox.setVisible(false);
        }
    }

    public void rateBook() {
        Main.loadFXML("rate_book_page.fxml", highlightedObject);
    }

    public void returnBook() {
        if(library.isAdmin() && Banner.showConfirmationDialog("Return Book", "Are you sure you want to return this book?") ) {
            library.removeLoan((Loan) highlightedObject);
            Banner.showInformationDialog("Success", "Book returned successfully.\nPlease refresh the page to see the changes");
        }
    }

}
