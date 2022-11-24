package com.ironhack.ironlibrary.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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

    public Issue(Integer issueId, Student student, Book book) {
        this.issueId = issueId;
        setIssueDate();
        setReturnDate();
        setStudent(student);
        setBook(book);
    }

    public Integer getIssueId() {
        return issueId;
    }



    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate() {
        LocalDate date = LocalDate.now();
        issueDate = date.toString();
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate()
    {
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
