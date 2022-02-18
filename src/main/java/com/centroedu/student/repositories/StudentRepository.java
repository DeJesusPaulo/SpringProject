package com.centroedu.student.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.centroedu.student.entities.Course;
import com.centroedu.student.entities.Student;

/**
 * Repository class for Student Entity
 * @author Paulo De Jesus
 * @version 1.0
 * 
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	
	/**
	 * Method that retrieves a list of Students search by Course
	 * @param id
	 * @return List of Students
	 */
	public List<Student> findByCourse(Course id);
	
	/**
	 * Method that retrieves a Student search by his DNI number
	 * @param dni
	 * @return a Student
	 */
	
	public Student findByDni(int dni);
	
	/**
	 * Method that retrieves a Student or Students search by his surname
	 * @param surname
	 * @return a Student or a list of Students
	 */
	public List<Student> findBySurname(String surname);

}
