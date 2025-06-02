package com.example.cipl_amc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cipl_amc.entity.Asset;
import com.example.cipl_amc.entity.Employee;
import com.example.cipl_amc.entity.PMReport;
import com.example.cipl_amc.repository.AssetRepo;
import com.example.cipl_amc.repository.EmpRepo;
import com.example.cipl_amc.repository.PMReportRepo;

@Service
public class PMReportService {

	@Autowired
	private PMReportRepo pmReportRepo;
	
	@Autowired
	private EmpRepo empRepo;
	
	@Autowired
	private AssetRepo assetRepo;
	
	public PMReport savePMReport(PMReport pmReport) {
		
		if(pmReport.getEmployee() != null && pmReport.getEmployee().getEmpId() != null) {
			String empId = pmReport.getEmployee().getEmpId();
			
			Employee employee = empRepo.findByEmpId(empId)
					.orElseThrow(() -> new RuntimeException("Employee with ID " + empId + "not found"));
			
			pmReport.setEmployee(employee);
			
//			List<Asset> assets = assetRepo.findByEmployeeEmpId(empId);
//			
//			if(!assets.isEmpty()) {
//				pmReport.setAsset(assets.get(0));
//			}else {
//				throw new RuntimeException("No asset found for Employee ID " + empId);
//			}
		}
		
		
		return pmReportRepo.save(pmReport);
	}
}
