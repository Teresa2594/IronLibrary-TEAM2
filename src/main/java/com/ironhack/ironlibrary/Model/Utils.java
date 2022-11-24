package com.ironhack.ironlibrary.Model;

import com.ironhack.ironlibrary.Repository.BookRepository;
import com.ironhack.ironlibrary.Repository.IssueRepository;
import com.ironhack.ironlibrary.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import com.ironhack.ironlibrary.Repository.AuthorRepository;


public class Utils {

    @Autowired

    AuthorRepository authorRepository;
    BookRepository bookRepository;
    StudentRepository studentRepository;
    IssueRepository issueRepository;

//    Add a book --> Martina

    public void addBook(String isbn, String title, String cathegory, Integer quantity) {
        if (bookRepository.findById(isbn).isPresent()) throw new IllegalArgumentException("Book already exist.");
        Book book = new Book(isbn, title, cathegory, quantity);
        bookRepository.save(book);
        System.out.println(book.toString());
    }

    //    Search book by title --> Martina
    public void findBookByTitle(String title) {
        if (!bookRepository.findByTitle(title).isPresent())
            throw new IllegalArgumentException("Book cannot be founded, please try again.");
        Book book = bookRepository.findByTitle(title).get();
        System.out.println(book.toString());
    }


    //    Search book by category --> Víctor
    public void findBookByCategory(String category) {
        if(!bookRepository.findOneByCathegory(category).isPresent())throw new IllegalArgumentException(("There is no book with this category"));
        List<Book> bookList = bookRepository.findAllByCathegory(category);
        System.out.println(bookList);
    }

    //    Search book by Author --> Víctor
    public void findBookByAuthor(String author){
        if (!authorRepository.findByName(author).isPresent()) throw new IllegalArgumentException("El autor no existe");

        Book book = authorRepository.findByAuthorBook(author);
    }
//    List all books along with author --> Maite
    public void findAllByAuthorBook(String author) {

        if (!authorRepository.findByName(author).isPresent()) throw new IllegalArgumentException("El autor no existe");

        List<Book> bookList = authorRepository.findAllByAuthorBook(author);

        System.out.println(bookList);
    }

    //    Issue book to student --> Maite
    public void addNewIssue(String usn, String isbn) {

        //Buscamos el estudiante
        if (!studentRepository.existsById(usn)) throw new IllegalArgumentException("El estudiante no existe");
        else if (bookRepository.existsById(isbn)) throw new IllegalArgumentException("El libro no existe");

        Student student = studentRepository.findByUsn(usn).get();
        Book book = bookRepository.findByIsbn(isbn).get();

        //Creamos la nueva ISSUE
        Issue nuevaIssue = new Issue(student, book);
        System.out.println(nuevaIssue.toString());
    }

    //    List books by usn --> Fernando
    public void showBookByUsn(String usn) {
        if (!studentRepository.findById(usn).isPresent())
            throw new IllegalArgumentException("Student cannot be founded.");
        else if (!issueRepository.findByUsnStudent(usn).isPresent())
            throw new IllegalArgumentException("This student did not take any books.");
        Issue issue = issueRepository.findByUsnStudent(usn).get();
        Book book = bookRepository.findById(issue.getBook().getIsbn()).get();
        System.out.println(book.toString());
    }

    //    Exit --> Fernando
    public void exit() {
        System.exit(0);
    }

}
