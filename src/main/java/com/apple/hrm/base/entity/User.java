package com.apple.hrm.base.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



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
	
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
	List<contact> cont=new ArrayList<>();


	public User() {
		super();
	}




	public User(String firstname, String lastname, String email, String password,String role,String phone,String about,String Address,String dob,String gender,String image) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role=role;
		this.about=about;
		this.phone=phone;
		this.Address=Address;
		this.dob=dob;
		this.gender=gender;
		this.image=image;
		
		
	}

	
	
	



	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public String getDob() {
		return dob;
	}




	public String getGender() {
		return gender;
	}




	public void setDob(String dob) {
		this.dob = dob;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public Integer getId() {
		return id;
	}

	
	public String getRole() {
		return role;
	}





	public String getFirstname() {
		return firstname;
	}




	public String getLastname() {
		return lastname;
	}




	public String getEmail() {
		return email;
	}




	public String getPassword() {
		return password;
	}



	public String getPhone() {
		return phone;
	}




	public String getAddress() {
		return Address;
	}




	public String getAbout() {
		return about;
	}



	
	
	
	
	
	
	
	public void setId(Integer id) {
		this.id = id;
	}




	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}




	public void setLastname(String lastname) {
		this.lastname = lastname;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public void setPassword(String password) {
		this.password = password;
	}

	

	public void setRole(String role) {
		this.role = role;
	}



	
	
	

	public List<contact> getCont() {
		return cont;
	}




	public void setCont(List<contact> cont) {
		this.cont = cont;
	}






	public void setPhone(String phone) {
		this.phone = phone;
	}




	public void setAddress(String address) {
		Address = address;
	}




	public void setAbout(String about) {
		this.about = about;
	}







	



	
	
	
	
	
	
	
}
