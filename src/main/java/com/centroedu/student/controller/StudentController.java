package com.centroedu.student.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.centroedu.student.entities.Student;
import com.centroedu.student.entities.Course;
import com.centroedu.student.service.CourseService;
import com.centroedu.student.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;

import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

	@Autowired
	StudentService studentService;
	@Autowired
	CourseService courseService;

	@ApiOperation(value = "Show a list of all Students")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
							@ApiResponse(code = 404, message = "Service not found"),
							@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class)
	})
	@GetMapping
	public ResponseEntity<List<Student>> listado() {
		List<Student> students = studentService.listAllStudents();
		if (students.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(students);
	}

	@ApiOperation(value = "Find a Student search by Id")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
							@ApiResponse(code = 404, message = "Service not found"),
							@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class)
	})
	@GetMapping("/search/{id}")
	public ResponseEntity<Student> getStudent(@ApiParam(value = "Id of the Student") @PathVariable("id") Long id) {
		Student student = studentService.getStudent(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(student);
	}

	@ApiOperation(value = "create a Student")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
							@ApiResponse(code = 404, message = "Service not found"),
							@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class) 
	})
	@PutMapping("/create/{id}")
	public ResponseEntity<Student> createStudent(
			@ApiParam(value = "Id of the Student") @Valid @PathVariable("id") Long id,
			@ApiParam(value = "A Student send as a parameter") @RequestBody Student alumno,
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

	@ApiOperation(value = "delete a Student")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
							@ApiResponse(code = 404, message = "Service not found"),
							@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class) 
	})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Student> deleteStudent(@ApiParam(value = "Id of the Student") @PathVariable("id") Long id) {
		Student student = studentService.deleteStudent(id);
		if (id == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(student);
	}

	@ApiOperation(value = "Show all the Courses")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
							@ApiResponse(code = 404, message = "Service not found"),
							@ApiResponse(code = 200, message = "Successful retrieval", response = Course.class) 
	})
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAllCourses() {
		List<Course> courses = courseService.getAllCursos();

		if (courses.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(courses);
	}

	@ApiOperation(value = "Show all students in a Course")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
							@ApiResponse(code = 404, message = "Service not found"),
							@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class)
	})
	@GetMapping("/courses/search/{id}")
	public ResponseEntity<List<Student>> findByCourse(@ApiParam(value = "Id of the Course") @PathVariable("id") Course id) {

		List<Student> students = studentService.findByCourse(id);

		if (students.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(students);

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
