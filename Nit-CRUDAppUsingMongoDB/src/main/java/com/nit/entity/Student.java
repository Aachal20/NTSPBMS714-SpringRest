package com.nit.entity;

import java.io.Serializable;
import java.util.ArrayList;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="my_table_report")
public class Student implements Serializable{

	@Transient                                     
	public static final String SEQUENCE_NAME = "tbl_product_sequence";

	@Id
	private Integer  id;

	@NotEmpty(message = "First Name  Required")
	@Field(name="first_Name")
	private String firstName;


	@NotEmpty(message = "Last Name  Required")
	@Field(name="last_name")
	private String lastName;

	//@Min(value=18, message="must be equal or greater than 18")  
	// @Max(value=45, message="must be equal or less than 45")  
	@Field(name="student_age")
	private Integer age;

	//@Pattern(regexp = "^(true|false)$", message = "restartable field allowed input: true or false")
	@Field(name="is_active")
	private Boolean isActive;

	@Field(name="student_address")
	private ArrayList<Address> address;                         //add list of address 
	//private Address address;                                           //add only one object
}
