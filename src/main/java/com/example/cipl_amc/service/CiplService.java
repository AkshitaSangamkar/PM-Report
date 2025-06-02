package com.example.cipl_amc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cipl_amc.entity.cipl;
import com.example.cipl_amc.repository.CiplRepo;

@Service
public class CiplService {

	@Autowired
	public CiplRepo ciplRepo;
	
	public boolean ciplLogin(String username, String password) {
		return ciplRepo.findById(username)
				.map(cipl -> cipl.getCipl_password().equals(password))
				.orElse(false);
	}
}
