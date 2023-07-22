package com.apple.hrm.base.controller.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Userlogindto {
	
	@Column(name = "First_Name")
	private String firstname;
	@Column(name = "Last_Name")
	private String  lastname;
	@Column(name = "Email")
	private String email;
	@Column(name = "Password")
	private String password;
}
