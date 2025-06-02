package com.example.cipl_amc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cipl_amc.entity.OtpData;

@Repository
public interface OtpRepo extends JpaRepository<OtpData, Integer>{

	Optional<OtpData> findByEmpEmailAndIsValid(String empEmail, boolean isValid);
}
