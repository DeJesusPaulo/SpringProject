package com.centroedu.student.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroedu.student.entities.Course;
import com.centroedu.student.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public List<Student> findByCourse(Course id);
	
	public Student findByDni(int dni);
	
	public List<Student> findBySurname(String surname);

}
