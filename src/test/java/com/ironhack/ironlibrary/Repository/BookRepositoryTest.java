package com.ironhack.ironlibrary.Repository;

import com.ironhack.ironlibrary.Model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookRepositoryTest {
@Autowired
BookRepository bookRepository;
    @Test
    void findByTitle() {
        Book book = new Book("ang","prueba","lala", 7);
        bookRepository.save(book);

    }
}