package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student john = new Student("John",  LocalDate.of(2000, Month.JANUARY, 21), "johndoe@gmail.com");
            Student mariam = new Student("Mariam", LocalDate.of(2004, Month.JANUARY, 11), "mariam@gmail.com");
            repository.saveAll(List.of(john,mariam));
        };
    }
}
