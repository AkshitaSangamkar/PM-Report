package com.example.cipl_amc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cipl_amc.entity.Asset;

@Repository
public interface AssetRepo extends JpaRepository<Asset, Integer>{

	List<Asset> findByEmployeeEmpId(String empId);
}
