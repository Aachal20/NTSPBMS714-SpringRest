package com.nit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.entity.Root;
import com.nit.exception.CustomException;
import com.nit.response.GlobalResponse;
import com.nit.service.RootServiceImpl;

@RestController
@RequestMapping("/root")
public class RootController {

	@Autowired
	private RootServiceImpl service;


	@GetMapping("/test")
	public String main() {
		return "ok";
	}
	@PostMapping("/add")
	public GlobalResponse registerRootData(@RequestBody Root root)  {
		try {
			System.out.println(root.getId());
			System.out.println(root.getType());
			System.out.println(root.getRating());
			System.out.println(root.getName());
			System.out.println(root.getIsDeleted());
			System.out.println(root.getDescription());
			return service.addData(root);

		}catch(Exception e){
			//  e.getMessage();
			throw new CustomException(e.getMessage());
		}

	}

	@GetMapping("/view/{id}")
	public Root viewRootData(@PathVariable String  id) {
		try {
			System.out.println("RootController.viewRootData()");
			return service.viewDataById(id);
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

	@GetMapping("/viewAll")
	public List<Root>  viewAllRootData() {
		try {
			System.out.println("RootController.viewRootData()");
			return service.viewData();
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}



	}

	@PutMapping("/update")
	public GlobalResponse updateData(@RequestBody Root root) {
		try {

			return this.service.updateRootData(root);
		}catch(Exception e) {
			throw new CustomException(e.getMessage());
		}

	}
	
	@PutMapping("/delete/{id}")
	public GlobalResponse putCategoryMulDelete(@PathVariable String  id) {

		try {
			return this.service.deletedata(id);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
}




