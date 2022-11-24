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
        this.issueDate =  LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate()  {

        LocalDate date  = new LocalDateType().fromString(this.issueDate).plusDays(7);

        this.returnDate = date.toString();
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
