package com.example.cipl_amc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cipl_amc.entity.PMReport;
import com.example.cipl_amc.repository.PMReportRepo;
import com.example.cipl_amc.service.SystemDetailsService;

@RestController
@RequestMapping("/pmreports")
@CrossOrigin("http://localhost:5173")
public class PMReportController {
	
	@Autowired
	private SystemDetailsService systemDetailsService;
	
	@Autowired
	private PMReportRepo pmReportRepo;
	
	@GetMapping("/fetch-system-details")
	public PMReport fetchSystemDetails() {
		PMReport pmReport = systemDetailsService.fetchSystemDetails();
		pmReportRepo.save(pmReport);
		return pmReport;
	}
	
	@PutMapping("/update/{pmId}")
	public ResponseEntity<PMReport> updatePMReport(@PathVariable Long pmId, @RequestBody PMReport updatedPMReport){
		Optional<PMReport> existingPMReportOptional = pmReportRepo.findById(pmId);
		
		if(existingPMReportOptional.isPresent()) {
			PMReport existingPMReport = existingPMReportOptional.get();
			
			existingPMReport.setEmpName(Optional.ofNullable(updatedPMReport.getEmpName()).orElse(existingPMReport.getEmpName()));
			existingPMReport.setDepartment(Optional.ofNullable(updatedPMReport.getDepartment()).orElse(existingPMReport.getDepartment()));
			existingPMReport.setLocation(Optional.ofNullable(updatedPMReport.getLocation()).orElse(existingPMReport.getLocation()));
			existingPMReport.setSerialNo(Optional.ofNullable(updatedPMReport.getSerialNo()).orElse(existingPMReport.getSerialNo()));
			existingPMReport.setComputerName(Optional.ofNullable(updatedPMReport.getComputerName()).orElse(existingPMReport.getComputerName()));
			existingPMReport.setModel(Optional.ofNullable(updatedPMReport.getModel()).orElse(existingPMReport.getModel()));
			existingPMReport.setPmNo(Optional.ofNullable(updatedPMReport.getPmNo()).orElse(existingPMReport.getPmNo()));
			existingPMReport.setQuarter(Optional.ofNullable(updatedPMReport.getQuarter()).orElse(existingPMReport.getQuarter()));
			existingPMReport.setDate(Optional.ofNullable(updatedPMReport.getDate()).orElse(existingPMReport.getDate()));
			existingPMReport.setCpuMakeModel(Optional.ofNullable(updatedPMReport.getCpuMakeModel()).orElse(existingPMReport.getCpuMakeModel()));
			existingPMReport.setMonitorModel(Optional.ofNullable(updatedPMReport.getMonitorModel()).orElse(existingPMReport.getMonitorModel()));
			existingPMReport.setCpuSerialNo(Optional.ofNullable(updatedPMReport.getCpuSerialNo()).orElse(existingPMReport.getCpuSerialNo()));
			existingPMReport.setKeyboardType(Optional.ofNullable(updatedPMReport.getKeyboardType()).orElse(existingPMReport.getKeyboardType()));
			existingPMReport.setMouseType(Optional.ofNullable(updatedPMReport.getMouseType()).orElse(existingPMReport.getMouseType()));
			existingPMReport.setKb(Optional.ofNullable(updatedPMReport.getKb()).orElse(existingPMReport.getKb()));
			existingPMReport.setMs(Optional.ofNullable(updatedPMReport.getMs()).orElse(existingPMReport.getMs()));
			existingPMReport.setMonitorSno(Optional.ofNullable(updatedPMReport.getMonitorSno()).orElse( existingPMReport.getMonitorSno()));
			existingPMReport.setIpAddress(Optional.ofNullable(updatedPMReport.getIpAddress()).orElse(existingPMReport.getIpAddress()));
			existingPMReport.setProcessorType(Optional.ofNullable(updatedPMReport.getProcessorType()).orElse(existingPMReport.getProcessorType()));
			existingPMReport.setHddType(Optional.ofNullable(updatedPMReport.getHddType()).orElse(existingPMReport.getHddType()));
			existingPMReport.setRamCapacity(Optional.ofNullable(updatedPMReport.getRamCapacity()).orElse(existingPMReport.getRamCapacity()));
			existingPMReport.setDvdType(Optional.ofNullable(updatedPMReport.getDvdType()).orElse(existingPMReport.getDvdType()));
			existingPMReport.setOperatingSystem(Optional.ofNullable(updatedPMReport.getOperatingSystem()).orElse(existingPMReport.getOperatingSystem()));
			existingPMReport.setOsVersion(Optional.ofNullable(updatedPMReport.getOsVersion()).orElse(existingPMReport.getOsVersion()));
			existingPMReport.setMsIndicLangInput(Optional.ofNullable(updatedPMReport.getMsIndicLangInput()).orElse(existingPMReport.getMsIndicLangInput()));
			existingPMReport.setSapVersion(Optional.ofNullable(updatedPMReport.getSapVersion()).orElse(existingPMReport.getSapVersion()));
			existingPMReport.setMsOfficeVer(Optional.ofNullable(updatedPMReport.getMsOfficeVer()).orElse(existingPMReport.getMsOfficeVer()));
			existingPMReport.setOutlookPatchVer(Optional.ofNullable(updatedPMReport.getOutlookPatchVer()).orElse(existingPMReport.getOutlookPatchVer()));
			existingPMReport.setJavaVer(Optional.ofNullable(updatedPMReport.getJavaVer()).orElse(existingPMReport.getJavaVer()));
			existingPMReport.setAdobeVer(Optional.ofNullable(updatedPMReport.getAdobeVer()).orElse(existingPMReport.getAdobeVer()));
			existingPMReport.setLapsVer(Optional.ofNullable(updatedPMReport.getLapsVer()).orElse(existingPMReport.getLapsVer()));
			existingPMReport.setTrellixAgentVer(Optional.ofNullable(updatedPMReport.getTrellixAgentVer()).orElse(existingPMReport.getTrellixAgentVer()));
			existingPMReport.setTrellixDLPVer(Optional.ofNullable(updatedPMReport.getTrellixDLPVer()).orElse(existingPMReport.getTrellixDLPVer()));
			existingPMReport.setTrellixEPSecVer(Optional.ofNullable(updatedPMReport.getTrellixEPSecVer()).orElse(existingPMReport.getTrellixEPSecVer()));
			existingPMReport.setTrellixThreatPre(Optional.ofNullable(updatedPMReport.getTrellixThreatPre()).orElse(existingPMReport.getTrellixThreatPre()));
			existingPMReport.setTrendEPACVer(Optional.ofNullable(updatedPMReport.getTrendEPACVer()).orElse(existingPMReport.getTrendEPACVer()));
			existingPMReport.setSymantecVer(Optional.ofNullable(updatedPMReport.getSymantecVer()).orElse(existingPMReport.getSymantecVer()));
			existingPMReport.setUltraVNCVer(Optional.ofNullable(updatedPMReport.getUltraVNCVer()).orElse(existingPMReport.getUltraVNCVer()));
			existingPMReport.setChromeVer(Optional.ofNullable(updatedPMReport.getChromeVer()).orElse(existingPMReport.getChromeVer()));
			existingPMReport.setDscDriverVer(Optional.ofNullable(updatedPMReport.getDscDriverVer()).orElse(existingPMReport.getDscDriverVer()));
			
			PMReport savedPMReport = pmReportRepo.save(existingPMReport);
			return ResponseEntity.ok(savedPMReport);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
