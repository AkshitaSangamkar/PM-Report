package com.example.cipl_amc.entity;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PMReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pmId;
	
	private String empName;
	private String department;
	private String location;
	
//	private String assetName;
	private String serialNo;
	private String computerName;
	private String model;
	
	private Long pmNo;
	private String quarter;
	private LocalDate date;
	private String cpuMakeModel;
	private String monitorModel;
	private String cpuSerialNo;
	private String keyboardType;
	private String mouseType;
	private String kb;
	private String ms;
	private String monitorSno;
	private String ipAddress;
	private String processorType;
	private String hddType;
	private String ramCapacity;
	private String dvdType;
	private String operatingSystem;
	private String osVersion;
	private String msIndicLangInput;
	private String sapVersion;
	private String msOfficeVer;
	private String outlookPatchVer;
	private String bigFixTarget;
	private String JavaVer;
	private String adobeVer;
	private String lapsVer;
	private String trellixAgentVer;
	private String trellixDLPVer;
	private String trellixEPSecVer;
	private String trellixThreatPre;
	private String SymantecVer;
	private String trendEPACVer;
	private String UltraVNCVer;
	private String chromeVer;
	private String dscDriverVer;
	
	private String hardDiskCrash;
	private String virusCheck;
	private String checkInstallations;
	private String checkAccessories;
	private String connectedToUPS;
	private String checkAutoArchiveConfig;
	private String UPSOutput;
	private String pcPurpose;
	private String remarks;
	
//	@ManyToOne
//	@JoinColumn(name = "s_no")
//	private Asset asset;
	
	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;
}
