package com.ironhack.ironlibrary.Model;

import javax.persistence.*;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    private String name;
    private String email;

   @OneToOne
   @JoinColumn(name = "author_book")
   private Book authorBook;

    public Author() {
    }

    public Author(Integer authorId, String name, String email, Book authorBook) {
        this.authorId = authorId;
        this.name = name;
        this.email = email;
        this.authorBook = authorBook;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Book getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(Book authorBook) {
        this.authorBook = authorBook;
    }
}
