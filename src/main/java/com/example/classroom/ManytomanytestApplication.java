package com.example.classroom;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@RestController
@EntityScan
public class ManytomanytestApplication {

    public static void main(String[] args) {

        SpringApplication.run(ManytomanytestApplication.class, args);
    }





}
