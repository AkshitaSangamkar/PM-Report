package com.example.cipl_amc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
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
import org.springframework.web.server.ResponseStatusException;

import com.example.cipl_amc.entity.Asset;
import com.example.cipl_amc.entity.Employee;
import com.example.cipl_amc.entity.PMReport;
import com.example.cipl_amc.repository.EmpRepo;
import com.example.cipl_amc.repository.PMReportRepo;
import com.example.cipl_amc.service.EmployeeService;
import com.example.cipl_amc.service.LoginService;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("http://localhost:5173")
public class EmployeeController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/send-otp")
	public ResponseEntity<String> sendOtp(@RequestParam String empId){
		System.out.println(empId);
		try {
			String response = loginService.generateAndSendOtp(empId);
			return ResponseEntity.ok(response);
		}catch(IllegalArgumentException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	

    // Verify OTP and fetch employee details
    @PostMapping("/verify-otp")
    public ResponseEntity<Object> verifyOtp(@RequestParam String empId, @RequestParam String otp) {
        boolean isValid = loginService.validateOtp(empId, otp);
        
        if (isValid) {
            // Fetch employee details after successful OTP validation
            Employee employee = employeeService.getEmployeeById(empId);
            
            if (employee != null) {
                return ResponseEntity.ok(employee);  // Return employee details if found
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired OTP");
        }
    }
    
    @Autowired
    private EmployeeService empService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String empId){
    	Employee employee = empService.getEmployeeById(empId);
    	if(employee != null) {
    		return ResponseEntity.ok(employee);
    	} else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
    	}
    }
    
    @GetMapping("/details")
    public ResponseEntity<?> getEmployeeDetails(@RequestParam String empId){
    	Employee employee = employeeService.getEmployeeById(empId);
    	
    	if(employee != null) {
    		return ResponseEntity.ok(employee);
    	}else {
    		return ResponseEntity.status(404).body("Employee not found with ID: " + empId);
    				
    	}
    }
    
//    @Autowired
//    private PMReportRepo pmReportRepo;
//    
//    @GetMapping("/pm-report/search")
//    public ResponseEntity<List<PMReport>> getPMReports(
//    		@RequestParam String empId,
//    		@RequestParam String quarter,
//    		@RequestParam String assetName){
//    	List<PMReport> reports = pmReportRepo.findByEmployee_EmpIdAndQuarterAndAssetAssetName(empId, quarter, assetName);
//    	return ResponseEntity.ok(reports);
//    }
}
