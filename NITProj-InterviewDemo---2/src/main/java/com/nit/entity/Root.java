package com.nit.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document("MYROOT")
public class Root  {
	
	@Id
	private String  id;
	private String type;
	private String name;
	
	private Integer rating;
	private Boolean isDeleted;
	
	public Description description;
	
	
}
