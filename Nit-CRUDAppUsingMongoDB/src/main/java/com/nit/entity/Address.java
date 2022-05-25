package com.nit.entity;

import java.util.HashMap;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
//@Document(collection="data_Address")
public class Address {
	
	@Field(name="addreline")
	private String addressLine;
	@Field(name="street")
	private  String streetAddress;
	private String city;
	private String state;
	private Long postalCode;
	//private HashMap<String,String> address;
}