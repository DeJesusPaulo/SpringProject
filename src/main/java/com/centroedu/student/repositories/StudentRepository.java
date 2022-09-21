package com.centroedu.student.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.centroedu.student.entities.Student;

/**
 * Repository class for Student Entity
 * @author Paulo De Jesus
 * @version 1.0
 * 
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	

	
	/**
	 * Method that retrieves a Student search by his DNI number
	 * @param dni of the Student
	 * @return a Student
	 */
	
	 Student findByDni(String dni);
	
	/**
	 * Method that retrieves a Student or Students search by his surname
	 * @param surname of the Student
	 * @return a Student or a list of Students
	 */
	 List<Student> findBySurname(String surname);

}
