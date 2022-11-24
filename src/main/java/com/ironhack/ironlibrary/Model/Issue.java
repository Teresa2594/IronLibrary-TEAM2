package com.ironhack.ironlibrary.Model;

import javax.persistence.*;

@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer issueId;

    private String issueDate;
    private String returnDate;


    @OneToOne
    @JoinColumn(name="usn_student")
    private Student student;

    @OneToOne
    @JoinColumn(name="issue_book")
    private Book book;

    public Issue() {
    }

    public Issue(Integer issueId, String issueDate, String returnDate, Student student, Book book) {
        this.issueId = issueId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.student = student;
        this.book = book;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
