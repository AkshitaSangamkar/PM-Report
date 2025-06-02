package com.example.cipl_amc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cipl_amc.entity.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, String>{

	boolean existsByEmpId(String empId);
	Optional<Employee> findByEmpId(String empId);
	
}
