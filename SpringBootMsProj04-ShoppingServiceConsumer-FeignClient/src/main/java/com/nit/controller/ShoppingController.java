package com.nit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.nit.client.IBillingServiceRestConsumer;



@RestController
@RequestMapping("/shopping")
public class ShoppingController {
	
	@Autowired
	private IBillingServiceRestConsumer consumer;
	
	@GetMapping("/info")
	public  String   displayShoppingDetails() {
		System.out.println("Shopping Controller::client comp class name::" +consumer.getClass());
		return  "Pongal Shopping for Family ...."+consumer.fetchBillDetails();
		
	}
	
}