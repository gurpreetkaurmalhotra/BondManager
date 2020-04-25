package com.gurpreet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BondApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(BondApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
