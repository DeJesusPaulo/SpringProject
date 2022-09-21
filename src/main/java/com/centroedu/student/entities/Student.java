package com.centroedu.student.entities;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class Student with his attributes and methods
 * 
 * @author Paulo De Jesus
 * @version 1.0
 */

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends BaseEntity{

	/**
	 * ID of the Student
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Name of the Student
	 */
	@Column(name = "name", nullable = false)
	@NotEmpty(message = "Debe ingresar un nombre")
	private String name;

	/**
	 * Surname of the Student
	 */
	@Column(name = "surname", nullable = false)
	@NotEmpty(message = "Debe ingresar un apellido")
	private String surname;

	/**
	 * Dni number of the Student
	 */
	@Column(name = "dni", nullable = false, unique = true)
	@Pattern(regexp = "\\d{8}")
	private String dni;

	/**
	 * E-mail of the Student
	 */
	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "Forma incorrecta de email")
	private String email;

	@Column(name = "status")
	private String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_Id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Course course;

}
