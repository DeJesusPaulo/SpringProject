package com.centroedu.student;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.centroedu.student.entities.Course;
import com.centroedu.student.entities.Student;
import com.centroedu.student.repositories.StudentRepository;

@DataJpaTest
public class StudentRepositoryMockTest {

	@Autowired
	StudentRepository studentRepository;

	@Test
	@DisplayName("itShouldCreateAStudent")
	public void itShouldCreateAStudent() {
		Student student01 = Student.builder()
				.id(11L)
				.name("Fred")
				.surname("Sancho")
				.dni("25649782")
				.email("mortelale@gmail.com")
				.course(Course.builder().id(2L).build())
				.build();

		studentRepository.save(student01);
		
		Assertions.assertThat(student01.getId().equals(2L));
		
	}
	
	@Test
	@DisplayName("itShouldDeleteAStudent")
	public void itShouldDeleteAStudent(Long id) {
		
		studentRepository.deleteById(id);
		
		List<Student> founds = studentRepository.findAll();
		
		Assertions.assertThat(founds.size()).isEqualTo(19);
	}
}
