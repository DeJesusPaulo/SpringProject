package com.centroedu.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroedu.student.entities.Course;
import com.centroedu.student.repositories.CourseRepository;
import com.centroedu.student.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;
	
	@Override
	public List<Course> getAllCursos() {
		
		return courseRepository.findAll();
	}

	@Override
	public Course findById(Long id) {
		
		return courseRepository.getById(id);
	}

}
