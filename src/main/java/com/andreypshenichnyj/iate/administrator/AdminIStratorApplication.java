package com.andreypshenichnyj.iate.administrator;

import com.andreypshenichnyj.iate.administrator.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminIStratorApplication {

    @Autowired
    MonitoringService monitoringService;

    public static void main(String[] args) {
        SpringApplication.run(AdminIStratorApplication.class, args);
    }
}
