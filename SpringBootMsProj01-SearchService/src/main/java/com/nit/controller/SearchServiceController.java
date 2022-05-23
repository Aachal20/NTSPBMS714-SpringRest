package com.nit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchServiceController {
   
	@GetMapping("/display")
	 public String displayMsg() {
		 return "Welcome to flipkart search service";
	 }
}
