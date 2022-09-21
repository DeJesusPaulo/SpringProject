	package com.centroedu.student.controller;

	import java.util.List;


	import com.centroedu.student.utils.exceptions.*;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.*;

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
		public ResponseEntity<List<Student>> listStudents(
				@RequestParam(value = "pageNumber", defaultValue = "0") int offSet,
				@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

			List<Student> students = studentService.listAllStudents(offSet, pageSize);
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
				@ApiParam(value = "A Student send as a parameter") @RequestBody @Valid Student student, BindingResult result){
			log.info("Creating Student: {}", student);

			if(result.hasErrors()){
				throw new DataValidationException("Invalid Data", result);
			}

			Student newStudent = studentService.createStudent(student);

			if (student == null) {
				throw new StudentNotFoundException("Student not found");
			}

			return ResponseEntity.ok(newStudent);
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


			if (id < 0) {
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

			if (dni.isEmpty()) {
				log.error("Unable to find dni");
				throw new DniNotFoundException("DNI not found");
			}

			Student student = studentService.findByDni(dni);

			if (student == null) {
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

			if (surname == null) {
				log.error("Unable to find surname{}",surname);
				throw new SurnameNotFound("Surname of the Student not found");
			}

			List<Student> students = studentService.findBySurname(surname);

			if (students.isEmpty()) {
				throw new StudentNotFoundException("Student not found");
			}

			return ResponseEntity.ok(students);
		}

		@ApiOperation(value = "Retrieve a Course search by his ID")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
				@ApiResponse(code = 404, message = "Service not found"),
				@ApiResponse(code = 200, message = "Successful retrieval", response = Course.class) })
		@GetMapping("/course/{id}")
		public ResponseEntity<Course> getCourse(@ApiParam(value = "Id of the course") Long id){

			if (id < 0 ) {
				throw new InvalidIdException("Invalid Id");
			}
			Course course = courseService.findById(id);

			if (course == null) {
				log.error("Course with id {} not found");
				throw new CourseNotFoundException("Course not found");
			}

			return ResponseEntity.ok(course);
		}

		@ApiOperation(value = "Show a list of all Courses")
		@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
				@ApiResponse(code = 404, message = "Service not found"),
				@ApiResponse(code = 200, message = "Successful retrieval", response = Course.class) })
		@GetMapping("/courses")
		public ResponseEntity<List<Course>> getAllCourses() {
			log.info("Fetching list of Courses");
			List<Course> courses = courseService.getAllCourses();

			if (courses.isEmpty()) {
				throw new CoursesNotFoundException("List of Courses not found");
			}

			return ResponseEntity.ok(courses);
		}

	}
