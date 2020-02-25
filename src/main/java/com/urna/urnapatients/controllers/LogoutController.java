package com.urna.urnapatients.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/rest/urna/logout")
public class LogoutController {
	@GetMapping("/logout")
	public @ResponseBody String logout(HttpSession sess) {
		sess.invalidate();
		return "loggedout";
		
	  }
}
