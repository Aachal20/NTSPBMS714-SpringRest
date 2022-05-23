package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Employee;
import com.nt.exception.CustomException;
import com.nt.response.GlobalResponse;
import com.nt.service.EmployeeService;


@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private  EmployeeService  service;

	
	
	@GetMapping("/test")
	public String linkTest()
	{
		try {
			return "Ok";
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@PostMapping("/add")
	public GlobalResponse addEmployee(@RequestBody Employee employee){
		try {
			return service.registerEmployee(employee);
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@GetMapping("/view/{empId}")
	public  Employee viewEmployeeDetails(@PathVariable Integer empId) {
		try {
			return service. employeeDetalis(empId);
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}

	}


	@GetMapping("/viewAll")
	public  ResponseEntity<?> viewAllEmployeeDetails() {
		try {

			List<Employee> list= service.AllemployeeDetalis();
			return new ResponseEntity<List<Employee>>(list,HttpStatus.OK);

		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

	//error
	@PutMapping("/update")
	public  GlobalResponse updateData(@RequestBody Employee employee) {
		
		//String msg=service.updateEmployeeDetails(employee);
		//return new ResponseEntity<String>(msg,HttpStatus.OK);
		return this.service.updateEmployeeDetails(employee);
		/*	try {
				//String msg=service.updateEmployeeDetails(employee);
				//return new ResponseEntity<String>(msg,HttpStatus.OK);
				return this.service.updateEmployeeDetails(employee);
			} catch (Exception e) {
				throw new CustomException(e.getMessage());
			}*/

	}
	
	@PutMapping("/delete/{id}")
	public  GlobalResponse  removeEmployee(@PathVariable("id") Integer empId){
		try {
		//use service
		return this.service.deleteEmployee(empId);

		}
		catch(Exception e) {
			throw new CustomException(e.getMessage());
		}


	}

}//class
