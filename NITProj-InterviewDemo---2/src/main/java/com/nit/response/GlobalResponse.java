package com.nit.response;

import com.nit.entity.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResponse {
	
	private String reason;
	private String message;
	private int status;
  
}
