package com.apple.hrm.base.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apple.hrm.base.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
    User findByEmail(String email);
	
}
