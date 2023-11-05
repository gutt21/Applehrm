package com.apple.hrm.base.entity;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Appleuser",uniqueConstraints = @UniqueConstraint(columnNames ="email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "First name field is required")
	@Size(max = 20, min = 3, message = "Size should be between 3 to 20")
	@Column(name = "First_Name")
	private String firstname;
	
	@NotBlank(message = "Last name field is required")
	@Size(max = 20, min = 3, message = "Size should be between 3 to 20")
	@Column(name = "Last_Name")
	private String  lastname;
	
	@Email(message = "{user.email.invalid}")
	@Column(name = "Email")
	private String email;
	
	//@Size(max = 20, min = 3, message = "Invalid passwors, Size should be between 3 to 20")
	@Column(name = "Password")
	private String password;
	
	@NotBlank(message="Please enter your Dob")
	private String dob;
	
	
	private String gender;
	
	
	@NotBlank(message="Please enter your phone number")
	@Pattern(regexp = ("(0/91)?[7-9][0-9]{9}"),message = "please provide valide number")
	private String phone;
	
	@NotBlank(message="Please enter your Address")
	@Size(max = 100, min = 3, message = "Size should be between 3 to 100")
	private String Address;
	
	@NotBlank(message="Please enter your about")
	@Size(max = 100, min = 20, message = "Size should be between 20 to 100")
	private String about;
	
	
	private String image;
	
	@Column(name = "Role")
	private String role;
	
	@Transient
	List<Rating> raing=new ArrayList<>();
	
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	List<contact> cont=new ArrayList<>();


	
	
	
	
	
	
	
}
