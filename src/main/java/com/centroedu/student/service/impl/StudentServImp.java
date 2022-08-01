package com.centroedu.student.service.impl;

import java.util.List;

import com.centroedu.student.utils.exceptions.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroedu.student.entities.Course;
import com.centroedu.student.entities.Student;
import com.centroedu.student.repositories.CourseRepository;
import com.centroedu.student.repositories.StudentRepository;
import com.centroedu.student.service.StudentService;



/**
 * Class that implements the Service for Student Entity
 * @author Paulo De Jesus
 * @version 1.0
 * @see StudentService
 */

@Service
public class StudentServImp implements StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;
	
	/**
	 * Method that retrieves a list of all the Students
	 * @return a list of Students
	 */
	@Override
	public List<Student> listAllStudents() {
		
		return studentRepository.findAll();
	}

	/**
	 * Method that retrieves a Student search by his Id
	 * @param id
	 * @return a Student
	 */
	@Override
	public Student getStudent(Long id) {
		
		return studentRepository.findById(id).orElse(null);
	}

	/**
	 * Method that creates a Student
	 * @param student
	 * @return the created Student
	 */
	@Override
	public Student  createStudent(Student student) throws StudentNotFoundException {

		if (student == null) { return null; }
			
		student.setStatus("Student created");
		
		return studentRepository.save(student);
	}
	
	/**
	 * Method that creates a Student
	 * @param student
	 * @return the updated Student
	 */
	@Override
	public Student updateStudent(Student student) {
		
		Student student1 = getStudent(student.getId());
		if (student1 == null) { return null; }
			
		student1.setName(student.getName());
		student1.setSurname(student.getSurname());
		student1.setDni(student.getDni());
		student1.setEmail(student.getEmail());
		student1.setCourse(student.getCourse());
		student1.setStatus("Student updated");
		
		return studentRepository.save(student1);
	}

	/**
	 * Method that deletes a Student 
	 * @param id
	 * @return the confirmation of the deleted Student
	 */
	@Override
	public Student deleteStudent(Long id) {
		Student student = getStudent(id);
		if (student == null) { return null; }		
		
		student.setStatus("Student deleted");
		
		return studentRepository.save(student);
	}
	
	/**
	 * Method that retrieves a Student or Students search by his surname
	 * @param surname
	 * @return a Student or Students
	 */
	@Override
	public List<Student> findBySurname(String surname) {
		
		return studentRepository.findBySurname(surname);
	}


	/**
	 * Method that retrieves the list of Students in a Course
	 * @param id
	 * @return all the Students in a Course
	 */
	@Override
	public List<Student> findByCourse(Course id) {	
		
		return studentRepository.findByCourse(id);
		
	}

	/**
	 * Method that retrieves a Student search by his Dni number
	 * @param dni
	 * @return
	 */
	@Override
	public Student findByDni(String dni) {
				
		return studentRepository.findByDni(dni);
	}

}
	
