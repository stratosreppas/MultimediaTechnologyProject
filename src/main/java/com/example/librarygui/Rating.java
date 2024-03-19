package com.example.librarygui;

public class Rating {

    private String rating;
    private String comment;
    private Book book;
    private User user;
    private String id;

    public Rating(String id, String rating, String comment, Book book, User user) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.book = book;
        this.user = user;
    }

    public String getRating() {
        return this.rating;
    }

    public String getComment() {
        return this.comment;
    }

    public Book getBook() {
        return this.book;
    }

    public User getUser() {
        return this.user;
    }

    public String getId() {
        return this.id;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(String id) {
        this.id = id;
    }
}
