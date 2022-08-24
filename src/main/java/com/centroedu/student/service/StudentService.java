package com.centroedu.student.service;

import java.util.List;


import com.centroedu.student.entities.Course;
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
	public List<Student> listAllStudents(int offSet, int pageSize);
	
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
	public Student createStudent(Student alumno) throws StudentNotFoundException;
	
	/**
	 * Method that updates a Student
	 * @param student
	 * @return the updated Student
	 */
	public Student updateStudent(Student student);
	
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
	public Student findByDni(String dni);
	
	/**
	 * Method that retrieves a Student or Students search by his surname
	 * @param surname
	 * @return a Student or Students
	 */
	public List<Student> findBySurname(String surname);

}
