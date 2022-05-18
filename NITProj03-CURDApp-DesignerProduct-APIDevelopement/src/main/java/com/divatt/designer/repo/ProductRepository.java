package com.divatt.designer.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.divatt.designer.entity.product.ProductMasterEntity;

@Repository
public interface ProductRepository extends MongoRepository<ProductMasterEntity, Integer> {

}
