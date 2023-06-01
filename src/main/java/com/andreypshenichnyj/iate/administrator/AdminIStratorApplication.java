package com.andreypshenichnyj.iate.administrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminIStratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminIStratorApplication.class, args);
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("It is worked!");
            }
        }).start();
    }
}
