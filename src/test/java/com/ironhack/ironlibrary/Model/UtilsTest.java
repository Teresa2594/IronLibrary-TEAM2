package com.ironhack.ironlibrary.Model;

import com.ironhack.ironlibrary.Repository.BookRepository;
import com.ironhack.ironlibrary.Repository.IssueRepository;
import com.ironhack.ironlibrary.Repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UtilsTest {
@Autowired
IssueRepository issueRepository;
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
}