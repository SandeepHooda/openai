package com.sandeep.AI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name ="EMPLOYEE", schema="public")
public class Employee {
	@Id
	@Column(name="EMP_ID")
	private int employeeId;
	@Column(name="NAME")
	private String name;
	@Column(name="CITY")
	private String city;
	@Column(name="SALARY")
	private String salary;
	

}
