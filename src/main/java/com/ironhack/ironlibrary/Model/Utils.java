package com.ironhack.ironlibrary.Model;

import com.ironhack.ironlibrary.Repository.BookRepository;
import com.ironhack.ironlibrary.Repository.IssueRepository;
import com.ironhack.ironlibrary.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Utils {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    IssueRepository issueRepository;
    @Autowired
    StudentRepository studentRepository;


//    Add a book --> Martina

    public void addBook(String isbn, String title, String cathegory, Integer quantity){
        if(bookRepository.findById(isbn).isPresent()) throw new IllegalArgumentException("Book already exist.");
        Book book = new Book(isbn,title,cathegory,quantity);
        bookRepository.save(book);
        System.out.println(book.toString());
    }
//    Search book by title --> Martina
    public void findBookByTitle(String title){
        if (!bookRepository.findByTitle(title).isPresent())throw new IllegalArgumentException("Book cannot be founded, please try again.");
        Book book = bookRepository.findByTitle(title).get();
        System.out.println(book.toString());
    }


//    Search book by category --> Víctor
//    Search book by Author --> Víctor
//    List all books along with author --> Maite
//    Issue book to student --> Maite
//    List books by usn --> Fernando
      public void showBookByUsn(String usn){
        if(!studentRepository.findById(usn).isPresent()) throw  new IllegalArgumentException("Student cannot be founded.");
        else if(!issueRepository.findByUsnStudent(usn).isPresent()) throw new IllegalArgumentException("This student did not take any books.");
        Issue issue = issueRepository.findByUsnStudent(usn).get();
        Book book = bookRepository.findById(issue.getBook().getIsbn()).get();
          System.out.println(book.toString());
      }

//    Exit --> Fernando
    public void exit(){
        System.exit(0);
    }

}
