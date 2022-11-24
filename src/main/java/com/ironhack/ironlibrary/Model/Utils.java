package com.ironhack.ironlibrary.Model;

import com.ironhack.ironlibrary.Repository.AuthorRepository;
import com.ironhack.ironlibrary.Repository.BookRepository;
import com.ironhack.ironlibrary.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class Utils {

    @Autowired

    AuthorRepository authorRepository;
    BookRepository bookRepository;
    StudentRepository studentRepository;

//    Add a book --> Martina
//    Search book by title --> Martina
//    Search book by category --> Víctor
//    Search book by Author --> Víctor
//    List all books along with author --> Maite
        public void findAllByAuthorBook (String author) {

            if (!authorRepository.findByName(author).isPresent())throw new IllegalArgumentException("El autor no existe");

            List<Book> bookList = authorRepository.findAllByAuthorBook(author);

             System.out.println(bookList);
        }

//    Issue book to student --> Maite
        public void addNewIssue (String usn , String isbn ) {

            //Buscamos el estudiante
            if (!studentRepository.existsById(usn))throw new IllegalArgumentException("El estudiante no existe");
            else if (bookRepository.existsById(isbn))throw new IllegalArgumentException("El libro no existe");

            Student student = studentRepository.findByUsn(usn).get();
            Book book = bookRepository.findByIsbn(isbn).get();

            //Creamos la nueva ISSUE
            Issue nuevaIssue = new Issue (student,book);
            System.out.println(nuevaIssue.toString());
        }

//    List books by usn --> Fernando
//    Exit --> Fernando


}
