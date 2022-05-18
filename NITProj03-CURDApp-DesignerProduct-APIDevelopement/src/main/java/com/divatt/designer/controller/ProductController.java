package com.divatt.designer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divatt.designer.entity.product.ProductMasterEntity;
import com.divatt.designer.exception.CustomException;
import com.divatt.designer.response.GlobalResponse;
import com.divatt.designer.service.ProductService;
import com.divatt.designer.service.SequenceGenerator;

@RestController
@RequestMapping("/designerProduct")
public class ProductController {

	@Autowired
	private ProductService productService;

	
	@PostMapping("/add")
	public GlobalResponse add(@Valid @RequestBody ProductMasterEntity productData) {
		
		try {
			return productService.addData(productData);
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	
	@GetMapping("/view/{productId}")
	public ProductMasterEntity viewProductDetails(@PathVariable Integer productId) {
		try {
			return productService.productDetalis(productId);
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}
			
}
	
	//not working
	@PutMapping("/status/{productId}")
	public GlobalResponse changeStatus(@PathVariable Integer productId) {
		try {
		  	return  this.productService.toChangeStatus(productId);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	
	}
	
	
	@PutMapping("/update/{productId}")
	public GlobalResponse updateProductData(@RequestBody ProductMasterEntity productMasterEntity,
			                                                                              @PathVariable Integer productId) {
		try {
			return this.productService.updateProduct(productId, productMasterEntity);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	
	@PutMapping("/delete/{productId}")
	public GlobalResponse productDelete(@PathVariable Integer productId) {
		try {
			 return this.productService.deleteProduct(productId);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	
	
	
	
}