package com.example.cipl_amc.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cipl_amc.entity.Employee;
import com.example.cipl_amc.entity.PMReport;
import com.example.cipl_amc.entity.cipl;
import com.example.cipl_amc.service.CiplService;
import com.example.cipl_amc.service.EmployeeService;
import com.example.cipl_amc.service.PMReportService;

@RestController
@RequestMapping("/api/cipl")
@CrossOrigin("http://localhost:5173")
public class CiplController {
	
	@Autowired
	public CiplService ciplService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> loginDetails){
		String username = loginDetails.get("username");
		String password = loginDetails.get("password");
		
		if(ciplService.ciplLogin(username, password)) {
			return ResponseEntity.ok("Login Successful!");
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}	
	
	@Autowired
	public EmployeeService employeeService;
	
	@GetMapping("/{empId}")
	public ResponseEntity<?> getEmployeeById(@PathVariable String empId){
		Employee employee = employeeService.getEmployeeById(empId);
		
		if(employee != null) {
			return ResponseEntity.ok(employee);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID:"+empId);
		}
		
	}
	
	@Autowired
	private PMReportService pmReportService;
	
	@PostMapping("/pm-report/save")
	public ResponseEntity<PMReport> submitPMReport(@RequestBody PMReport pmReport){
		PMReport savedReport = pmReportService.savePMReport(pmReport);
		return ResponseEntity.ok(savedReport);
	}
	
}
