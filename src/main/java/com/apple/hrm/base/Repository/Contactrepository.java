package com.apple.hrm.base.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apple.hrm.base.entity.User;
import com.apple.hrm.base.entity.contact;



public interface Contactrepository extends JpaRepository<contact, Integer> {

	
	Optional<contact> findById(Integer id);
	List<contact> findByNameContainingAndUser(String name,User user);
	
	
}
