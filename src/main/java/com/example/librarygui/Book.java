package com.example.librarygui;

public class Book {

    public String title;
    public String author;
    public String isbn;
    public String publisher;
    public String year;
    public String copies;

    public Book(String title, String author, String isbn, String publisher, String year, String copies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.year = year;
        this.copies = copies;
    }

    public boolean isNumeric() {
        try {
            Integer.parseInt(copies);
            Integer.parseInt(year);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void removeCopy() {
        if (isNumeric()) {
            int copies = Integer.parseInt(this.copies);
            if (copies > 0) {
                copies--;
                this.copies = Integer.toString(copies);
            }
        }
    }

    public void addCopy() {
        if (isNumeric()) {
            int copies = Integer.parseInt(this.copies);
            copies++;
            this.copies = Integer.toString(copies);
        }
    }

}
