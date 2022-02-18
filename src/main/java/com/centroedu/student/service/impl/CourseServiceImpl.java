package com.centroedu.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroedu.student.entities.Course;
import com.centroedu.student.repositories.CourseRepository;
import com.centroedu.student.service.CourseService;

/**
 * Class that implements the Service for Course Entity
 * @author Paulo De Jesus
 * @version 1.0
 * @see CourseService
 */
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;
	
	/**
	 * Method that retrieves a list of all the existing Courses
	 * @return all the existing Courses
	 */
	@Override
	public List<Course> getAllCursos() {
		
		return courseRepository.findAll();
	}

	/**
	 * Method that retrieves a Course search by his Id
	 * @param id
	 * @return a Course 
	 */
	@Override
	public Course findById(Long id) {
		
		return courseRepository.getById(id);
	}

}
