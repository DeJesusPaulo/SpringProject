package com.centroedu.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroedu.student.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
