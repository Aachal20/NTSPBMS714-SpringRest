package com.nit.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nit.entity.Address;
import com.nit.entity.Student;

public interface studRepo extends MongoRepository<Student, Integer> {
   List<Student> findByIsActive(Boolean isActive);
  // List<Student> findByAddress(ArrayList<Address> address);
}
