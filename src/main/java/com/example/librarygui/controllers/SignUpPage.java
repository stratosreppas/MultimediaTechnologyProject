package com.example.librarygui.controllers;

import com.example.librarygui.Main;
import com.example.librarygui.User;
import com.example.librarygui.interfaces.Banner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;


public class SignUpPage extends Controller{
    private User user;
    public Text titleText;
    public Button deleteButton;
    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField confirmPasswordField;
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField adtField;
    public TextField emailField;


    public void init() {
        if (highlightedObject != null){
            titleText.setText("Edit User");

            this.user = (User) highlightedObject;

            usernameField.setText(user.getUsername());
            usernameField.setPromptText(user.getUsername());

            passwordField.setText(user.getPassword());
            passwordField.setPromptText(user.getPassword());

            confirmPasswordField.setText(user.getPassword());
            confirmPasswordField.setPromptText(user.getPassword());

            firstNameField.setText(user.getFirstName());
            firstNameField.setPromptText(user.getFirstName());

            lastNameField.setText(user.getLastName());
            lastNameField.setPromptText(user.getLastName());

            adtField.setText(user.getAdt());
            adtField.setPromptText(user.getAdt());

            emailField.setText(user.getEmail());
            emailField.setPromptText(user.getEmail());
        }
        else {
            titleText.setText("Sign Up");
            deleteButton.setVisible(false);

            usernameField.setPromptText("Username");
            passwordField.setPromptText("Password");
            confirmPasswordField.setPromptText("Confirm Password");
            firstNameField.setPromptText("First Name");
            lastNameField.setPromptText("Last Name");
            adtField.setPromptText("ADT");
            emailField.setPromptText("Email");
        }


    }
        @FXML
        public void save(ActionEvent event) throws Exception {

            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String adt = adtField.getText();
            String email = emailField.getText();

            if (username.equals("") || password.equals("") || confirmPassword.equals("") || firstName.equals("") || lastName.equals("") || adt.equals("") || email.equals("")) {
                Banner.showErrorBanner("Error", "Please fill in all fields");
                return;
            }

            if (!password.equals(confirmPassword)) {
                Banner.showErrorBanner("Error", "Passwords do not match");
                return;
            }

            if (library.userExists(username) && !username.equals(user.getUsername())){
                Banner.showErrorBanner("Error", "Username already exists");
                return;
            }

            if(library.userExists(email) && !email.equals(user.getEmail())) {
                Banner.showErrorBanner("Error", "User with that email already exists");
                return;
            }

            if(library.userExists(adt) && !adt.equals(user.getAdt())) {
                Banner.showErrorBanner("Error", "User with that ADT already exists");
                return;
            }

            if (highlightedObject != null) {
                String prev_username = user.getUsername();
                String prev_password = user.getPassword();
                String prev_firstName = user.getFirstName();
                String prev_lastName = user.getLastName();
                String prev_adt = user.getAdt();
                String prev_email = user.getEmail();

                if (!username.equals(prev_username)) {
                    Banner.showInformationDialog("Success", "Username will be edited to: '" + username + "'");
                    user.setUsername(username);
                }

                if (!password.equals(prev_password)) {
                    Banner.showInformationDialog("Success", "Password will be edited to: '" + password + "'");
                    user.setPassword(password);
                }

                if (!firstName.equals(prev_firstName)) {
                    Banner.showInformationDialog("Success", "First Name will be edited to: '" + firstName + "'");
                    user.setFirstName(firstName);
                }

                if (!lastName.equals(prev_lastName)) {
                    Banner.showInformationDialog("Success", "Last Name will be edited to: '" + lastName + "'");
                    user.setLastName(lastName);
                }

                if (!adt.equals(prev_adt)) {
                    Banner.showInformationDialog("Success", "ADT will be edited to: '" + adt + "'");
                    user.setAdt(adt);
                }

                if (!email.equals(prev_email)) {
                    Banner.showInformationDialog("Success", "Email will be edited to: '" + email + "'");
                    user.setEmail(email);
                }

                library.editUser(prev_username, username, password, firstName, lastName, adt, email);
                Banner.showInformationDialog("Success", "User updated successfully");
                Main.loadFXML("admin_main_page.fxml");
            }
            else {
                library.addUser(new User(username, password, firstName, lastName, adt, email));

                Banner.showInformationDialog("Success", "User created successfully");
                Main.loadFXML("login_page.fxml");
            }
        }

        @FXML
        public void delete(ActionEvent event) throws Exception {
            if (Banner.showConfirmationDialog("Delete User", "Are you sure you want to delete this user?")) {
                library.removeUser(user);
                Banner.showInformationDialog("Success", "User deleted successfully");
                Main.loadFXML("admin_main_page.fxml");
            }
        }

}

