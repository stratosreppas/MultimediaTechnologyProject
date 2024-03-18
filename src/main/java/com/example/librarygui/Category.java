package com.example.librarygui;

import com.example.librarygui.interfaces.FileIO;

import java.util.List;

public class Category {

    private String path = "medialab/categories/";
    public String name;
    public List<String> ISBNs;

    public Category(String name, List<String> ISBNs) {
        this.name = name;
        this.ISBNs = ISBNs;
    }

    public List<String> getISBNs() {
        return this.ISBNs;
    }

    public void addISBN(String ISBN) {
        this.ISBNs.add(ISBN);
    }

    public void saveCategory() {
        FileIO.writeMultipleStrings(path+name+".bin", this.ISBNs.toArray(new String[0]));
    }

    public String getBooksCategory(String isbn) {
        // Searches if the book's isbn exists in the List in this category
        // If it does, return the category name
        // If it doesn't, return null
        if (ISBNs.contains(isbn)) {
            return name;
        } else {
            return null;
        }
    }

        public void removeCategory(String category) {

        }

    }
