package com.apple.hrm.base.Service;
import org.springframework.security.core.userdetails.UserDetailsService;


import com.apple.hrm.base.entity.User;
import com.apple.hrm.base.entity.contact;

public interface UserService extends UserDetailsService {

	User verfiyemailandpassword(String email);
	User save(User user);
	contact save(contact uscontacter);
	
}
