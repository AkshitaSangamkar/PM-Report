package com.example.cipl_amc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.cipl_amc.entity.Asset;
import com.example.cipl_amc.service.AssetService;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@CrossOrigin("http://localhost:5173")
public class AssetController {

    @Autowired
    private AssetService assetService;

    // Endpoint to get assets by employee ID
    @GetMapping("/employee/{empId}")
    public List<Asset> getAssetsByEmployeeId(@PathVariable String empId) {
        return assetService.getAssetsByEmployeeId(empId);
    }
}

	