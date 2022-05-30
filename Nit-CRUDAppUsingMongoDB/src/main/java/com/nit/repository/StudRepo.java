package com.nit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nit.entity.Student;

public interface StudRepo extends MongoRepository<Student, Integer> {
	// List<Student> findByIsActive(Boolean isActive);
	
	// List<Student> findByStatus(ArrayList<Status> status);
	// boolean findByStatusInActiveList(Boolean inActive);
	// void findByActive();
	// List<Status> findByActive(boolean active);
	// List <Student> findAll();

//	public Optional<Student>   findByIdAndStatus(Integer id , String status);
	public Optional<Student> findByIdAndStatusIn();

	@Query("from Student where status In(?1,?2)")
	public List<Student> searchStudentByStatusIn(String status1, String status2);

	public List<Student> findStudentByStatus(String s1, String s2);

	public List<Student> findStudentByStatus();

	// @Query(from Student where status =:active AND status=: Isactive )
	// @Query(SELECT * from Student where status in(active,Inactive))
	// List<Student> findByStatus(String active, String Inactive);
	
	// @Query("{'status': active} , { 'status': Inactive} ")
	// List<Student> findNamedList(@Param("status") String active,
	
	// @Param("status")String Inactive);
	// List<Book> findNamedParameters(@Param("author") String author,
	
	// @Param("category") String category);
  // @Query(from Student where status =:active AND status=: Isactive )

}
