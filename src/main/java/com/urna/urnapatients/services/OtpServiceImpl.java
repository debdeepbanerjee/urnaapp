package com.urna.urnapatients.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.urna.urnapatients.models.OtpInfo;
import com.urna.urnapatients.repo.OtpInfoRepository;

public class OtpServiceImpl implements OtpService{
	OtpInfoRepository otpInfoRepository;

	public OtpInfoRepository getOtpInfoRepository() {
		return otpInfoRepository;
	}

	@Autowired
	public void setOtpInfoRepository(OtpInfoRepository otpInfoRepository) {
		this.otpInfoRepository = otpInfoRepository;
	}

	@Override
	public OtpInfo save(OtpInfo otpInfo) {
		return otpInfoRepository.save(otpInfo);
	}
}
