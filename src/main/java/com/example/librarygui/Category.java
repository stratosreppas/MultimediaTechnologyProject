package com.example.librarygui;

import com.example.librarygui.interfaces.FileIO;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String path = "medialab/categories/";
    public String name;
    public List<String> isbns;

    public Category(String name, List<String> isbns) {
        this.name = name;
        this.isbns = isbns;
    }

    public Category(String name) {
        this.name = name;
        this.isbns = new ArrayList<>();
    }

    public List<String> getIsbns() {
        return this.isbns;
    }

    public void addISBN(String ISBN) {
        this.isbns.add(ISBN);
    }

    public void saveCategory() {
        FileIO.writeMultipleStrings(path+name+".bin", this.isbns.toArray(new String[0]));
    }

    public String getBooksCategory(String isbn) {
        // Searches if the book's isbn exists in the List in this category
        // If it does, return the category name
        // If it doesn't, return null
        if (isbns.contains(isbn)) {
            return name;
        } else {
            return null;
        }
    }

    }
