package com.example.librarygui.controllers;

import com.example.librarygui.*;
import com.example.librarygui.interfaces.Banner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;


public class RateBookPage extends Controller {
    private Book book;
    private User user;
    @FXML
    private Text titleText;
    @FXML
    private Button saveButton;
    @FXML
    private Spinner ratingField;
    @FXML
    private TextArea commentField;

    private Rating rating;

    public RateBookPage() {
    }

    public void init() {

        //load the combo box from categories names

            titleText.setText("Rate Book");
            Loan loan = (Loan) highlightedObject;
            this.book = loan.book;
            this.user = library.loggedUser;

            rating = library.getRating(book, user);
            if (rating != null) {
                ratingField.getValueFactory().setValue(Integer.parseInt(rating.rating));
                commentField.setText(rating.comment);
            }
    }

    @FXML
    public void save (ActionEvent event) throws Exception {

        String rating = ratingField.getValue().toString();
        String review = commentField.getText();

        if (this.rating != null) {
            library.editRating(rating, review, book, user);
        }
        else {
            library.addRating(rating, review, book, user);
        }
        Main.loadFXML("user_main_page.fxml");

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

