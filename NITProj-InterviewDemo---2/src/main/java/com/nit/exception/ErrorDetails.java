package com.nit.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
	public static final String HttpStatus = null;
	private Long timeStamp;
	private String message;
	private String status;
	
}
