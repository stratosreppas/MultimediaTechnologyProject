package com.example.librarygui.controllers;

import com.example.librarygui.Main;
import com.example.librarygui.User;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

public class UserView extends Controller{

    public User user;
    public Text usernameText;
    public Text firstnameText;
    public Text lastnameText;
    public Text emailText;
    public Text adtText;


    public void init() {
        user = (User) highlightedObject;

        usernameText.setText(user.getUsername());
        firstnameText.setText(user.getFirstName());
        lastnameText.setText(user.getLastName());
        emailText.setText(user.getEmail());
        adtText.setText(user.getAdt());
    }

    public void addUser() {
        Main.loadFXML("signup_page.fxml", user);
    }
}
