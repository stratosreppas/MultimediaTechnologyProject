package com.example.librarygui;
import com.example.librarygui.interfaces.Banner;
import com.example.librarygui.interfaces.FileIO;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class User {

    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String adt;
    public String email;

    public User(String username, String password, String firstName, String lastName, String adt, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adt = adt;
        this.email = email;
    }

    public void login(Scene scene) {

        TextField usernameField = (TextField) scene.lookup("#usernameField");
        PasswordField passwordField = (PasswordField) scene.lookup("#passwordField");

        String path = "medialab/users/";
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("") || password.equals("")) {
            Banner.showErrorBanner("Error", "Please fill in all fields");
        } else if (!FileIO.exists_file(path + username + ".bin")) {
            Banner.showErrorBanner("Error", "Username does not exist");
        } else {
            String[] user = FileIO.readMultipleStrings(path + username + ".bin");
            if (user[1].equals(password)) {
                Banner.showInformationDialog("Success", "Logged in successfully");
            } else {
                Banner.showErrorBanner("Error", "Incorrect password");
            }
        }
    }
}
