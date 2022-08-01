	package com.centroedu.student.controller;

	import java.util.List;


	import com.centroedu.student.utils.exceptions.*;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.CrossOrigin;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.centroedu.student.entities.Student;
	import com.centroedu.student.entities.Course;
	import com.centroedu.student.service.CourseService;
	import com.centroedu.student.service.StudentService;

	import io.swagger.annotations.ApiOperation;
	import io.swagger.annotations.ApiParam;
	import io.swagger.annotations.ApiResponses;
	import lombok.extern.slf4j.Slf4j;
	import io.swagger.annotations.ApiResponse;

	import javax.validation.Valid;

	@Slf4j
	@CrossOrigin(origins = "http://localhost:4200")
	@RestController
	@RequestMapping("api/v1")
	public class StudentController {

		@Autowired
		StudentService studentService;
		@Autowired
		CourseService courseService;

		@ApiOperation(value = "Show a list of all Students")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
				@ApiResponse(code = 404, message = "Service not found"),
				@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class) })
		@GetMapping("/students")
		public ResponseEntity<List<Student>> listStudents() {

			List<Student> students = studentService.listAllStudents();
			if (students.isEmpty()) {
				log.error("Students not found");
				throw new ListStudentsNotFoundException("Students not found");
			}

			return ResponseEntity.ok(students);
		}

		@ApiOperation(value = "Find a Student search by Id")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
				@ApiResponse(code = 404, message = "Service not found"),
				@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class) })
		@GetMapping("/student/id/{id}")
		public ResponseEntity<Student> getStudent(@ApiParam(value = "Id of the Student") @PathVariable("id") Long id) {
			if (id < 0) {
				throw new InvalidIdException("Invalid Id");
		}

			Student student = studentService.getStudent(id);

			if (student == null ) {
				log.error("Student with id{} not found", id);
				throw new StudentNotFoundException("Student not found");
			}

			return ResponseEntity.ok(student);
		}

		@ApiOperation(value = "create a Student")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
				@ApiResponse(code = 404, message = "Service not found"),
				@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class) })
		@PostMapping("/create")
		public ResponseEntity<Student> createStudent(
				@ApiParam(value = "A Student send as a parameter") @RequestBody @Valid Student alumno, BindingResult result){
			log.info("Creating Student: {}", alumno);

			if(result.hasErrors()){
				throw new DataValidationException("Invalid Data", result);
			}

			Student student = studentService.createStudent(alumno);

			if (student == null) {
				throw new StudentNotFoundException("Student not found");
			}

			return ResponseEntity.ok(student);
		}

		@ApiOperation(value = "update a Student")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
				@ApiResponse(code = 404, message = "Service not found"),
				@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class) })
		@PutMapping("/update/{id}")
		public ResponseEntity<Student> updateStudent(@ApiParam(value = "Id of the Student") @PathVariable("id") Long id,
				 @RequestBody @Valid Student student, BindingResult result){
			log.info("Updating Student with id{}", id);

			if (result.hasErrors()){
				throw new DataValidationException("Invalid Data", result);
			}

			if (id < 0){
				throw new InvalidIdException("Invalid Id");
			}

			Student student1 = studentService.getStudent(id);

			if (student1 == null) {
				log.error("Unable to update, Student with id{} not found", id);
				throw new StudentNotFoundException("Student not found");
			}
			student.setId(id);
			student1 = studentService.updateStudent(student);

			return ResponseEntity.ok(student1);
		}

		@ApiOperation(value = "delete a Student")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
				@ApiResponse(code = 404, message = "Service not found"),
				@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class) })
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Student> deleteStudent(@ApiParam(value = "Id of the Student") @PathVariable("id") Long id) {
			log.info("Deleting Student with id{}", id);


			if (id < 0 || id == null) {
				log.error("Unable to delete, Student with id{} not found", id);
				throw new InvalidIdException("Invalid Id");
			}
			Student student = studentService.deleteStudent(id);

			if (student == null) {
				log.error("Unable to update, Student with id{} not found", id);
				throw new StudentNotFoundException("Student not found");
			}
			return ResponseEntity.ok(student);
		}

		@ApiOperation(value = "Find a Student search by DNI")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
				@ApiResponse(code = 404, message = "Service not found"),
				@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class) })
		@GetMapping("/find/dni/{dni}")
		public ResponseEntity<Student> findByDni(@ApiParam(value = "Dni of the Student") @PathVariable("dni") String dni) {
			log.info("Fetching a Student with dni{}", dni);

			Student student = studentService.findByDni(dni);

			if (dni.isEmpty() || (dni == null)) {
				log.error("Unable to find dni", dni);
				throw new StudentNotFoundException("Student not found");
			}

			return ResponseEntity.ok(student);
		}

		@ApiOperation(value = "Find a Student search by Surname")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
								@ApiResponse(code = 404, message = "Service not found"),
								@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class)
		})
		@GetMapping("/surname/{surname}")
		public  ResponseEntity<List<Student>> findBySurname(@ApiParam(value = "Surname of the Student") @PathVariable("surname") String surname) {
			log.info("Fetching a Student with surname{}", surname);

			List<Student> students = studentService.findBySurname(surname);

			if (surname == null) {
				log.error("Unable to find surname{}",surname);
				return ResponseEntity.noContent().build();
			}

			return ResponseEntity.ok(students);
		}

		@ApiOperation(value = "Show a list of all Courses")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
				@ApiResponse(code = 404, message = "Service not found"),
				@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class) })
		@GetMapping("/courses")
		public ResponseEntity<List<Course>> getAllCourses() {
			log.info("Fetching list of Courses");
			List<Course> courses = courseService.getAllCourses();

			if (courses.isEmpty()) {
				throw new CoursesNotFoundException("List of Courses not found");
			}

			return ResponseEntity.ok(courses);
		}

		@ApiOperation(value = "Find a Student search by Course Id")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
				@ApiResponse(code = 404, message = "Service not found"),
				@ApiResponse(code = 200, message = "Successful retrieval", response = Student.class) })
		@GetMapping("/courses/search/{id}")
		public ResponseEntity<List<Student>> findByCourse(
				@ApiParam(value = "Id of the Course") @PathVariable("id") Course id) {
			log.info("Fetching a Student search by his Course");

			if (id == null){
				throw new InvalidIdException("Invalid Id");
			}

			List<Student> students = studentService.findByCourse(id);

			if (students.isEmpty()) {
				throw new ListStudentsNotFoundException("Students not found");
			}

			return ResponseEntity.ok(students);
		}

	}
