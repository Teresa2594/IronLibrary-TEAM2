package com.ironhack.ironlibrary;

import com.ironhack.ironlibrary.Model.*;
import com.ironhack.ironlibrary.Repository.BookRepository;
import com.ironhack.ironlibrary.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class IronlibraryApplication {

    @Autowired


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(IronlibraryApplication.class, args);
        Utils utils = applicationContext.getBean(Utils.class);

       utils.menu();
    }
    }

