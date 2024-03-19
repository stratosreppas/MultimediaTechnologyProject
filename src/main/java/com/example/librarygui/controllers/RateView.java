package com.example.librarygui.controllers;

import com.example.librarygui.Rating;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class RateView extends Controller{

    Rating rating;
    @FXML
    private Text ratingText;
    @FXML
    private Text commentText;
    public void init() {
        rating = (Rating) highlightedObject;

        ratingText.setText(rating.rating);
        commentText.setText(rating.comment);
    }
}
