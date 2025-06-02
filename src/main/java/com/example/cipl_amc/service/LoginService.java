package com.example.cipl_amc.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.cipl_amc.entity.Asset;
import com.example.cipl_amc.entity.Employee;
import com.example.cipl_amc.entity.OtpData;
import com.example.cipl_amc.repository.AssetRepo;
import com.example.cipl_amc.repository.EmpRepo;
import com.example.cipl_amc.repository.OtpRepo;

@Service
public class LoginService {

	@Autowired
	private EmpRepo empRepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private OtpRepo otpRepo;
	
	public String generateAndSendOtp(String empId) {
		Optional<Employee> employeeOptional = empRepo.findById(empId);
		if(employeeOptional.isPresent()) {
			Employee employee = employeeOptional.get();
			String otp = String.valueOf(new Random().nextInt(900000) + 100000);
			
			//Save OTP details in the database
			OtpData otpData = new OtpData();
			otpData.setEmpEmail(employee.getEmail());
			otpData.setOtp(otp);
			otpData.setGeneratedTime(LocalDateTime.now());
			otpData.setValid(true);
			otpRepo.save(otpData);
			
			//Send OTP to employee's email
			sendEmail(employee.getEmail(), otp);
			System.out.println("Generated OTP: " + otp);
			
			return "OTP sent successfully to " + employee.getEmail();
		}else {
			throw new IllegalArgumentException("Employee not found");
		}
	}
	
	public boolean validateOtp(String empId, String otp) {
		Optional<Employee> employeeOptional = empRepo.findById(empId);
		if(employeeOptional.isEmpty()) {
			throw new IllegalArgumentException("Employee not found");
		}
		
		Employee employee = employeeOptional.get();
		String email = employee.getEmail();
		
		//Fetch the latest valid OTP for the email
		Optional<OtpData> otpDataOptional = otpRepo.findByEmpEmailAndIsValid(email, true);
		if(otpDataOptional.isPresent()) {
			OtpData otpData = otpDataOptional.get();
			
			//Validate OTP and its expiration
			if(otpData.getOtp().equals(otp) && 
					Duration.between(otpData.getGeneratedTime(),LocalDateTime.now()).toMinutes() <= 5) {
				otpData.setValid(false);
				otpRepo.save(otpData);
				return true;
			}
		}
		return false;
	}
	
	private void sendEmail(String recipientEmail, String otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(recipientEmail);
		message.setSubject("Your OTP Code");
		message.setText("Your OTP for login is: " + otp);
		try {
		    mailSender.send(message);
		    System.out.println("Email sent to: " + recipientEmail);
		} catch (MailException e) {
		    System.err.println("Failed to send email: " + e.getMessage());
		    e.printStackTrace();
		}
	}
	
	
}
