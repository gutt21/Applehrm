package com.apple.hrm.base.seesion_;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class sessionhellper {

	
	
	public void removeAttributehellper() {
		try {
			
			    HttpSession request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	           
			    request.removeAttribute("Message");
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
