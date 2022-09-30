package com.centroedu.student;

import com.centroedu.student.entities.Course;
import com.centroedu.student.entities.Student;
import com.centroedu.student.repositories.CourseRepository;
import com.centroedu.student.repositories.StudentRepository;
import com.centroedu.student.service.impl.StudentServImp;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Spy StudentRepository studentRepo;
    @Mock CourseRepository courseRepository;

    @InjectMocks StudentServImp studentServImp;

    @MockBean Course course;
    @MockBean Student student;


    @BeforeEach
    void setUp() {

        course = new Course(1L,"History","Night","Monday");
        courseRepository.save(course);

        student = new Student(1L,"Jorge","Ovando",
                "21780445","jorgeo@gmail.com","created",course);
        studentRepo.save(student);

        // studentServImp = new StudentServImp(studentRepo);

    }


    @Test
    void givenStudentId_whenSearchById_thenReturnStudent() {

        studentServImp.getStudent(student.getId());

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

        verify(studentRepo).findById(idCaptor.capture());

        Long idCaptured = idCaptor.getValue();

        assertThat(idCaptured).isEqualTo(student.getId());

    }

    @Test
    void givenStudent_whenCreateStudent_thenReturnNewStudent(){

        studentServImp.createStudent(student);

        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepo,times(2)).save(studentCaptor.capture());

        Student studentCaptured = studentCaptor.getValue();

        assertThat(studentCaptured).isEqualTo(student);
    }

    @Test
    void givenAnyStudentField_whenUpdateStudent_thenReturnStudentUpdated(){

        when(studentRepo.findById(1L)).thenReturn(Optional.ofNullable(student));
        String newEmail = "jorge.ovando@gmail.com";
        student.setEmail(newEmail);
        studentServImp.updateStudent(student);

        verify(studentRepo,times(2)).save(student);

        assertThat(student.getEmail()).isEqualTo(newEmail);
    }

    @Test
    void givenStudentId_whenDeleteStudent_thenReturnDeletedStudentStatus(){

        when(studentRepo.findById(1L)).thenReturn(Optional.ofNullable(student));
        studentServImp.deleteStudent(student.getId());

        assertThat(student.getStatus()).isEqualTo("Student deleted");

    }

    @Test
    void givenStudentSurname_whenSearchBySurname_thenReturnStudent(){

        studentServImp.findBySurname(student.getSurname());

        ArgumentCaptor<String> surnameCaptor = ArgumentCaptor.forClass(String.class);

        verify(studentRepo).findBySurname(surnameCaptor.capture());

        String surnameCaptured= surnameCaptor.getValue();

        assertThat(surnameCaptured).isEqualTo(student.getSurname());

    }

    @Test
    void givenStudentDNI_whenSearchByDNI_thenReturnStudent(){

        studentServImp.findByDni(student.getDni());

        ArgumentCaptor<String> dniCaptor = ArgumentCaptor.forClass(String.class);

        verify(studentRepo).findByDni(dniCaptor.capture());

        String dniCaptured = dniCaptor.getValue();

        assertThat(dniCaptured).isEqualTo(student.getDni());

    }
}
