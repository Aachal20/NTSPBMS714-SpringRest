package com.nit.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nit.entity.Root;

public interface RootRepository extends MongoRepository<Root, Integer> {

	boolean existsById(String id);

	Optional<Root> findById(String id);

}
