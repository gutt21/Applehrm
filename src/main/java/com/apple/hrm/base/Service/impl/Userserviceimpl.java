package com.apple.hrm.base.Service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apple.hrm.base.Repository.Contactrepository;
import com.apple.hrm.base.Repository.UserRepository;
import com.apple.hrm.base.Service.UserService;
import com.apple.hrm.base.config.customuserdetails;
import com.apple.hrm.base.entity.User;
import com.apple.hrm.base.entity.contact;



@Service
public class Userserviceimpl implements UserService {

	@Autowired
	private UserRepository userrepo;

	@Autowired
	private Contactrepository Contrepo;

	
	public Userserviceimpl() {
		super();
	}



	public Userserviceimpl(UserRepository userrepo) {
		super();
		this.userrepo = userrepo;
	}



	@Override
	public User verfiyemailandpassword(String email) {
		User user=userrepo.findByEmail(email);
		return user;
	}



	@Override
	public User save(User user) {
		
		User u=userrepo.save(user);
		return u;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userrepo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("could not found user !!");
		}
		customuserdetails usercustomdetails=new customuserdetails(user);
		return usercustomdetails;
	}



	@Override
	public contact save(contact uscontacter) {
		contact u=Contrepo.save(uscontacter);
		return u;
	}



	
	
	
	
	
	

}
