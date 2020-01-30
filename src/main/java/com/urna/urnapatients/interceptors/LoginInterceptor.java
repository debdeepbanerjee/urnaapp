package com.urna.urnapatients.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.models.LoginInfo;
import com.urna.urnapatients.models.Patient;

public class LoginInterceptor extends HandlerInterceptorAdapter  {
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
    	LoginInfo loginInfo = getUserLoggedInInfo(request.getSession());
    	if (loginInfo.isLoggedIn() && loginInfo.getDoctor()!=null) {
        	response.sendRedirect("/UrnaLandingSecuredDoctor");
        	return false;
        }
    	else if (loginInfo.isLoggedIn() && loginInfo.getPatient()!=null) {
        	response.sendRedirect("/UrnaLandingSecuredPatient");
        	return false;
        }
    	else {
        	response.sendRedirect("/UrnaLanding");
        	return false;
        }
        //return true;
    }

	

	private LoginInfo getUserLoggedInInfo(HttpSession httpSession) {
		Doctor doctor = (Doctor) httpSession.getAttribute("doctor");
		Patient patient = (Patient) httpSession.getAttribute("patient");
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setDoctor(doctor);
		loginInfo.setPatient(patient);
		if(doctor !=null || patient!=null) {
			loginInfo.setLoggedIn(true);
		} else {
			loginInfo.setLoggedIn(false);
		}
	    return loginInfo;
	}
}
