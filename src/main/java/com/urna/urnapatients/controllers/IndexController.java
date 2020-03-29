package com.urna.urnapatients.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping(value = {
        "/",
        "/PatientLogin",
        "/DoctorLogin",
        "/RegisterDoctor",
        "/RegisterPatient",
        "/UrnaLandingSecuredDoctor",
        "/UrnaLandingSecuredPatient"
	})
	public String index() {
		return "index";
	}
}