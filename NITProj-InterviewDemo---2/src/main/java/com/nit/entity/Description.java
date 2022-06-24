package com.nit.entity;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Description {
	
   private ArrayList<Batch> batch;
   private ArrayList<Location> location;
   
}
