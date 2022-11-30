package com.ironhack.ironlibrary;

import com.ironhack.ironlibrary.Assets.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class IronlibraryApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(IronlibraryApplication.class, args);
        Utils utils = applicationContext.getBean(Utils.class);

        utils.menu();
    }
    }

