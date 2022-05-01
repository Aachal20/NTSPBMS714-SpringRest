package com.nit.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.model.Company;
import com.nit.model.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerOperationsController {
  
	/*@GetMapping("/report")
	 public ResponseEntity<Customer> showData(){
	   Customer cust =  new Customer(1001 , "Aachal" , 5526.39f);
	   HttpStatus status = HttpStatus.OK;
	   return  new ResponseEntity<Customer> (cust,status);
	}*/
	
	/*@GetMapping("/report1")
	 public ResponseEntity<Customer> showData1(){
		//body
	   Customer cust =  new Customer(1001 , "Aachal" , 5526.39f ,
			                                                            new String[] {"red " ,"Green" ,"Pink"},
			                                                            List.of("10th" , "10+2" , "MCA"),
			                                                             Set.of(55555555555L, 9999999999L,8524746263L),
			                                                             Map.of("aadharId " , 652394741L , "PANId" ,57413694L , "JobCardId" , 5888888L),
			                                                              new Company("SAMSUNG",  "Kolkatta" ,  "Electronics" , 400));                                                         
			   
	   HttpStatus status = HttpStatus.OK;
	   return  new ResponseEntity<Customer> (cust,status);
	}*/
	
	@GetMapping("/report2")
	public ResponseEntity<Customer>  showData1(){
		//body
		  Customer cust=new Customer(1001,"raja",54566.66f,
				                                                       new String[] {"red","green","blue"},
				                                                       List.of("10th","10+2","B.Tech"),
				                                                       Set.of(544535345L,7576575L,6465654L),
				                                                       Map.of("aadhar", 35345435L, "panNo", 354353534L),
				                                                       new Company("SAMSUNG","hyd","Eletronics",4000));
				//status                                                       
		  HttpStatus status=HttpStatus.OK;
		  return  new ResponseEntity<Customer>(cust,status);
	}
	
}
