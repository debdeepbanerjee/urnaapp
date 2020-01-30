package com.urna.urnapatients.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter  {
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        if (isUserLogged(request.getSession())) {
            addToModelUserDetails(request.getSession());
        }
        return true;
    }

	private void addToModelUserDetails(HttpSession session) {
		// TODO Auto-generated method stub
		
	}

	private boolean isUserLogged(HttpSession httpSession) {
		
		return false;
	}
}
