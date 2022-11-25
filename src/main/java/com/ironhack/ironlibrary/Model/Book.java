package com.ironhack.ironlibrary.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Book {

    @Id
    private String isbn;

    private String title;
    private String category;
    private Integer quantity;


    public Book() {
    }

    public Book(String isbn, String title, String category, Integer quantity) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
                ", category: '" + category + '\'' +
                ", quantity: " + quantity +
                '.';
    }
}
