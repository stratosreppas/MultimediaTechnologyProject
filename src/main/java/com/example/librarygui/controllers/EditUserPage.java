//package com.example.librarygui.controllers;
//
//import com.example.librarygui.User;
//import com.example.librarygui.Main;
//import com.example.librarygui.interfaces.Banner;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//
//
//public class EditUserPage extends Controller {
//    private User user;
//    public TextField usernameField;
//    public TextField firstnameField;
//    public TextField lastnameField;
//
//    public TextField emailField;
//    public TextField adtField;
//
//public void init() {
//        this.user = (User) highlightedObject;
//
//        usernameField.setText(user.username);
//        usernameField.setPromptText(user.username);
//
//        firstnameField.setText(user.firstName);
//        firstnameField.setPromptText(user.firstName);
//
//        lastnameField.setText(user.lastName);
//        lastnameField.setPromptText(user.lastName);
//
//        emailField.setText(user.email);
//        emailField.setPromptText(user.email);
//
//        adtField.setText(user.adt);
//        adtField.setPromptText(user.adt);
//    }
//
//    @FXML
//    public void editUser (ActionEvent event) throws Exception {
//
//        String username = usernameField.getText();
//        String prev_username = user.username;
//        String firstname = firstnameField.getText();
//        String prev_firstname = user.firstName;
//        String lastname = lastnameField.getText();
//        String prev_lastname = user.lastName;
//        String email = emailField.getText();
//        String prev_email = user.email;
//        String adt = adtField.getText();
//        String prev_adt = user.adt;
//
//
//        if (username.equals("") || firstname.equals("") || lastname.equals("") || email.equals("") || adt.equals("")) {
//            Banner.showErrorBanner("Error", "Please fill in all fields");
//            return;
//        }
//
//        if (library.userExists(username) && !username.equals(prev_username)) {
//            Banner.showErrorBanner("Error", "User already exists");
//            return;
//        }
//
//        else {
//            if (!username.equals(prev_username)) {
//                user.username = username;
//                Banner.showInformationDialog("Success", "Username will be edited to: '" + username + "'");
//            }
//
//            if (!firstname.equals(prev_firstname)) {
//                user.firstName = firstname;
//                Banner.showInformationDialog("Success", "First Name will be edited to: '" + firstname + "'");
//            }
//
//            if (!lastname.equals(prev_lastname)) {
//                user.lastName = lastname;
//                Banner.showInformationDialog("Success", "Last Name will be edited to: '" + lastname + "'");
//            }
//
//            if (!email.equals(prev_email)) {
//                user.email = email;
//                Banner.showInformationDialog("Success", "Email will be edited to: '" + email + "'");
//            }
//
//            if (!adt.equals(prev_adt)) {
//                user.adt = adt;
//                Banner.showInformationDialog("Success", "ADT will be edited to: '" + adt + "'");
//            }
//
//            library.editUser(prev_username, username, user.password, firstname, lastname, adt, email);
//            Main.loadFXML("admin_main_page.fxml");
//        }
//
//    }
//
//    public void deleteUser(ActionEvent event) throws Exception {
//        if (library.removeUser(user)) {
//            Banner.showInformationDialog("Success", "User deleted successfully");
//            Main.loadFXML("admin_main_page.fxml");
//        }
//        else
//            Banner.showErrorBanner("Error", "User not found");
//    }
//}
//
