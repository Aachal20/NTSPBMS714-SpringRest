package com.nit.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.And;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.nit.entity.Student;
import com.nit.exception.CustomException;
import com.nit.repository.StudRepo;
import com.nit.response.GlobalResponse;

@Service
public class StudentService {

	@Autowired
	private SequenceGenerator sequenceGenerator;

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private StudRepo repo;

	public GlobalResponse addStudent(Student student) {
		try {
			student.setId(sequenceGenerator.getCurrentSequenceNumber(Student.SEQUENCE_NAME));
			repo.save(student);
			return new GlobalResponse("success", "Student data added successfully", 200);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	public Student getDetailsById(Integer id) {
		try {

			Optional<Student> student = repo.findById(id); // here we directly return student obj

			if (student.isPresent()) {
				if (student.get().getStatus().equals("active")) {
					return student.get();
				} // if
				else if (student.get().getStatus().equals("delete")) {
					throw new CustomException("Student is deleted for thid ID");
				} else {
					throw new CustomException("This Student Status Is Inactive");
				}

			} //

			else {
				throw new CustomException("Student  not found");
			}
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	/*if(student.getStatus()) {                                                    //if true then i dont want to display deleted data
					throw new CustomException("Student deleted for this ID");
				}
				else {
					return student;                                                                     //for this id we have a data and we give that data /return the data
				}*/
	/*List<Status> status  = repo.findByActive(true);
				if(status.stream().filter(Inactive) !=true)){
					return student;       
				}
				else {
					throw new CustomException("Student deleted for this ID");
				}*/

	// @SuppressWarnings("unlikely-arg-type"
	
	public List<Student> fetchStudentByStatus() {
		//1st way
		/*	try {
				List<Student> list = repo.findStudentByStatus();
				list.forEach(System.out::println);
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				throw new CustomException(e.getMessage());
			}*/
		
		//2nd way
		 List<Student> list = repo.findAll();
		
		 repo.findAll().stream().filter(e->e.getStatus().equals("active")).collect(Collectors.toList());
		 System.out.println(" In Side getAllDetails");
		 //List<Student> list = repo.findByStatus("active");
		List<Student> list1 = repo.findStudentByStatus("active", "Inactive");
		return list1;
		//3rd way
		 
		/*	if (student.getStatus().equals("active") ) {
					student.setStatus("InActive");
				student.setStatus("delete");
					repo.save(student);
		
				List<Student> list = repo.searchStudentByStatusIn("active", "Inactive" );
				list.forEach(System.out::println);
				//System.out.println("List of Data :: " + list);
				// return
					list.stream().filter(s->s.getStatus().equals("active") ||  s.getStatus().equals("Inactive")).collect(Collectors.toList());
		
				//return list;
				} catch (Exception e) {
		e.printStackTrace();
		throw new CustomException(e.getMessage());
		}*/
		// return null;

	}

	public GlobalResponse changeStatus(Integer id) {
		try {

			if (repo.existsById(id)) {
				Optional<Student> data = repo.findById(id);
				Student entity = data.get();
				String status = null;
				if (entity.getStatus().equals("active")) {
					status = "Inactive";
				} else {
					status = "active";
				}
				entity.setStatus(status);

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
			Optional<Student> optional = repo.findById(student.getId());
			if (optional.isPresent()) {
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
			Optional<Student> data = repo.findById(id);
			if (data.isPresent()) {
				Student student = data.get();
				/*	if (student.getStatus().equals("active")) {
						student.setStatus("InActive");
					student.setStatus("delete");
						repo.save(student);
						return new GlobalResponse("success ", "student  deleted", 200);
					} else {
						return new GlobalResponse("Bad request!!", "student  allReady deleted", 400);
					}*/
				//
				student.setStatus("delete");
				repo.save(student);
				return new GlobalResponse("success ", "student  deleted", 200);
			} else {
				return new GlobalResponse("Bad request", "student  does not exist", 400);
			}
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

	/*	public String getAllCount() {
			try {
				// List<Student> list = repo.findAll();
				// List<Student> collect =
				// repo.findAll().stream()..filter(e->e.getStatus().equals("active")).collect(Collectors.toList());
				System.out.println(" In Side getAllCount");
				List<Student> list = repo.findByStatus("active");
				List<Student> list1 = repo.findByStatus("Inactive");
	
				long countActive = list.stream().count();
				long countInActive = list1.stream().count();
				System.out.println("List of Data :: " + list);
				// return list.stream().filter(s->s.getStatus().equals("active")).count();
				return "Active Record :: " + countActive + "   InActive Record ::" + countInActive;
			} catch (Exception e) {
				e.printStackTrace();
				throw new CustomException(e.getMessage());
			}
		}*/

	/*public List<Student> getAllDetails() {
		try {
			Student stud  = new Student();
			Query query = new Query();
			query.addCriteria(Criteria.where("status").is("active"));
			List<Student> list = repo.searchStudentByStatusIn("active", "Inactive");
			list.forEach(System.out::println);
			// System.out.println("List of Data :: " + list);
			// return
			list.stream().filter(s -> s.getStatus().equals("active") || s.getStatus().equals("Inactive"))
					.collect(Collectors.toList());
	
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}
		// return null;
	}*/
}
