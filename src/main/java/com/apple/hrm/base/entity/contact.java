package com.apple.hrm.base.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "deatils")
public class contact {

	
	
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer id;
	
	 @NotBlank(message="Please enter your phone number")
	 @Pattern(regexp = ("(0/91)?[7-9][0-9]{9}"),message = "please provide valide number")
	 private String phonenumber;
	
	 @NotBlank(message="Please enter your Address")
	 private String address;
	 
	 @NotBlank(message="Please enter your Email")
	 @Email(message = "{user.email.invalid}")
	 private String email;
	 
	 @NotBlank(message="Please enter your Name")
	 @Size(max = 20, min = 3, message = "Size should be between 3 to 20")
	 private String name;
	 
	 
	 @NotBlank(message="Please enter your NickName")
	 @Size(max = 20, min = 3, message = "Size should be between 3 to 20")
	 private String nickname;
	 
	 
	 @NotBlank(message="Please enter your colleage name")
	 @Size(max = 100, min = 3, message = "Size should be between 3 to 100")
	 private String colleage;
	 
	 private String imageurl;
	 
	 @Column(length = 50000)
	 private String descripation;
	  
	 @ManyToOne
	 @JsonIgnore
	 private User user;
	
	
	
	
	public contact() {
		super();
	}
	
	
	public contact(String phonenumber, String address, String email, String name, String nickname, String colleage,
			String imageurl, String descripation) {
		super();
		this.phonenumber = phonenumber;
		this.address = address;
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.colleage = colleage;
		this.imageurl = imageurl;
		this.descripation = descripation;
	}
	
	
	public Integer getId() {
		return id;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getNickname() {
		return nickname;
	}
	public String getColleage() {
		return colleage;
	}
	public String getImageurl() {
		return imageurl;
	}
	public String getDescripation() {
		return descripation;
	}
	
	public User getUser() {
		return user;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setColleage(String colleage) {
		this.colleage = colleage;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public void setDescripation(String descripation) {
		this.descripation = descripation;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "contact [id=" + id + ", phonenumber=" + phonenumber + ", address=" + address + ", email=" + email
				+ ", name=" + name + ", nickname=" + nickname + ", colleage=" + colleage + ", imageurl=" + imageurl
				+ ", descripation=" + descripation + ", user=" + user + "]";
	}
	
	
	
	
}
