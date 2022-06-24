package com.nit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.nit.entity.Student;
import com.nit.exception.CustomException;
import com.nit.repository.StudRepo;
import com.nit.response.GlobalResponse;

@Service
public class StudentService {

	@Autowired
	private SequenceGenerator sequenceGenerator;

	@Autowired
	private StudRepo repo;

	@Autowired
	private MongoOperations mongo;

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

	public List<Student> fetchStudentByStatus() {

		final String activeStatus = "active";
		final String inActiveStatus = "Inactive";
		//create a query class
		//Student stud = new Student();

		List<Student>  filteractive = repo.findByStatus("active");
		List<Student>  filterInactive = repo.findByStatus("Inactive");
		System.out.println(filteractive);
		System.out.println(filterInactive);


		// we can then pass the filters to the findAll() method
		//  List<Student> list =  repo.findAll(filteractive.addAll(filterInactive));
		return filterInactive;

		/*	List<Student> list = repo.findAll();

				repo.findAll().stream().filter(e->e.getStatus().equals("active")).collect(Collectors.toList());
				System.out.println(" In Side getAllDetails");
				//List<Student> list = repo.findByStatus("active");
				List<Student> list1 = repo.findStudentByStatus("active", "Inactive");
				return list1;
				//3rd way

					if (student.getStatus().equals("active") ) {
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
				}
				// return null;
		 */	
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

		public String getAllCount() {
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
		}

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


	public List<Student> displayStudentByStatus(Boolean asc, String status) {
		Sort sort = Sort.by(status);
		return repo.findAll(sort);
	}

	public List<Student> findPaginated(int pageNo, int pazeSize) {
		Pageable paging =PageRequest.of(pageNo, pazeSize);
		Page<Student> page = repo.findAll(paging);
		return page.toList();
	}

	public List<Student> findStudentStatusIn() {

		return repo.findAll(Sort.by(Sort.Direction.ASC, "active"));
	}

	public Map<String, Object> findStudentContainingName(int page, int limit, String sort, String lastName) {
		try {
			int countData = (int) repo.count();

			Pageable pagingSort = null;
			if (limit == 0) {
				limit = countData;
			}


			if (sort.equals("ASC")) {
				pagingSort = PageRequest.of(page, limit, Sort.by(lastName));
			} else {
				pagingSort = PageRequest.of(page, limit, Sort.by(lastName));
				//pagingSort = PageRequest.of(page, limit, lastName);
			}
			Page<Student> map = repo.findAll(pagingSort);
			Map<String, Object> response = new HashMap<>();
			//Map<Student> map = new HashMap();
			response.put("data", map.getContent());
			response.put("Get currentPage Number ", map.getNumber());
			response.put("total", map.getTotalElements());
			response.put("totalPages count ", map.getTotalPages());
			response.put("perPage size", map.getSize());
			response.put("perPageElement count", map.getNumberOfElements());
			return response ;

		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}


	}



}
