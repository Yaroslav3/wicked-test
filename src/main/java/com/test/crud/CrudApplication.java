package com.test.crud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CrudApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(CrudApplication.class).run(args);

    }

}

