package com.centroedu.student.service;

import java.util.List;

/**
 * Service Class for Course Entity
 * @author Paulo De Jesus
 * @version 22/01/2022
 */
import com.centroedu.student.entities.Course;

/**
 * Service Class for Course Entity
 * @author Paulo De Jesus
 * @version 1.0
 */
public interface CourseService {
	
	/**
	 * Method that retrieves a list of all the existing Courses
	 * @return all the existing Courses
	 */
	public List<Course> getAllCourses();

	/**
	 * Method that retrieves a Course search by his Id
	 * @param id
	 * @return a Course 
	 */
	public Course findById(Long id);

}
