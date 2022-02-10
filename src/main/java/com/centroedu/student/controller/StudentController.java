package com.centroedu.student.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.centroedu.student.entities.Student;
import com.centroedu.student.entities.Course;
import com.centroedu.student.service.CourseService;
import com.centroedu.student.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

	@Autowired
	StudentService studentService;
	@Autowired
	CourseService courseService;

// ------------------------ List all Students -------------------	

	@GetMapping
	public ResponseEntity<List<Student>> listado() {
		List<Student> students = studentService.listAllStudents();
		if (students.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(students);
	}

// ------------------------ Search a Student by ID -------------------	

	@GetMapping("/search/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
		Student student = studentService.getStudent(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(student);
	}

// ------------------------ Create a Student -------------------		

	@PutMapping("/create/{id}") 
	public ResponseEntity<Student> createStudent(@Valid @PathVariable("id") Long id, @RequestBody Student alumno,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoMensajes(result));
		}
		alumno.setId(id);
		Student student = studentService.createStudent(alumno);

		if (student == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(student); //
	}

// ------------------------ Delete a Student -------------------		

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id) {
		Student student = studentService.deleteStudent(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(student);
	}
	
//------------------------- Show all the Courses ---------------------
	
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAllCourses(){
		List<Course> courses = courseService.getAllCursos();
		
		if (courses.isEmpty()) { return ResponseEntity.noContent().build(); }
		
		return ResponseEntity.ok(courses);
	}

// ------------------------ Search a student by Course  -------------------		

	@GetMapping("/search/course/{curso_id}")
	public ResponseEntity<List<Student>> findByCurso(@RequestParam("curso_id") Long curso_id) {
		List<Student> student = new ArrayList<>();

		if (curso_id == null) {
			return ResponseEntity.notFound().build(); }
		 else {
				student = studentService.findByCurso(Course.builder().id(curso_id).build());
				if (student.isEmpty()) {
					return ResponseEntity.noContent().build();
				}
			}
		
		return ResponseEntity.ok(student);
	}

	private String formatoMensajes(BindingResult result) {
		List<Map<String, String>> errores = result.getFieldErrors().stream().map(err -> {
			Map<String, String> error = new HashMap<>();
			error.put(err.getField(), err.getDefaultMessage());
			return error;
		}).collect(Collectors.toList());

		ErrorMessage errorMessage = ErrorMessage.builder().code("01").mensajes(errores).build();

		ObjectMapper om = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = om.writeValueAsString(errorMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonString;
	}

}
