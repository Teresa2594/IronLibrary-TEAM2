package com.ironhack.ironlibrary;

import com.ironhack.ironlibrary.Model.*;
import com.ironhack.ironlibrary.Repository.BookRepository;
import com.ironhack.ironlibrary.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class IronlibraryApplication {

@Autowired


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(IronlibraryApplication.class, args);
        Utils myClass = applicationContext.getBean(Utils.class);
        Book book1 = new Book("B08","Prueba", "Comedia",10);
        Author author1 = new Author("Adri","adri@gmail.com",book1);
        Student student1 = new Student("S002","David");
        Issue issue1 = new Issue(student1,book1);


       // myClass.addBook("b03","prueba","kaka",8,"gema","email@email.com");
       // myClass.findBookByTitle(book1.getTitle());
       // myClass.findBookByCategory("Comedia");
        // myClass.findBookByAuthor("gema");
       // myClass.findAllByAuthorBook("gema");
    //myClass.addNewIssue("s04","mai","b03");
   // myClass.showBookByUsn("s04");
    myClass.exit();
    }



}
