package com.ironhack.ironlibrary.Model;

import com.ironhack.ironlibrary.Repository.BookRepository;
import com.ironhack.ironlibrary.Repository.IssueRepository;
import com.ironhack.ironlibrary.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.ironhack.ironlibrary.Repository.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    IssueRepository issueRepository;

//    Add a book --> Martina

    public void addBook(String isbn, String title, String category, Integer quantity, String name, String email) {
        if (authorRepository.findByName(name).isPresent()) throw new IllegalArgumentException("Author already exist");
        if (bookRepository.findById(isbn).isPresent()) throw new IllegalArgumentException("Book already exist.");
        Book book = new Book(isbn, title, category, quantity);
        bookRepository.save(book);
        Author author = new Author(name, email, book);
        authorRepository.save(author);
        System.out.println(book.toString());
        System.out.println(author.toString());

    }

    //    Search book by title --> Martina
    public void findBookByTitle(String title) {
        if (!bookRepository.findByTitle(title).isPresent())
            throw new IllegalArgumentException("Book cannot be found, please try again.");
        Book book = bookRepository.findByTitle(title).get();
        System.out.println(book.toString());
    }

    //
//    //    Search book by category --> Víctor
    public void findBookByCategory(String category) {
        if (!bookRepository.findOneByCategory(category).isPresent())
            throw new IllegalArgumentException(("There is no book with this category"));
        List<Book> bookList = bookRepository.findAllByCategory(category);
        System.out.println(bookList);
    }

    //    Search book by Author --> Víctor
    public void findBookByAuthor(String author) {
        if (!authorRepository.findByName(author).isPresent()) throw new IllegalArgumentException("El autor no existe");
        Author author1 = authorRepository.findByName(author).get();
        Book book = author1.getAuthorBook();
        System.out.println(book);
    }

    //
//
//    List all books along with author --> Maite
    public void findAllByAuthorBook(String author) {

        if (!authorRepository.findByName(author).isPresent())
            throw new IllegalArgumentException("Author cannot be found, please try again.");
        Author author1 = authorRepository.findByName(author).get();
        List<Book> bookList = new ArrayList<Book>();
        bookList.add(author1.getAuthorBook());

        System.out.println(bookList);
    }

    //
////    Issue book to student --> Maite
    public void addNewIssue(String usn, String name, String isbn) {

        //Buscamos el estudiante
        Student student;
        if (!bookRepository.existsById(isbn))
            throw new IllegalArgumentException("Book cannot be founded, please try again.");
        else if (studentRepository.existsById(usn)) throw new IllegalArgumentException("Student already exists or already has a book");
        student = new Student(usn, name);
        studentRepository.save(student);
        Book book = bookRepository.findById(isbn).get();
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        //Creamos la nueva ISSUE
        Issue nuevaIssue = new Issue(student, book);

        System.out.println(nuevaIssue.toString());

    }

    //    List books by usn --> Fernando
    public void showBookByUsn(String usn) {
        if (!studentRepository.findById(usn).isPresent())
            throw new IllegalArgumentException("Student cannot be found.");
        else if (!issueRepository.findByStudent(usn).isPresent())
            throw new IllegalArgumentException("This student did not take any books.");
        Issue issue = issueRepository.findByStudent(usn).get();
        Book book = bookRepository.findById(issue.getBook().getIsbn()).get();
        System.out.println(book.toString());
    }

//    Exit --> Fernando
    public void exit(){
        System.exit(0);
    }

}
