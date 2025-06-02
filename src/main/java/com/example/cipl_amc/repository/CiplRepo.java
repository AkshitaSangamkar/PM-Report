package com.example.cipl_amc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cipl_amc.entity.cipl;

@Repository
public interface CiplRepo extends JpaRepository<cipl, String>{

}
