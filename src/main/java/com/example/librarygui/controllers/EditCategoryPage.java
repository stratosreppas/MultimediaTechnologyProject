package com.example.librarygui.controllers;

import com.example.librarygui.interfaces.Banner;
import com.example.librarygui.Category;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;

public class EditCategoryPage {
    public List<Category> categories;
    public TextField categoryField;
    public ComboBox<String> comboBox;

        public void initialize() {
            // Populate the ComboBox with all the names in the categories list
            for (Category category : categories) {
                comboBox.getItems().add(category.name);
            }
        }

        public void addCategory(ActionEvent event) throws Exception {
            String category_name = categoryField.getText();
            if (category_name.equals("")) {
                Banner.showErrorBanner("Error", "Please fill in the category_name field");
            } else {
                for (Category category : categories) {
                    if (category.name.equals(category_name)) {
                        Banner.showErrorBanner("Error", "Category already exists");
                        return;
                    }
                }
                this.categories.add(new Category(category_name, null));
                Banner.showInformationDialog("Success", "Category edited successfully");
            }
        }
    public void editCategory(ActionEvent event) throws Exception {
        String category_name = categoryField.getText();
        String category_old_name = categoryField.getPromptText();
        if (category_name.equals("")) {
            Banner.showErrorBanner("Error", "Please fill in the category_name field");
        } else {
            for (Category category : categories) {
                if (category.name.equals(category_name)) {
                    Banner.showErrorBanner("Error", "Category already exists");
                    return;
                } else if (category.name.equals(category_old_name)) {
                    category.name = category_name;
                    Banner.showInformationDialog("Success", "Category edited successfully");
                    return;
                }
            }
            Banner.showErrorBanner("Error", "Error editing category");
        }
    }

    public void removeCategory(ActionEvent event) throws Exception {
        String category_name = categoryField.getText();

        for (Category category : categories) {
            if (category.name.equals(category_name)) {
                categories.remove(category);
                Banner.showInformationDialog("Success", "Category removed successfully");
                return;
            }
        }
        Banner.showErrorBanner("Error", "Error removing category");
    }
}
