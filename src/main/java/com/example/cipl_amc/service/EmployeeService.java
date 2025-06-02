package com.example.cipl_amc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cipl_amc.entity.Employee;
import com.example.cipl_amc.repository.EmpRepo;

@Service
public class EmployeeService {

	 @Autowired
	    private EmpRepo empRepo;

	    public Employee getEmployeeById(String empId) {
	        // Fetch the employee from the repository (database)
	        return empRepo.findByEmpId(empId).orElse(null); // Assuming `findByEmpId` is a valid method in EmpRepo
	    }
}
