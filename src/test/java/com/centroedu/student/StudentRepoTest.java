package com.centroedu.student;

import com.centroedu.student.entities.Course;
import com.centroedu.student.entities.Student;
import com.centroedu.student.repositories.CourseRepository;
import com.centroedu.student.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class StudentRepoTest {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;


    @Test
    void itShouldFindAStudentSearchByDni(){
        Course course = new Course(1L,"History","Night","Monday");
        courseRepository.save(course);

       Student student = new Student(1L,"Jorge","Ovando",
               "21780445","jorgeo@gmail.com","created",course);

       studentRepository.save(student);

    Student result = studentRepository.findByDni(student.getDni());

    assertThat(result.getDni()).isEqualTo("21780445");
    }

    @Test
    void itShouldFindAStudentBySurname(){

        Course course = new Course(1L,"History","Night","Monday");
        courseRepository.save(course);

        Student student = new Student(1L,"Jorge","Ovando",
                "21780445","jorgeo@gmail.com","created",course);
        studentRepository.save(student);

        List<Student> founds = studentRepository.findBySurname(student.getSurname());

        assertEquals(1, founds.size());
    }


}
