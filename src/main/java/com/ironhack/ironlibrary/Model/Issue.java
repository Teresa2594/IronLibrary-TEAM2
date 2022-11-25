package com.ironhack.ironlibrary.Model;

import org.hibernate.type.LocalDateType;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer issueId;

    private String issueDate;
    private String returnDate;


    @OneToOne
    @JoinColumn(name = "usn_student")
    private Student student;

    @OneToOne
    @JoinColumn(name = "issue_book")
    private Book book;

    public Issue() {
    }

    public Issue(Student student, Book book) {
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
        this.issueDate = LocalDate.now().toString();
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate() {
        this.returnDate = LocalDate.now().plusDays(7).toString();
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

    @Override
    public String toString() {
        return "Issue{" +
                "issueId=" + issueId +
                ", issueDate='" + issueDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", student=" + student +
                ", book=" + book +
                '}';
    }
}
