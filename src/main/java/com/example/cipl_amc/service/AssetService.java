package com.example.cipl_amc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cipl_amc.entity.Asset;
import com.example.cipl_amc.repository.AssetRepo;

@Service
public class AssetService {

	@Autowired
	private AssetRepo assetRepo;
	
	public List<Asset> getAssetsByEmployeeId(String empId) {
        return assetRepo.findByEmployeeEmpId(empId);
    }
}
