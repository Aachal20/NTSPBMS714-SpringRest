package com.divatt.designer.entity.product;

public class AgeEntity {
	private Integer min;
	private Integer max;
	

	public AgeEntity() {
		// TODO Auto-generated constructor stub
	}
	
	
	public AgeEntity(Integer min, Integer max) {
		super();
		this.min = min;
		this.max = max;
	}
	
	
	
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public Integer getMax() {
		return max;
	}
	

	public void setMax(Integer max) {
		this.max = max;
	}
	
	
	@Override
	public String toString() {
		return "AgeEntity [min=" + min + ", max=" + max + "]";
	}

}
