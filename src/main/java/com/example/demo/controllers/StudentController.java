package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.Student;
import com.example.demo.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Decorator para indicar que se trata de un controlador Rest
@RestController
// Mapeo de request
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    // Indica que StudentService debería ser Enlazado automaticamente para nosotros, aka, instanciado e inyectado al constructor
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Retorna un JSON Array de estudiantes
	@GetMapping("/list")
	public List<Student> getStudents(){
		return studentService.getStudents();
	}

	// Metodo Post tomando estudiante desde el body
	@PostMapping
	public void registerNewStudent(@RequestBody Student student){
		studentService.addNewStudent(student);
	}

	// Toma el Id del estudiante como una variable de path
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long id){
		studentService.deleteStudent(id);
	}

	// Metodo put para actualizar nombre y/o email
	@PutMapping(path = "{studentId}")
	public void updateStudent(@PathVariable("studentId") Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String email){
		studentService.updateStudent(id,name,email);
	}



    // Método Get básico retornando un String
	@GetMapping
	public String hello(){
		return "Students Controller";
	} 

	// Retorna un JSON Array sin necesidad de casting
	@GetMapping("/test")
	public List<String> helloArray(){
		return List.of("Hello", "World");
	} 


    
}
