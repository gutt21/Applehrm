package com.apple.hrm.base.controller.dto;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.apple.hrm.base.Repository.Contactrepository;
import com.apple.hrm.base.Repository.UserRepository;
import com.apple.hrm.base.entity.User;
import com.apple.hrm.base.entity.contact;

@RestController
public class Searchcontroller {

	@Autowired
	private UserRepository userrpo;
	
	@Autowired
	private Contactrepository contrepo;
	
	
	
	
	
	
	
	@GetMapping("/search/{query}")
	public ResponseEntity<?> Searchcontacts(@PathVariable("query")String query,Principal principle){
		  String name=principle.getName();
	      User user	=userrpo.findByEmail(name);
	      System.out.println(query);
		  List<contact> contact =contrepo.findByNameContainingAndUser(query, user);
		  return ResponseEntity.ok(contact);
		
	}
	
	
	
	
	
	
	
	
	
}
