package com.example.librarygui;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String year;
    private String copies;

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

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public String getYear() {
        return this.year;
    }

    public String getCopies() {
        return this.copies;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCopies(String copies) {
        this.copies = copies;
    }

}
