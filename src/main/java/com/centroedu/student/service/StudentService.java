package com.centroedu.student.service;

import java.util.List;



import com.centroedu.student.entities.Student;
import com.centroedu.student.utils.exceptions.StudentNotFoundException;

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
	 List<Student> listAllStudents(int offSet, int pageSize);
	
	/**
	 * Method that retrieves a Student search by his Id
	 * @param id the Student
	 * @return a Student
	 */
	 Student getStudent(Long id);
	
	/**
	 * Method that creates a Student
	 * @param student set as a parameter
	 * @return the created Student
	 */
	 Student createStudent(Student student) throws StudentNotFoundException;
	
	/**
	 * Method that updates a Student
	 * @param  student set as a parameter
	 * @return the updated Student
	 */
	 Student updateStudent(Student student);
	
	/**
	 * Method that deletes a Student 
	 * @param id of the Student
	 * @return the confirmation of the deleted Student
	 */
	 Student deleteStudent(Long id);

	
	/**
	 * Method that retrieves a Student search by his Dni number
	 * @param dni of the Student
	 * @return a Student search by his Dni number
	 */
	 Student findByDni(String dni);
	
	/**
	 * Method that retrieves a Student or Students search by his surname
	 * @param surname of the Student
	 * @return a Student or a list Students
	 */
	 List<Student> findBySurname(String surname);

}
