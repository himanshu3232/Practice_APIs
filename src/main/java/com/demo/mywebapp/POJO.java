package com.demo.mywebapp;

public class POJO {
    private String bookName;
    private String author;
    private int copies;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
    @Override
    public String toString(){
        return "Name: " + bookName + " Author: " + author + " Sales: " + copies;
    }
}
