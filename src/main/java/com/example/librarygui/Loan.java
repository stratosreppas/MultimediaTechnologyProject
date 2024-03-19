package com.example.librarygui;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Loan {
    private String id;
    private String loanDate;
    private String returnDate;

    private Book book;

    private User user;

    public Loan(String id, Book book, User user) {
        this.id = id;
        this.loanDate = getCurrentDate();
        this.returnDate = calcReturnDate();
        this.book = book;
        this.user = user;
    }

    public Loan(String id, String loanDate, Book book, User user) {
        this.id = id;
        this.loanDate = loanDate;
        this.returnDate = calcReturnDate();
        this.book = book;
        this.user = user;
    }

    private String calcReturnDate() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM");
            String loanDateWithYear = loanDate + " " + new SimpleDateFormat("yyyy").format(new Date());
            Date loanDateObj = new SimpleDateFormat("dd MMM yyyy").parse(loanDateWithYear);
            // Create a Calendar instance and set it to the loan date
            Calendar returnDateCal = Calendar.getInstance();
            returnDateCal.setTime(loanDateObj);

            // Add 5 days to the loan date
            returnDateCal.add(Calendar.DAY_OF_MONTH, 5);

            // Format the return date
            return dateFormat.format(returnDateCal.getTime());
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM");
        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));
        return dateFormat.format(currentDate);
    }

    public String getId() {
        return this.id;
    }

    public String getLoanDate() {
        return this.loanDate;
    }

    public String getReturnDate() {
        return this.returnDate;
    }

    public Book getBook() {
        return this.book;
    }

    public User getUser() {
        return this.user;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
