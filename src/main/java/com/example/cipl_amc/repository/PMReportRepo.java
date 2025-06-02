package com.example.cipl_amc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cipl_amc.entity.PMReport;

public interface PMReportRepo extends JpaRepository<PMReport, Long>{


	List<PMReport> findByEmployee_EmpIdAndQuarter(String empId, String quarter);

}
