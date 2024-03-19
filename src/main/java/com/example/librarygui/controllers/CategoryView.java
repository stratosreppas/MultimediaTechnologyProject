package com.example.librarygui.controllers;

import com.example.librarygui.Category;
import com.example.librarygui.Main;
import com.example.librarygui.interfaces.Banner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CategoryView extends Controller{

    @FXML
    private TextField categoryTextField;
    Category category;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;

    public void init(){
        category = (Category) highlightedObject;
        if(category != null) {
            categoryTextField.setText(category.name);
            categoryTextField.setPromptText(category.name);
        }
        else {
            deleteButton.setVisible(false);
            categoryTextField.setPromptText("Add category");
        }
    }

    public void deleteCategory() throws Exception {
        if (Banner.showConfirmationDialog(
                "Delete Category",
                "Are you sure you want to delete this category? " +
                        "This action will also remove all the books in this category."
        )) {
            System.out.println("delete category");

            if (library.removeCategory(category)) {
                Banner.showInformationDialog("Success", "Category deleted successfully");
                Main.loadFXML("admin_main_page.fxml");
            } else
                Banner.showErrorBanner("Error", "Book not found");
        }
    }

    public void editCategory() throws Exception {
        String newCategory = categoryTextField.getText();
        if (newCategory.equals("")) {
            deleteCategory();
            return;
        }

        if (library.categoryExists(newCategory)) {
            Banner.showErrorBanner("Error", "Category already exists");
            return;
        }

            if(category == null) {
                if(Banner.showConfirmationDialog("Add Category", "Are you sure you want to add this category?")) {
                    library.addCategory(newCategory);
                    Banner.showInformationDialog("Success", "Category added successfully");
                    Main.loadFXML("admin_main_page.fxml");
                }
            }
            else if (Banner.showConfirmationDialog("Edit Category", "Are you sure you want to edit this category?")
                    && library.editCategory(category.name, newCategory)) {
                Banner.showInformationDialog("Success", "Category edited successfully");
                Main.loadFXML("admin_main_page.fxml");
            } else
                Banner.showErrorBanner("Error", "Category not found");
    }
}
