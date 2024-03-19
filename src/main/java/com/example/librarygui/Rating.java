package com.example.librarygui;

public class Rating {

    public String rating;
    public String comment;
    public Book book;
    public User user;
    public String id;

    public Rating(String id, String rating, String comment, Book book, User user) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.book = book;
        this.user = user;
    }
}
