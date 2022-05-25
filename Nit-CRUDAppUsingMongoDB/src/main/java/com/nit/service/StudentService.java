package com.nit.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.entity.Student;
import com.nit.exception.CustomException;
import com.nit.repository.studRepo;
import com.nit.response.GlobalResponse;

@Service
public class StudentService {


	@Autowired
	private SequenceGenerator sequenceGenerator;

	@Autowired
	private studRepo repo;

	public GlobalResponse addStudent(Student  student) {
		try {
			student.setId(sequenceGenerator.getCurrentSequenceNumber(Student.SEQUENCE_NAME));
			repo.save(student);
			return new GlobalResponse("success" ,"Student data added successfully" , 200);
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}


	public Student  getDetails(Integer id) {
		try {
			if (repo.existsById(id)) {
				Student student = repo.findById(id).get();                    //here we directly return student obj data..but first i need to check isActive status is true or false..if it is false then only give data(student data  is not deleted)
				if(student.getIsActive()) {                                                    //if true then i dont want to display deleted data
					throw new CustomException("Student deleted for this ID");
				}
				else {
					return student;                                                                     //for this id we have a data and we give that data /return the data
				}
			} else {
				throw new CustomException("Student  not found");
			}
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}



	public List<Student>  getAllDetails() {
		try {
			List<Student>  list = repo.findByIsActive(false);
			return list;
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}


	public GlobalResponse changeStatus(Integer id) {
		try {

			if (repo.existsById(id)) {
				Boolean status;
				Optional<Student> data = repo.findById(id);
				Student entity = data.get();
				if (entity.getIsActive().equals(true)) {
					status = false;
				} else {
					status = true;
				}
				entity.setIsActive(status);


				repo.save(entity);
				return new GlobalResponse("Success", "Status change successfully", 200);
			} else {
				return new GlobalResponse("Bad request", "student  does not exist", 400);
			}
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}


	public GlobalResponse updateData(Student student) {
		try {
			Optional<Student> optional  =  repo.findById(student.getId());
			if(optional.isPresent()) {
				repo.save(student);
				return new GlobalResponse("Success", "Student data updated successfully", 200);
			} else {
				return new GlobalResponse("Bad request", "student  does not exist", 400);
			}

		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	public GlobalResponse deleteStudent(Integer id) {

		try {

			if (repo.existsById(id)) {
				Boolean isActive = false;
				Optional<Student> data = repo.findById(id);
				Student stud  = data.get();
				if (stud.getIsActive().equals(false)) {
					//if (stud.getIsActive()==false) {
					isActive = true;
					stud.setIsActive(isActive);
				} else {
					return new GlobalResponse("Bad request!!", "student  allReady deleted", 400);
				}

				repo.save(stud);
				return new GlobalResponse("Success", "Deleted successfully", 200);
			} else {
				return new GlobalResponse("Bad request", "student  does not exist", 400);
			}
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

}
