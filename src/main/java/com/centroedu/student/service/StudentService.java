package com.centroedu.student.service;

import java.util.List;

import com.centroedu.student.entities.Course;
import com.centroedu.student.entities.Student;

/**
 * Service Class for Student Entity
 * @author Paulo De Jesus
 * @version 1.0
 */
public interface StudentService {
	
	/**
	 * Method that retrieves a list of all the Students
	 * @return a list of Students
	 */
	public List<Student> listAllStudents();
	
	/**
	 * Method that retrieves a Student search by his Id
	 * @param id
	 * @return a Student
	 */
	public Student getStudent(Long id);
	
	/**
	 * Method that creates a Student
	 * @param alumno
	 * @return the created Student
	 */
	public Student createStudent(Student alumno);
	
	/**
	 * Method that deletes a Student 
	 * @param id
	 * @return the confirmation of the deleted Student
	 */
	public Student deleteStudent(Long id);
	
	/**
	 * Method that retrieves the list of Students in a Course
	 * @param id
	 * @return all the Students in a Course
	 */
	public List<Student> findByCourse(Course id);
	
	/**
	 * Method that retrieves a Student search by his Dni number
	 * @param dni
	 * @return
	 */
	public Student findByDni(int dni);
	
	/**
	 * Method that retrieves a Student or Students search by his surname
	 * @param surname
	 * @return a Student or Students
	 */
	public List<Student> findBySurname(String surname);

}
