package com.example.librarygui;

public class Book {

    public String title;
    public String author;
    public String isbn;
    public String publisher;
    public String year;
    public String copies;
    public String rating;

    public Book(String title, String author, String isbn, String publisher, String year, String copies, String rating) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.year = year;
        this.copies = copies;
        this.rating = rating;
    }

}
