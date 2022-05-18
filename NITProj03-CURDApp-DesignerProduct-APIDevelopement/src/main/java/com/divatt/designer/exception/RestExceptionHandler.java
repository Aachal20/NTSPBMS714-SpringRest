package com.divatt.designer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.divatt.designer.response.GlobalResponse;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CustomErrorMessages> handleException(CustomException exc){
		
		CustomErrorMessages customeErrorMessage = new CustomErrorMessages(HttpStatus.BAD_REQUEST.value(),
				exc.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(customeErrorMessage, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler
	public ResponseEntity<CustomErrorMessages> handleException(Exception exc){
		
		CustomErrorMessages customeErrorMessage = new CustomErrorMessages(HttpStatus.BAD_REQUEST.value(),
				exc.getMessage(),
				System.currentTimeMillis());
		return new ResponseEntity<>(customeErrorMessage, HttpStatus.BAD_REQUEST);
		
	}
	
	/*	
		public ResponseEntity<GlobalResponse> handleGlobalException(CustomException ex){
			GlobalResponse globalResponseErrorMessage = new GlobalResponse("404 Bad Request",
					"Product Not found", 
					HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(globalResponseErrorMessage, HttpStatus.BAD_REQUEST);
		}*/
	
}
