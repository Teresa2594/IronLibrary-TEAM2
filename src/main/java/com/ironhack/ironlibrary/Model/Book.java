package com.ironhack.ironlibrary.Model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    private String isbn;

    private String title;
    private String cathegory;
    private Integer quantity;

    public Book() {
    }

    public Book(String isbn, String title, String cathegory, Integer quantity) {
        this.isbn = isbn;
        this.title = title;
        this.cathegory = cathegory;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCathegory() {
        return cathegory;
    }

    public void setCathegory(String cathegory) {
        this.cathegory = cathegory;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book: " +
                "isbn: '" + isbn + '\'' +
                ", title: '" + title + '\'' +
                ", cathegory: '" + cathegory + '\'' +
                ", quantity: " + quantity +
                '.';
    }
}
