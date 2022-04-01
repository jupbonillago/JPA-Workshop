package com.example.demo.models;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.example.demo.repositores.StudentRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Se le indica que se ejecutará en runtime y puede declarar uno o más Beans
@Configuration
public class StudentConfig {

    // Configuración para almacenar un estudiante
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
			Student maria = new Student(
            "Maria",
            "maria@test.com",
            LocalDate.of(2000,Month.AUGUST,27));
            Student alex = new Student(
            "Alex",
            "alex@test.com",
            LocalDate.of(2004,Month.AUGUST,27));

            // Guarda los dos estudiantes creados
            repository.saveAll(List.of(maria, alex));
        };
    }
}
