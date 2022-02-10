package com.centroedu.student.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroedu.student.entities.Student;
import com.centroedu.student.entities.Course;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public List<Student> findByCurso(Course curso);
}
