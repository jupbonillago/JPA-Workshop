package com.example.demo.models;

import java.time.LocalDate;
import java.time.Period;

// Importa todo el paquete de persistence
import javax.persistence.*;

// Indicador de entidad para Hibernate
@Entity
// Reconozca como tabla en la db
@Table
// Modelo de estudiante que será mapeado a la base de datos
public class Student {
    // Indicador de ID
    @Id
    // Generador de secuencia para el id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    // Se indica como se generará el valor y el generador por usar
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    // No es estrictamente necesario, ayuda a configurar información adicional
    private Long id;
    private String name;
    @Column(
        name = "email",
        nullable = false,
        unique = true
    )
    private String email;
    private LocalDate dateOfBirth;
    // El dato no es mapeado a la base de datos
    @Transient
    private Integer age;

    // Constructor sin ID
    public Student(String name, String email, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    // Constructor con todos los campos
    public Student(Long id, String name, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    // Default constructor NECESARIO
    public Student(){
        //this.email = "";
    }

    // Getters y Setters -----------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // PERSONALIZADO
    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{age=" + age + ", id=" + id + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", name="
                + name + "}";
    }

    // ---------------------------------------------------------------------------
    

    
    
}
