package com.apple.hrm.base.controller.dto;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.apple.hrm.base.Service.UserService;
import com.apple.hrm.base.entity.User;
import com.apple.hrm.base.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class controller {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@Autowired
	private UserService userservice;
	
	

	@GetMapping("/")
	public String Home(Model model) {
		model.addAttribute("user", "login");
		model.addAttribute("title", "Login");
		return "login";
		
	}
	
	
	
	@GetMapping("/login")
	public String loginpage(Model model) {
		model.addAttribute("user", "login");
		model.addAttribute("title", "Login");
		return "login";
		
	}
		/*
		 * @PostMapping("/login")
	     public String validate(@ModelAttribute("user") User user) {
		 * User u=userservice.verfiyemailandpassword(user.getEmail().trim()); try {
		 * System.out.println("hello"); System.out.println(u.getPassword());
		 * System.out.println(passwordEncoder.encode(user.getPassword().trim()));
		 * if(u.getEmail().equals(user.getEmail().trim())&&u.getPassword().equals(
		 * passwordEncoder.encode(user.getPassword().trim()))) {
		 * 
		 * return "home";
		 * 
		 * }else { System.out.println(u.getPassword());
		 * System.out.println(passwordEncoder.encode(user.getPassword().trim())); return
		 * "redirect:/login?error"; } }catch(NullPointerException e) { return
		 * "redirect:/login?invalidemail"; }
		 * 	return "home";
	           }
	
		 * 
		 */
		
	
	@GetMapping("/registration")
	public String creatregistration(Model model)
	{
		User user=new User();
		model.addAttribute("user",user);
		return "registration2";
	}
	
	
	 @PostMapping("/registration")
	 public String creatregistrationnewaccount(@Valid @ModelAttribute("user") User user,BindingResult result,
			 @RequestParam(value = "agreement",defaultValue = "false")boolean agreement,
			 @RequestParam("profileimageurl") MultipartFile file,Model model) {
	
		     try {

		    	 
		    	   if(!agreement) {
		    		 
		    		 throw new Exception("you have not agreed the terms and conditions"); 
		    	   }
	
		    	    if(result.hasErrors()) {
		    		 model.addAttribute("user",user);
		    		 return "registration2";
		    	    }
		    	 
		    	     if(file.isEmpty()) {
		            	user.setImage("customer.png"); 
		             
		    	   }else {
		    		   
				         File filesave=new ClassPathResource("/static/img").getFile(); 
				         Path path=Paths.get(filesave.getAbsolutePath()+File.separator+file.getOriginalFilename()); 
				         Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
					     user.setImage(file.getOriginalFilename()); 
		            }
		    	 
		    	 
		    	 
		    	 
		    	 
			     //User u=userservice.verfiyemailandpassword(user.getEmail().trim());
		    	 user.setPassword(passwordEncoder.encode(user.getPassword()));
		    	 user.setRole("USER");
			     User uu= userservice.save(user);
			     model.addAttribute("user", new User());
			     return "redirect:/registration?success";
			 
		    	 
		     }catch(Exception e) {
		    	 model.addAttribute("user", user);
		    	 return "redirect:/registration?error";
		     }
		 
			
			/*
			 * if(user.getFirstname().trim().isEmpty()){ return
			 * "redirect:/registration?first"; } else
			 * if(user.getLastname().trim().isEmpty()) { return
			 * "redirect:/registration?last"; } else if(u!=null) { return
			 * "redirect:/registration?exist";
			 * 
			 * }else { userservice.save(user); return "redirect:/registration?success";
			 * 
			 * }
			 */
		
		 
	 }
	
}
