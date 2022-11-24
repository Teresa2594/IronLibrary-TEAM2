package com.ironhack.ironlibrary.Model;

import com.ironhack.ironlibrary.Repository.AuthorRepository;
import com.ironhack.ironlibrary.Repository.BookRepository;
import com.ironhack.ironlibrary.Repository.IssueRepository;
import com.ironhack.ironlibrary.Repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UtilsTest {
@Autowired
IssueRepository issueRepository;
@Autowired
AuthorRepository authorRepository;
@Autowired
BookRepository  bookRepository;
@Autowired
StudentRepository  studentRepository;

    @Test
    void addBook_validParams_book() {

    }

    @Test
    void findBookByTitle() {
    }

    @Test
    void showBookByUsn() {
    }

    @Test
    void findBookByCategory_correctCategory_correctBook() {
        List<Book> bookList = bookRepository.findAllByCathegory("aventura");
        System.out.println(bookList);
        assertEquals(1,bookList.size());
    }

    @Test
    void findBookByName_correctAuthor_correctBook() {
        Optional<Author> author = authorRepository.findByName("name");
        System.out.println("EL libro es: "+author.toString());
//        assertEquals("isbn2",book.getIsbn());
    }
//    @Test
//    void findBookByAuthor_correctAuthor_correctBook() {
//        Book book = authorRepository.findByAuthorBook("name");
//        System.out.println("EL libro es: "+book.toString());
////        assertEquals("isbn2",book.getIsbn());
//    }
}