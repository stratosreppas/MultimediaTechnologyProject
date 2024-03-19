package com.example.librarygui.controllers;

import com.example.librarygui.Book;
import com.example.librarygui.Main;
import com.example.librarygui.Rating;
import com.example.librarygui.User;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PresentBookPage extends Controller{

    @FXML
    private Text titleText;
    @FXML
    private Text authorText;
    @FXML
    private Text isbnText;
    @FXML
    private Text publisherText;
    @FXML
    private Text yearText;
    @FXML
    private Text copiesText;
    @FXML
    private Text categoryText;
    @FXML
    private Text ratingText;
    @FXML
    private VBox ratingVBox;

    public void init() {
        Book book = (Book) highlightedObject;
        titleText.setText(book.getTitle());
        authorText.setText(book.getAuthor());
        isbnText.setText(book.getIsbn());
        publisherText.setText(book.getPublisher());
        categoryText.setText(library.getBooksCategory(book));
        yearText.setText(book.getYear());
        copiesText.setText(book.getCopies());
        ratingText.setText("Rating: "+ Double.toString(library.getBookRatingAverage(book))+ " based on "+ Integer.toString(library.getBookRatingCount(book)) + " ratings" );

        //print the ratings and comments
        ratingVBox.getChildren().clear();
        for (User user : library.users){
            Rating rating = library.getRating(book, user);
            if(rating != null){
                Parent ratingItem = Main.loadItem("rating_view.fxml", rating);
            // Customize the category item if necessary
            ratingVBox.getChildren().add(ratingItem);
            }
        }

    }

}
