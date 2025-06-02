package com.example.cipl_amc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

	@Id
	private Integer s_no;
	private String empName;
	private String location;
	private String locationCode;
	private String make;
	private String model;
	private String serialNo;
	private String computerName;
	private String osVersion;
	private String msOfficeVer;
	private String pmStatus;
	private String assetName;
	
	@ManyToOne
	@JoinColumn(name = "emp_id", nullable = false)
	@JsonIgnore
	private Employee employee;
}
