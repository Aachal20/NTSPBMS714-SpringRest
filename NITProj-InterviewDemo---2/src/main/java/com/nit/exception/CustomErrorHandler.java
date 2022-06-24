package com.nit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//@ControllerAdvice
@RestControllerAdvice
public class CustomErrorHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorDetails>  handelException(CustomException exception){
		ErrorDetails details = new ErrorDetails(System.currentTimeMillis(), exception.getMessage() ,"404-Error");
		System.out.println("CustomErrorHandler.handelException()");
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	

}

	@ExceptionHandler
	public ResponseEntity<ErrorDetails>  handelException(Exception exception){
		ErrorDetails details = new ErrorDetails(System.currentTimeMillis(), exception.getMessage() ,"Problem in execution");
      System.out.println("CustomErrorHandler.handelException()");
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	

}
}

