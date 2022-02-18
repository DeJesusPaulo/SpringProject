package com.centroedu.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroedu.student.entities.Course;

/**
 * Repository Class for Entity Course
 * @author Paulo De Jesus
 * @version 1.0
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

}
