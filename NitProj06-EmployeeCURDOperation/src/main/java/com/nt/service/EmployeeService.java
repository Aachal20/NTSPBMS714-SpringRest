package com.nt.service;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBElement.GlobalScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;


import com.nt.entity.Employee;
import com.nt.exception.CustomException;
import com.nt.repo.IEmployeRepo;
import com.nt.response.GlobalResponse;


@Service("employeeService")
public class EmployeeService  {

	@Autowired
	private IEmployeRepo   empRepo;

	public GlobalResponse  registerEmployee(Employee  employe) {
		try {
			empRepo.save(employe);
			return new GlobalResponse("Success!!",  "Employee added successfully",  200);
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}


	public Employee  employeeDetalis(Integer empId) {
		try {
			if(empRepo.existsById(empId)) {
				return empRepo.findById(empId).get();
			}else 
				throw new CustomException("Employee  Not Found for this ID");
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}


	public List<Employee> AllemployeeDetalis() {
		try {
			List<Employee> list=empRepo.findAll();
			list.sort((t1,t2)->t1.getEmpId().compareTo(t2.getEmpId()));
			return list;
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	/*
	public ResponseEntity<String> updateEmployeeDetails(Employee employee) {
		try {
			Optional<Employee> optional=empRepo.findById(employee.getempId());
			if(optional.isPresent()) {
				empRepo.save(employee);  // save(-) performs either save obj or update obj operation
				return  ResponseEntity<String>(" Employee is updated" ,HttpStatus.ok);
			}
			else {
				throw new CustomException(employee.getempId()+" Employee  not found ");    	
			}
			catch(Exception e) {
				throw new CustomException(e.getMessage());
			}

		}
	 */



	public GlobalResponse updateEmployeeDetails(Employee employee)  {
		Optional<Employee> optional=empRepo.findById(employee.getEmpId());
		if(optional.isPresent()) {
			empRepo.save(employee);  // save(-) performs either save obj or update obj operation
			return new GlobalResponse("Success", "Employee updated successfully", 200);
		}
		else {
			throw new CustomException(" Employee  not found ");    	
		}

	}

	public GlobalResponse deleteEmployee(Integer empId)  {

		try {
			if (empRepo.existsById(empId)) {
				Boolean isActive = false;
				Optional<Employee> Data = empRepo.findById(empId);
				Employee Entity = Data.get();
				if (Entity.getIsAtive().equals(false)) {
					isActive= true;
				} else {
					return new GlobalResponse("Bad request!!", "Employee  AllReady deleted", 400);
				}
				Entity.setIsAtive(isActive);


				empRepo.save(Entity);
				return new GlobalResponse("Success", "Deleted successfully", 200);
			} else {
				return new GlobalResponse("Bad request", "employe does not exist", 400);
			}
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

}






