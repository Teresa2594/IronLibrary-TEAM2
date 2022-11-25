package com.ironhack.ironlibrary.Model;

import com.ironhack.ironlibrary.Repository.AuthorRepository;
import com.ironhack.ironlibrary.Repository.BookRepository;
import com.ironhack.ironlibrary.Repository.IssueRepository;
import com.ironhack.ironlibrary.Model.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UtilsTest {


   Utils utils = new Utils();
       Book book;
    Author author;
    Student student;
    Issue issue;

    @BeforeEach
    public void setup() {
        book = new Book("book1", "Libro de prueba", "Novela", 5);
        utils.bookRepository.save(book);
        System.out.println("entro");
        author = new Author("Autor de prueba","autor@autor.com",book);
        utils.authorRepository.save(author);
        student = new Student("St1","Estudiante de prueba");
        utils.studentRepository.save(student);
        issue = new Issue(student,book);
        utils.issueRepository.save(issue);
    }

    @AfterEach
    public void reset() {
        utils.bookRepository.deleteById(book.getIsbn());
        utils.authorRepository.deleteById(author.getAuthorId());
        utils.studentRepository.deleteById(student.getUsn());
        utils.issueRepository.deleteById(issue.getIssueId());

    }


    @Test
    void addBook_validValues_book() {

        assertTrue(utils.bookRepository.existsById(book.getIsbn()));

    }

    @Test
    void findBookByTitle_validTitle_book() {
       Book book1 = utils.bookRepository.findByTitle(book.getTitle()).get();
       assertTrue(utils.bookRepository.findByTitle(book.getTitle()).isPresent());
       assertEquals(book1.getIsbn(),book.getIsbn());
    }

    @Test
    void findAllByAuthorBook_authorName_bookList() {
        List<Book> list =utils.authorRepository.findByAuthorBook(author.getName());

        assertEquals(list, 1);
    }

    @Test
    void addNewIssue() {
    }

    @Test
    void showBookByUsn_usnValid_Book() {
        Issue issue = utils.issueRepository.findByStudent(student.getUsn()).get();
        Book book = utils.bookRepository.findById(issue.getBook().getIsbn()).get();
        assertTrue(utils.bookRepository.findById(book.getIsbn()).isPresent());

    }
}