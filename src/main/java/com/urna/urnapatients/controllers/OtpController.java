package com.urna.urnapatients.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.urna.urnapatients.models.OtpInfo;
import com.urna.urnapatients.services.OtpService;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/otp")
public class OtpController {
	
	OtpService otpService;
	
	public OtpService getOtpService() {
		return otpService;
	}

	@Autowired
	public void setOtpService(OtpService otpService) {
		this.otpService = otpService;
	}

	@PostMapping("/otpinfo")
	public @ResponseBody OtpInfo createOptInfo(@Valid @RequestBody OtpInfo otpInfo) {	    
		return otpService.save(otpInfo);
	  }
}
