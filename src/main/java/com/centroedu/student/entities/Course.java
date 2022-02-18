package com.centroedu.student.entities;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class Course with his attributes and methods
 * @author Paulo De Jesus
 * @version 1.0
 */

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "courses")
public class Course {

	/**
	 * Id of the Course
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Course Name
	 */
	@Column(name = "name", nullable = false)
	private String name;
	/**
	 * Shift of the Course
	 */
	
	@Column(name = "shift", nullable = false)
	private String shift;
	
	/**
	 * Days in which the Course is dictated
	 */
	@Column(name = "days", nullable = false)
	private String days;

}
