package com.nit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nit.entity.Student;
import com.nit.exception.CustomException;
import com.nit.response.GlobalResponse;
import com.nit.service.StudentService;


@RestController
@RequestMapping("/stud")
public class StudentController {

	@Autowired
	private StudentService service;


	@GetMapping("/view")
	public String   showData(){
		try {

			return "ok";

		}
		catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}


	@PostMapping("/add")
	public GlobalResponse   addData(@Valid @RequestBody Student student){
		try {
			service.addStudent(student);
			return new GlobalResponse("succes" , "Student added successfully" , 200);

		}
		catch(Exception e) {
			throw new CustomException(e.getMessage());
		}

	}



	@GetMapping("/get/{id}")
	public Student  viewDetails(@PathVariable Integer id) {
		try {
			return this.service.getDetails(id);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}



	@GetMapping("/getAll")
	public List<Student>  viewAllDetails() {
		try {
			return service.getAllDetails();
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@PutMapping("/status/{id}")
	public GlobalResponse changeStatus(@PathVariable Integer id) {
		try {

			return this.service.changeStatus(id);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}



	@PutMapping("/update")
	public GlobalResponse updateData(@RequestBody Student student) {
		try {

			return this.service.updateData( student);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@PutMapping("/delete/{id}")
	public GlobalResponse putCategoryMulDelete(@PathVariable Integer id) {

		try {
			return this.service.deleteStudent(id);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}	
	}

}
