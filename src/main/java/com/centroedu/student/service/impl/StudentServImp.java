package com.centroedu.student.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroedu.student.entities.Course;
import com.centroedu.student.entities.Student;
import com.centroedu.student.repositories.CourseRepository;
import com.centroedu.student.repositories.StudentRepository;
import com.centroedu.student.service.StudentService;

@Service
public class StudentServImp implements StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	CourseRepository courseRepository;

	@Override
	public List<Student> listAllStudents() {
		
		return studentRepository.findAll();
	}

	@Override
	public Student getStudent(Long id) {
		
		return studentRepository.findById(id).orElse(null);
	}

	@Override
	public Student createStudent(Student student) {
		Student student1 = getStudent(student.getId());
		if (student1 == null) { return null; }
			
		student1.setName(student.getName());
		student1.setSurname(student.getSurname());
		student1.setDni(student.getDni());
		student1.setEmail(student.getEmail());
		student1.setCourse(student.getCourse());
		student1.setStatus("Student created");
		
		return studentRepository.save(student1);
		
	}

	@Override
	public Student deleteStudent(Long id) {
		Student student = getStudent(id);
		if (student == null) { return null; }		
		
		student.setStatus("Student deleted");
		
		return  studentRepository.save(student);
	}

	@Override
	public List<Student> findByCourse(Course id) {	
		
		return studentRepository.findByCourse(id);
		
	}
}
