package com.example.demo.repositores;

import java.util.Optional;

import com.example.demo.models.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// Indica que esta interfaz es un repositorio
@Repository
// Se indica que se usará Student como tipo y que su llave es de tipo Long
public interface StudentRepository extends JpaRepository<Student, Long>{

    // Método personalizado que equivale a:
    // SELECT * FROM student WHERE email = ? (email dado) equivalente POR DEFECTO
    // Con Query se indica especificamente cual es el Query que se ejecutará
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email); 
    
}
