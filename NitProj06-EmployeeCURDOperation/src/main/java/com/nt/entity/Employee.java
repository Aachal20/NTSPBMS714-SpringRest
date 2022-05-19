package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NonNull;
@Entity
@Table(name="tbl_employee_data")

public class Employee {
  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Integer empId;
	
	@NonNull
	@Column(length = 20)
	private String  empName;
	
	@NonNull
	@Column(length = 20,name="addrs")
	private String empAddrs;
	
	@NonNull
	@Column(length = 20,name="course")
	private String course;
	
	@Column(name="MobNo")
	private Long mobileNo;
	
	@Column(name="IsActive")
	private Boolean isAtive;

	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpAddrs() {
		return empAddrs;
	}

	public void setEmpAddrs(String empAddrs) {
		this.empAddrs = empAddrs;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Boolean getIsAtive() {
		return isAtive;
	}

	public void setIsAtive(Boolean isAtive) {
		this.isAtive = isAtive;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empAddrs=" + empAddrs + ", course=" + course
				+ ", mobileNo=" + mobileNo + ", isAtive=" + isAtive + "]";
	}

	public Employee(Integer empId, @NonNull String empName, @NonNull String empAddrs, @NonNull String course,
			Long mobileNo, Boolean isAtive) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empAddrs = empAddrs;
		this.course = course;
		this.mobileNo = mobileNo;
		this.isAtive = isAtive;
	}
	

}
