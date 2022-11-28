package com.ironhack.ironlibrary.Model;

import com.ironhack.ironlibrary.Repository.BookRepository;
import com.ironhack.ironlibrary.Repository.IssueRepository;
import com.ironhack.ironlibrary.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

            if(bookRepository.findById(isbn).isPresent()) throw new IllegalArgumentException("Isbn already exist");

            if (authorRepository.findByName(name).isPresent()) throw new IllegalArgumentException("Author already exist");

            Book book = new Book(isbn, title, category, quantity);
            bookRepository.save(book);

            Author author = new Author(name, email, book);
            authorRepository.save(author);

        }

    //    Search book by title --> Martina
    public void findBookByTitle(String title) {

        if (bookRepository.findByTitle(title).isEmpty()) throw new IllegalArgumentException("Book cannot be found, please try again.");
        Book book = bookRepository.findByTitle(title).get();
        System.out.println(book.toString());
    }


   //    Search book by category --> Víctor
    public void findBookByCategory(String category) {
        if (bookRepository.findOneByCategory(category).isEmpty()) throw new IllegalArgumentException(("There is no book with this category"));
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
        issueRepository.save(nuevaIssue);

        System.out.println(nuevaIssue.toString());

    }

    //    List books by usn --> Fernando
    public void showBookByUsn(String usn) {
      try {

          if (studentRepository.findById(usn).isEmpty()) throw new IllegalArgumentException("Student cannot be found");

          Student student = studentRepository.findById(usn).get();
              Issue issue = issueRepository.findByStudent(student).get();
              Book book = issue.getBook();
              System.out.println(book.toString());


      } catch (Exception e) {
          e.getMessage();
      }
    }

//    Exit --> Fernando
    public void exit(){
        System.exit(0);
    }

/*    public void checkBD (String str, Integer caso) {

        boolean isInputCorrect=false;
        while (isInputCorrect){
        switch (caso){
            case 1:
                try {
                    if (bookRepository.findById(str).isPresent()) throw new IllegalArgumentException("Book already exist");
                } catch (IllegalArgumentException iae){
                    iae.getMessage();

                }

        }
        } isInputCorrect=true;
    }*/



    Scanner scanner = new Scanner(System.in);

    public void showOptions () {
        //First show options
        System.out.println();
        System.out.println(Constants.ANSI_CYAN + "----------------------------------------------------------------------------- " + Constants.ANSI_RESET);
        System.out.println(Constants.ANSI_CYAN + "1. ADD A BOOK:" + Constants.ANSI_RESET + " This action is responsible of adding a book and its author in the system.\n" +
                Constants.ANSI_CYAN + "2. SEARCH BOOK BY TITLE :" + Constants.ANSI_RESET + " This action is responsible for searching a book by title.\n" +
                Constants.ANSI_CYAN + "3. SEARCH BOOK BY CATEGORY:" + Constants.ANSI_RESET + " This action is responsible for searching a book by category.\n" +
                Constants.ANSI_CYAN + "4. SEARCH BOOK BY AUTHOR:" + Constants.ANSI_RESET + "  This action is responsible for searching a book by author name.\n" +
                Constants.ANSI_CYAN + "5. LIST ALL BOOKS ALONG WITH AUTHOR:" + Constants.ANSI_RESET + " This action is responsible for listing all the books available and there corresponding authors.\n" +
                Constants.ANSI_CYAN + "6. ISSUE BOOK TO STUDENT:" + Constants.ANSI_RESET + " This action is responsible for creating a student and issuing him/her the specified book.\n" +
                Constants.ANSI_CYAN + "7. LIST BOOKS BY USN:" + Constants.ANSI_RESET + "This action is responsible for listing all books rented by the specified student..\n" +
                Constants.ANSI_RED + "8. EXIT" + Constants.ANSI_RED);
    }


    public void menu () {

        showOptions();
        boolean b = true;

        do {
        try {
            //Método para poder validar un numero
            int method = InputKeyboard.checkInt("Choose an option between 1 and 8", false, scanner);

            switch (method) {

                case 1 -> {

                    String isbn = InputKeyboard.checkFakeInt("Please enter isbn", false, scanner).trim();
                    String title = InputKeyboard.checkString("Please enter title", false, scanner).trim();
                    String category = InputKeyboard.checkString("Please enter category", false, scanner).trim();
                    String authorName = InputKeyboard.checkString("Please enter author name", false, scanner).trim();
                    String email = InputKeyboard.checkString("Please enter author email", false, scanner).trim();
                    Integer quantity = InputKeyboard.checkInt("Please enter quantity", false, scanner);
                    addBook(isbn, title, category, quantity, authorName, email);
                    menu();
                }
                case 2 -> {
                    String titleBook = InputKeyboard.checkString("Please enter title ", false, scanner).trim();
                    findBookByTitle(titleBook);
                    menu();
                }
                case 3 -> {
                    String categoryBook = InputKeyboard.checkString("Please enter category ", false, scanner).trim();
                    findBookByCategory(categoryBook);
                    menu();
                }
                case 4 -> {
                    String author = InputKeyboard.checkString("Please enter author name ", false, scanner).trim();
                    findBookByAuthor(author);
                    menu();
                }
                case 5 -> {
                    String nameAuThor = InputKeyboard.checkString("Please enter author name ", false, scanner).trim();
                    findAllByAuthorBook(nameAuThor);
                    menu();
                }
                case 6 -> {

                    String usn = InputKeyboard.checkFakeInt("Please enter usn", false, scanner);
                    String name2 = InputKeyboard.checkString("Please enter student's name ", false, scanner).trim();

                    String isbn = InputKeyboard.checkFakeInt("Please enter isbn", false, scanner);
                    addNewIssue(usn, name2, isbn);
                    menu();
                }
                case 7 -> {
                    String usn = InputKeyboard.checkFakeInt("Please enter usn", false, scanner);
                    showBookByUsn(usn);
                    menu();
                }
                case 8 -> {
                    System.out.println("Exiting...");
                    exit();
                    b = false;
                }
            }
        } catch (IllegalArgumentException iae){
            System.out.println("Error grave en la aplicación");
            showOptions();
        }
        }while (b);

        }
        }











