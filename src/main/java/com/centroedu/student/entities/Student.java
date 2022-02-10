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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor 
@Builder
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	@NotEmpty(message = "Debe ingresar un nombre")
	private String name;
	
	@Column(name = "surname", nullable = false)
	@NotEmpty(message = "Debe ingresar un apellido")
	private String surname;
	
	@Column(name = "dni", nullable = false, unique = true, length = 10)
	@Size(max = 10, min = 10, message = "Cantidad de cifras incorrecta")
	@NotEmpty(message = "Debe ingresar el dni")
	private int dni;
	
	@Column(name = "email",nullable = false, unique = true)
	@Email(message = "Forma incorrecta de email")
	private String email;
	
	@Column(name = "status")
	private String status;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Course curso;
	
	
	
	

}
