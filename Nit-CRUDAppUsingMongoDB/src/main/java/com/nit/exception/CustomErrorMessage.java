package com.nit.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorMessage {


	
	private int status;
	private String message;
	private long timeStamp;

}
