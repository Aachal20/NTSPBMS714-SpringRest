package com.divatt.designer.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divatt.designer.entity.product.ProductMasterEntity;
import com.divatt.designer.exception.CustomException;
import com.divatt.designer.helper.CustomRandomString;
import com.divatt.designer.repo.ProductRepository;
import com.divatt.designer.response.GlobalResponse;

@Service
public class ProductService {
	private static final String SEQUENCE_NAME = null;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CustomRandomString customRandomString;

	
	@Autowired
	private SequenceGenerator sequenceGenerator;
	
	
	public GlobalResponse addData(ProductMasterEntity productData) {
		try {
			//generate sequence
			productData.setProductId(sequenceGenerator.getCurrentSequenceNumber(SEQUENCE_NAME));
			
			/*productData.setProductId();
			int productId=customRandomString.getAlphaNumericString(8);
				Random rd= new Random();
				int productId=rd.nextInt();
				while(productRepo.existsById(productId) && productId>0)
			{
					productId=rd.nextInt();
				}
				productData.setProductId(productId);*/
			productRepo.save(productData);
			return new GlobalResponse("Success!!",  "Your Product added successfully",  200);
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}



	public ProductMasterEntity productDetalis(Integer productId) {
		try {
			long count = sequenceGenerator.getCurrentSequenceNumber(ProductMasterEntity.SEQUENCE_NAME);
			Random rd = new Random();
		
			if(productRepo.existsById(productId)) {
				return productRepo.findById(productId).get();
			}else {
				throw new CustomException("Product Not Found for this ID");
			}
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}


	public GlobalResponse toChangeStatus(Integer productId){
		try {
			if( productRepo.existsById(productId)) {
				Boolean status;
				Optional<ProductMasterEntity>  productData  = productRepo.findById(productId);
				ProductMasterEntity productEntity = productData.get();
				if (productEntity.getIsActive().equals(true)) {
					status = false;
				} else {
					status = true;
				}
				productEntity.setIsActive(status);
				productEntity.setUpdatedBy(productEntity.getDesignerId().toString());
				productRepo.save(productEntity);
				return new GlobalResponse("Success", "product Status change successfully", 200);
			}else {
				return new GlobalResponse("Bad request", "Product does not exist for this Id", 400);
			}
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}



	public GlobalResponse updateProduct(Integer productId, ProductMasterEntity productMasterEntity) {
		try {

			if (productRepo.existsById(productId)) {
				productRepo.save(productMasterEntity);
				return new GlobalResponse("Success", "Product updated successfully", 200);
			} else {
				throw new CustomException("Product not found");
			}
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}


	public GlobalResponse deleteProduct(Integer productId) {

		try {
			if (productRepo.existsById(productId)) {
				Boolean isDelete = false;
				Optional<ProductMasterEntity> productData = productRepo.findById(productId);
				ProductMasterEntity productEntity = productData.get();
				if (productEntity.getIsDeleted().equals(false)) {
					isDelete = true;
				} else {
					return new GlobalResponse("Bad request!!", "Product AllReady deleted", 400);
				}
				productEntity.setIsDeleted(isDelete);
				productEntity.setUpdatedBy(productEntity.getDesignerId().toString());

				productRepo.save(productEntity);
				return new GlobalResponse("Success", "Deleted successfully", 200);
			} else {
				return new GlobalResponse("Bad request", "Product does not exist", 400);
			}
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

}


