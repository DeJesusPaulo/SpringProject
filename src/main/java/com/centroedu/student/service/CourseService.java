package com.centroedu.student.service;

import java.util.List;

import com.centroedu.student.entities.Course;

public interface CourseService {

	public List<Course> getAllCursos();

	public Course findById(Long id);

}
