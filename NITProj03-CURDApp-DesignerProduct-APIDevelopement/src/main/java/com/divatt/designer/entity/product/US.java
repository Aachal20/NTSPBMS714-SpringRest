package com.divatt.designer.entity.product;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class US {
	private Long mrp;
	private Long dealPrice;
	private String discountType;
	private Integer discountValue;
	//@JsonFormat(shape = Shape.STRING,pattern = "yyyy/MM/dd")
	//private Date dealStart;
	//@JsonFormat(shape = Shape.STRING,pattern = "yyyy/MM/dd")
	//private Date dealEnd;
	
	
	public US() {
		// TODO Auto-generated constructor stub
	}


	public Long getMrp() {
		return mrp;
	}


	public void setMrp(Long mrp) {
		this.mrp = mrp;
	}


	public Long getDealPrice() {
		return dealPrice;
	}


	public void setDealPrice(Long dealPrice) {
		this.dealPrice = dealPrice;
	}


	public String getDiscountType() {
		return discountType;
	}


	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}


	public Integer getDiscountValue() {
		return discountValue;
	}


	public void setDiscountValue(Integer discountValue) {
		this.discountValue = discountValue;
	}


	public US(Long mrp, Long dealPrice, String discountType, Integer discountValue) {
		super();
		this.mrp = mrp;
		this.dealPrice = dealPrice;
		this.discountType = discountType;
		this.discountValue = discountValue;
	}


	@Override
	public String toString() {
		return "US [mrp=" + mrp + ", dealPrice=" + dealPrice + ", discountType=" + discountType + ", discountValue="
				+ discountValue + "]";
	}
	
	
	
}
