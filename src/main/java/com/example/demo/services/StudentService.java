package com.example.demo.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.models.Student;
import com.example.demo.repositores.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Habilita que la clase sea instanciada (para usar con autowired), ie, la hace un Bean
// @Component
// Igual que @Component, se usa para denotar componentes de servicio (para legibilidad, nada más)
@Service
public class StudentService {

  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents(){
		return studentRepository.findAll();
	}

  public void addNewStudent(Student student) {
    // Puede o no recibir un estudiante
    Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    // Si recibe un valor aka si existe
    // En este caso revisa si el email ya está en uso
    if(studentOptional.isPresent()){
      throw new IllegalStateException("email already taken");
    }
    // Si pasa las pruebas almacena el estudiante
    studentRepository.save(student);
    //System.out.println(student);
  }

public void deleteStudent(Long studentId) {
  // Si existe un registro con las caracteristicas
  boolean exists = studentRepository.existsById(studentId);
  if(!exists){
    throw new IllegalStateException("student with id " + studentId + " does not exists");
  }
  // Elimina el estudiante con el Id
  studentRepository.deleteById(studentId);
}

// La entidad entra en un estado de manage
@Transactional
public void updateStudent(Long id, String name, String email) {
  // versión Compacta para verificar si el estudiante existe
  Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalStateException("student with id " + id + " does not exists"));
  // Si existe name, no tiene longitud 0 y es diferente del nombre almacenado se ejecuta
  if(name!=null && name.length() > 0 && !Objects.equals(student.getName(), name)){
    student.setName(name);
  }
  // Misma verificación que arriba
  if(email!=null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
    // Verifica adicionalmente que el email no haya sido ocupado ya
    // Puede o no recibir un estudiante
    Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
    // Si recibe un valor aka si existe
    // En este caso revisa si el email ya está en uso
    if(studentOptional.isPresent()){
      throw new IllegalStateException("email already taken");
    }
    // Si no, se actualiza el email del estudiante
    student.setEmail(email);
  }
}
    
}
