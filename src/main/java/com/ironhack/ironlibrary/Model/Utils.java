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

    boolean isPresent = true;

    public void addBook() {

        String isbn = null;
        String category = null;
        String authorName = null;

        //Check isbn
        while (isPresent) {
            try {
                isbn = InputKeyboard.checkFakeInt("Please enter isbn. Only numbers", false, scanner).trim();
                if (bookRepository.findById(isbn).isPresent()) throw new IllegalArgumentException("Isbn already exist");
                else {
                    isPresent = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());

            }
        }
        isPresent = true;

        //Input title
        String title = InputKeyboard.checkString("Please enter title", false, scanner).trim();


        //Check category
        while (isPresent) {
            try {
                category = InputKeyboard.checkString("Please enter category", false, scanner).trim();
                if (bookRepository.findOneByCategory(category).isPresent())
                    throw new IllegalArgumentException("Category already exist");
                else {
                    isPresent = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());

            }
        }
        isPresent = true;

        //Check authorName
        while (isPresent) {
            try {
                authorName = InputKeyboard.checkString("Please enter author name", false, scanner).trim();
                if (authorRepository.findByName(authorName).isPresent())
                    throw new IllegalArgumentException("Author name already exist");
                else {
                    isPresent = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());

            }
        }
        isPresent = true;


        //Check email
        String email = InputKeyboard.checkEmail("Please enter author email", false, scanner).trim();

        //Input quantity
        Integer quantity = InputKeyboard.checkInt("Please enter quantity", false, scanner);

        Book book = new Book(isbn, title, category, quantity);
        bookRepository.save(book);

        Author author = new Author(authorName, email, book);
        authorRepository.save(author);

    }

    //    Search book by title --> Martina
    public void findBookByTitle() {

        while (isPresent) {
            try {
                String titleBook = InputKeyboard.checkString("Please enter title ", false, scanner).trim();

                if (bookRepository.findByTitle(titleBook).isEmpty())
                    throw new IllegalArgumentException("Book cannot be found, please try again.");

                else {
                    isPresent = false;
                }

                Book book = bookRepository.findByTitle(titleBook).get();

                System.out.println(book.toString());
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
        isPresent = true;
    }


    //    Search book by category --> Víctor
    public void findBookByCategory() {

        while (isPresent) {
            try {
                String categoryBook = InputKeyboard.checkString("Please enter category ", false, scanner).trim();
                if (bookRepository.findOneByCategory(categoryBook).isEmpty()) throw new IllegalArgumentException("There is no book with this category");
                else {
                    isPresent = false;
                }

                List<Book> bookList = bookRepository.findAllByCategory(categoryBook);
                System.out.println(bookList);
            } catch (IllegalArgumentException iae){
                System.out.println(iae.getMessage());
            }

        } isPresent=true;

    }

    //    Search book by Author --> Víctor
    public void findBookByAuthor() {

        while (isPresent) {
            try {
                String author = InputKeyboard.checkString("Please enter author name", false, scanner).trim();

                if (!authorRepository.findByName(author).isPresent()) throw new IllegalArgumentException("Author cannot be found, please try again");
                else {
                    isPresent=false;
                }
                Author author1 = authorRepository.findByName(author).get();
                Book book = author1.getAuthorBook();
                System.out.println(book);

            } catch (IllegalArgumentException iae){
                System.out.println(iae.getMessage());
            }
        } isPresent=true;

    }


//
//    List all books along with author --> Maite
    public void findAllByAuthorBook() {

       while (isPresent) {
           try {
               String nameAuthor = InputKeyboard.checkString("Please enter author name ", false, scanner).trim();
               if (!authorRepository.findByName(nameAuthor).isPresent())
                   throw new IllegalArgumentException("Author cannot be found, please try again");
               else {
                   isPresent=false;
               }
               Author author1 = authorRepository.findByName(nameAuthor).get();
               List<Book> bookList = new ArrayList<Book>();
               bookList.add(author1.getAuthorBook());

               System.out.println(bookList);
           } catch (IllegalArgumentException iae){
               System.out.println(iae.getMessage());
           }

       } isPresent=true;

    }


    ////    Issue book to student --> Maite
    public void addNewIssue() {

        String usn=null;
        String name=null;
        String isbn=null;
        Student student;


         //Check usn
        while (isPresent) {
            try {
                usn =  usn = InputKeyboard.checkFakeInt("Please enter usn", false, scanner).trim();
                if (studentRepository.findById(usn).isPresent()) throw new IllegalArgumentException("Usn already exist");
                else {
                    isPresent = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());

            }
        }
        isPresent = true;

        //Input name
        name = InputKeyboard.checkString("Please enter student's name ", false, scanner).trim();

        //Check isbn

        while (isPresent) {
            try {
                isbn = InputKeyboard.checkFakeInt("Please enter isbn", false, scanner);
                if (!bookRepository.existsById(isbn))
                    throw new IllegalArgumentException("Book cannot be founded, please try again.");
                else {
                    isPresent = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());

            }
        }
        isPresent = true;

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
    public void showBookByUsn() {

       while (isPresent) {
           try {
               String usn = InputKeyboard.checkFakeInt("Please enter usn", false, scanner);
               if (studentRepository.findById(usn).isEmpty())
                   throw new IllegalArgumentException("Student cannot be found");
               else {
                   isPresent=false;
               }

               Student student = studentRepository.findById(usn).get();
               Issue issue = issueRepository.findByStudent(student).get();
               Book book = issue.getBook();
               System.out.println(book.toString());


           } catch (IllegalArgumentException iae) {
               System.out.println(iae.getMessage());
           }
       } isPresent=true;

    }

    //    Exit --> Fernando
    public void exit() {
        System.exit(0);
    }



    Scanner scanner = new Scanner(System.in);

    public void showOptions() {
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


    public void menu() {

        showOptions();
        boolean b = true;


        do {
            try {
                //Método para poder validar un numero
                int method = InputKeyboard.checkInt("Choose an option between 1 and 8", false, scanner);

                switch (method) {

                    case 1 -> {
                        addBook();
                        menu();
                    }

                    case 2 -> {
                        findBookByTitle();
                        menu();
                    }
                    case 3 -> {
                        findBookByCategory();
                        menu();
                    }
                    case 4 -> {
                        findBookByAuthor();
                        menu();
                    }
                    case 5 -> {

                        findAllByAuthorBook();
                        menu();
                    }
                    case 6 -> {
                        addNewIssue();
                        menu();
                    }
                    case 7 -> {

                        showBookByUsn();
                        menu();
                    }
                    case 8 -> {
                        System.out.println("Exiting...");
                        exit();
                        b = false;
                    }
                }
            } catch (IllegalArgumentException iae) {
                System.out.println("Error grave en la aplicación");
                showOptions();
            }
        } while (b);

    }




}













