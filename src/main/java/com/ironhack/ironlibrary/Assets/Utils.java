package com.ironhack.ironlibrary.Assets;


import com.ironhack.ironlibrary.Model.Author;
import com.ironhack.ironlibrary.Model.Book;
import com.ironhack.ironlibrary.Model.Issue;
import com.ironhack.ironlibrary.Model.Student;
import com.ironhack.ironlibrary.Repository.BookRepository;
import com.ironhack.ironlibrary.Repository.IssueRepository;
import com.ironhack.ironlibrary.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.ironhack.ironlibrary.Repository.AuthorRepository;
import org.springframework.stereotype.Component;

import static com.ironhack.ironlibrary.Assets.Constants.ANSI_BLUE;
import static com.ironhack.ironlibrary.Assets.Constants.ANSI_RESET;

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


    boolean isPresent = true;
    Scanner scanner = new Scanner(System.in);

    /**
     * @Description menu(): this method associate all commands with his method
     */
    public void menu() {

        showOptions();
        boolean b = true;

        do {
            try {

                int method = InputKeyboard.checkInt("Choose an option between 1 and 9", false, scanner);

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
                        showReturnDate();
                        menu();
                    }
                    case 9 -> {
                        System.out.println("Exiting...");
                        exit();
                        b = false;
                    }
                }
            } catch (IllegalArgumentException | InterruptedException iae) {
                System.out.println("Fatal error");
                showOptions();
            }
        } while (b);

    }

    /**
     * @Description showOptions(): this method will display the list of commands to navigate through the application
     */
    public void showOptions() {
        //First show options
        System.out.println();
        System.out.println(Constants.ANSI_CYAN + "----------------------------------------------------------------------------- " + ANSI_RESET);
        System.out.println(Constants.ANSI_CYAN + "1. ADD A BOOK:" + ANSI_RESET + " This action is responsible of adding a book and its author in the system.\n" +
                Constants.ANSI_CYAN + "2. SEARCH BOOK BY TITLE :" + ANSI_RESET + " This action is responsible for searching a book by title.\n" +
                Constants.ANSI_CYAN + "3. SEARCH BOOK BY CATEGORY:" + ANSI_RESET + " This action is responsible for searching a book by category.\n" +
                Constants.ANSI_CYAN + "4. SEARCH BOOK BY AUTHOR:" + ANSI_RESET + "  This action is responsible for searching a book by author name.\n" +
                Constants.ANSI_CYAN + "5. LIST ALL BOOKS ALONG WITH AUTHOR:" + ANSI_RESET + " This action is responsible for listing all the books available and there corresponding authors.\n" +
                Constants.ANSI_CYAN + "6. ISSUE BOOK TO STUDENT:" + ANSI_RESET + " This action is responsible for creating a student and issuing him/her the specified book.\n" +
                Constants.ANSI_CYAN + "7. LIST BOOKS BY USN:" + ANSI_RESET + "This action is responsible for listing all books rented by the specified student..\n" +
                Constants.ANSI_CYAN + "8. SHOW BOOKS TO BE RETURNED TODAY:" + ANSI_RESET + "This action is responsible for listing all books to be returned today..\n" +
                Constants.ANSI_RED + "9. EXIT" + Constants.ANSI_RED);
    }


    /**
     * @Description addBook(): this method will create a book with its author and save them
     */
    public void addBook() throws InterruptedException {

        String isbn = null;
        String category = null;
        String authorName = null;
        String title = null;
        int quantity = 0;


        while (isPresent) {
            try {
                isbn = InputKeyboard.checkFakeInt("Please enter isbn. Only numbers", false, scanner).trim();
                if (bookRepository.findById(isbn).isPresent())
                    throw new IllegalArgumentException("Isbn already exists");
                else {
                    isPresent = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                TimeUnit.MILLISECONDS.sleep(1000);

            }
        }
        isPresent = true;

        while (isPresent) {
            try {
                title = InputKeyboard.checkString("Please enter title", false, scanner).trim();
                if (bookRepository.findByTitle(title).isPresent())
                    throw new IllegalArgumentException("A Book with this name already exists");
                else {
                    isPresent = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                TimeUnit.MILLISECONDS.sleep(1000);
            }

        }
        isPresent = true;


        while (isPresent) {
            try {
                category = InputKeyboard.checkString("Please enter category", false, scanner).trim();
                if (bookRepository.findOneByCategory(category).isPresent())
                    throw new IllegalArgumentException("Category already exists");
                else {
                    isPresent = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }
        isPresent = true;


        while (isPresent) {
            try {
                authorName = InputKeyboard.checkString("Please enter author name", false, scanner).trim();
                if (authorRepository.findByName(authorName).isPresent())
                    throw new IllegalArgumentException("Author name already exists");
                else {
                    isPresent = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }
        isPresent = true;


        String email = InputKeyboard.checkEmail("Please enter author email", false, scanner).trim();


        while (quantity <= 0) {
            try {
                quantity = InputKeyboard.checkInt("Please enter quantity", false, scanner);
                if (quantity <= 0) throw new InputException("Input not valid. There must be at least one book");

            } catch (InputException ie) {
                System.out.println(ie.getMessage());
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }

        Book book = new Book(isbn, title, category, quantity);
        bookRepository.save(book);

        Author author = new Author(authorName, email, book);
        authorRepository.save(author);
        System.out.println();
        System.out.println(ANSI_BLUE + book.getTitle() + " added successfully." + ANSI_RESET);
        TimeUnit.MILLISECONDS.sleep(1000);
    }


    /**
     * @Description findBookByTitle(): this method will search a book using its title as a parameter
     */
    public void findBookByTitle() throws InterruptedException {

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
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (IllegalArgumentException | InterruptedException iae) {
                System.out.println(iae.getMessage());
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }
        isPresent = true;
    }


    /**
     * @Description findBookByCategory(): this method will search a book using its category as a parameter
     */
    public void findBookByCategory() throws InterruptedException {

        while (isPresent) {
            try {
                String categoryBook = InputKeyboard.checkString("Please enter category ", false, scanner).trim();
                if (bookRepository.findOneByCategory(categoryBook).isEmpty())
                    throw new IllegalArgumentException("There is no book with this category");
                else {
                    isPresent = false;
                }

                List<Book> bookList = bookRepository.findAllByCategory(categoryBook);

                System.out.println(bookList);
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (IllegalArgumentException | InterruptedException iae) {
                System.out.println(iae.getMessage());
                TimeUnit.MILLISECONDS.sleep(1000);
            }

        }
        isPresent = true;

    }


    /**
     * @Description findBookByAuthor(): this method will search a book using its author's name as a parameter
     */
    public void findBookByAuthor() throws InterruptedException {

        while (isPresent) {
            try {
                String author = InputKeyboard.checkString("Please enter author name", false, scanner).trim();

                if (!authorRepository.findByName(author).isPresent())
                    throw new IllegalArgumentException("Author cannot be found, please try again");
                else {
                    isPresent = false;
                }
                Author author1 = authorRepository.findByName(author).get();
                Book book = author1.getAuthorBook();
                System.out.println(book);
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (IllegalArgumentException | InterruptedException iae) {
                System.out.println(iae.getMessage());
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }
        isPresent = true;

    }


    /**
     * @Description findBookByAuthor(): this method will search a booklist using their author's name as a parameter
     */
    public void findAllByAuthorBook() throws InterruptedException {

        while (isPresent) {
            try {
                String nameAuthor = InputKeyboard.checkString("Please enter author name ", false, scanner).trim();
                if (!authorRepository.findByName(nameAuthor).isPresent())
                    throw new IllegalArgumentException("Author cannot be found, please try again");
                else {
                    isPresent = false;
                }
                Author author1 = authorRepository.findByName(nameAuthor).get();
                List<Book> bookList = new ArrayList<Book>();
                bookList.add(author1.getAuthorBook());

                System.out.println(bookList);
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (IllegalArgumentException | InterruptedException iae) {
                System.out.println(iae.getMessage());
                TimeUnit.MILLISECONDS.sleep(1000);
            }

        }
        isPresent = true;

    }

    /**
     * @Description addNewIssue(): this method will generate a new issue creating a new student and linking a book to him
     */
    public void addNewIssue() throws InterruptedException {

        String usn = null;
        String name = null;
        String isbn = null;
        Student student;


        while (isPresent) {
            try {
                usn = usn = InputKeyboard.checkFakeInt("Please enter usn", false, scanner).trim();
                if (studentRepository.findById(usn).isPresent())
                    throw new IllegalArgumentException("Usn already exist");
                else {
                    isPresent = false;
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }
        isPresent = true;


        name = InputKeyboard.checkString("Please enter student's name ", false, scanner).trim();


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
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }
        isPresent = true;

        student = new Student(usn, name);
        studentRepository.save(student);

        Book book = bookRepository.findById(isbn).get();

        try {
            if (book.getQuantity() <= 0) throw new InputException("There is no book in stock");
            book.setQuantity(book.getQuantity() - 1);

            bookRepository.save(book);

            Issue nuevaIssue = new Issue(student, book);
            issueRepository.save(nuevaIssue);

            System.out.println();
            System.out.println(ANSI_BLUE + "Issue " + " added successfully." + ANSI_RESET);
            System.out.println(nuevaIssue.toString());
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InputException ie) {
            System.out.println(ie.getMessage());
            System.out.println(ANSI_BLUE + "This book will be available on: " + ANSI_RESET);
            List<String> listDates = showReturnDate(book);
            System.out.println(listDates.toString());
            TimeUnit.MILLISECONDS.sleep(1000);
        }
    }

    /**
     * @param book
     * @return List<String>
     * @Description showReturnDate: this method will show the return date of the book passed as a parameter
     */
    public List<String> showReturnDate(Book book) throws InterruptedException {
        List<String> returnDates = new ArrayList<>();

        List<Issue> issueBooks = issueRepository.findAllByBook(book);
        for (Issue i : issueBooks) {
            returnDates.add(i.getReturnDate().toString());
        }
        return returnDates;

    }

    /**
     * @Description showReturnDate(): this method will show a list of books to be returned today
     */
    public void showReturnDate() throws InterruptedException {
        List<String> returnDates = new ArrayList<>();
        List<Issue> issueBooks = issueRepository.findAll();

        for (Issue i : issueBooks) {
            if (i.getReturnDate().equals(LocalDate.now().toString())) {
                String titleBook = i.getBook().getTitle();
                String returnDate = i.getReturnDate();
                String concatStrings = titleBook + " " + " " + returnDate;
                returnDates.add(concatStrings);
            }
            continue;

        }
        System.out.println();
        System.out.println(ANSI_BLUE + "Books to return today:" + ANSI_RESET);
        System.out.println(returnDates.toString());
        TimeUnit.MILLISECONDS.sleep(1000);
    }

    /**
     * @Description showBookByUsn(): this method will display a book using its student's usn as a parameter
     */
    public void showBookByUsn() throws InterruptedException {

        while (isPresent) {
            try {
                String usn = InputKeyboard.checkFakeInt("Please enter usn", false, scanner);
                if (studentRepository.findById(usn).isEmpty())
                    throw new IllegalArgumentException("Student cannot be found");
                else {
                    isPresent = false;
                }

                Student student = studentRepository.findById(usn).get();
                Issue issue = issueRepository.findByStudent(student).get();
                Book book = issue.getBook();
                System.out.println(book.toString());
                TimeUnit.MILLISECONDS.sleep(1000);

            } catch (IllegalArgumentException | InterruptedException iae) {
                System.out.println(iae.getMessage());
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }
        isPresent = true;

    }


    /**
     * @Description exit(): this method stop the application from running
     */
    public void exit() throws InterruptedException {
        System.out.println(ANSI_BLUE + "exiting program..."+ ANSI_RESET);
        TimeUnit.MILLISECONDS.sleep(1000);
        System.exit(0);
    }

}













