package com.apple.hrm.base.controller.dto;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.apple.hrm.base.Repository.Contactrepository;
import com.apple.hrm.base.Service.UserService;
import com.apple.hrm.base.entity.User;
import com.apple.hrm.base.entity.contact;
import com.apple.hrm.base.helper.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/user")
public class Usercontroller {

	   @Autowired
	   private BCryptPasswordEncoder passwordEncoder;
	
	
	   @Autowired
	   private UserService userservice;
	   
	   @Autowired
	   private Contactrepository contrepo;
	  
	   @ModelAttribute
	   public void addcommon(Model model,Principal principle) {
		   
		   String email= principle.getName();
		   User user=userservice.verfiyemailandpassword(email);
		   model.addAttribute("user",user);
		   
	   }
	   
	    @GetMapping("/home")
		public String homepage(Model model)
		{
		  
		   return "normal/dashboard";
		}

	  
	    @GetMapping("/add-contact")
		public String Add_contect(Model model)
		{
	    	model.addAttribute("contact", new contact());
	    	model.addAttribute("title", "Add-Contact");
			return "normal/Contact";
		}
	
	    
	    @PostMapping("/process-contact")
	    public String add_contact_form(@Valid @ModelAttribute ("contact") contact contact,BindingResult result,
	    		@RequestParam("profileimageurl") MultipartFile file,Model model,
	    		Principal principle) {
	      
	    	
	    	try {
	    		
	    	     String emailid=principle.getName();
	             User user= userservice.verfiyemailandpassword(emailid);	  
	           
	             //file upload section
	             if(result.hasErrors()) {
	            	 return "normal/Contact";
	             }
	           
	             if(file.isEmpty()) {
	            	throw new Exception("upload file field sould be required ");  
	            	
	              }else {
	            	 
				     contact.setImageurl(file.getOriginalFilename()); 
			         File filesave=new ClassPathResource("/static/img").getFile(); 
			         Path path=Paths.get(filesave.getAbsolutePath()+File.separator+file.
				     getOriginalFilename()); Files.copy(file.getInputStream(),path,
					 StandardCopyOption.REPLACE_EXISTING);
	            }
	            contact.setUser(user);
	    	    user.getCont().add(contact);
	    	    userservice.save(user);
	    	    return "redirect:/user/add-contact?success";
	    	}catch(Exception e) {
	    		 return "redirect:/user/add-contact?error";
	    	}
	    }
	    
	
	    
	    
	    
	    @GetMapping("/view-contact")
	    public String view_all_contact(Model model,Principal principle) {
	   
	        String name=principle.getName();
	    	User user=userservice.verfiyemailandpassword(name);
	    	List<contact> Contact=user.getCont();
	    	//Pageable pageable=PageRequest.of(page, 3);
	    	
	        model.addAttribute("contact", Contact);
	        model.addAttribute("title", "View-Contact");
	    	return "normal/viewcontact";
	    }
	
	    
	  
	    
	    @GetMapping("/view-contact/{id}")
	    public String show_single_id_detailse(@PathVariable("id") Integer integ,Model model,Principal principle)
	    {
	    	     Optional<contact> optiopnal=contrepo.findById(integ);
	    	     contact Contact =optiopnal.get();
	    	     
	    	     String name=principle.getName();
	 	    	 User user=userservice.verfiyemailandpassword(name);
	    	     
	    	     if(user.getId()==Contact.getUser().getId())
	    	     model.addAttribute("contact", Contact);
	    	     model.addAttribute("title",Contact.getName());
	    	    return "normal/contactdetails";
	    }
	      
	    
	    @GetMapping("/delete/{id}")
	    public String deletecontact(@PathVariable("id") Integer integ,Model model) {
	    	
	    	Optional<contact> optiopnal=contrepo.findById(integ);
   	        contact Contact =optiopnal.get();
   	        Contact.setUser(null);
   	        contrepo.delete(Contact);
   	        
   	        return "redirect:/user/view-contact?delete";
	    	
	    }
	    
	    @PostMapping("/update/{id}")
	    public String updatecontact(@PathVariable("id") Integer integ,Model model) {
	    	
	    	Optional<contact> optiopnal=contrepo.findById(integ);
   	        contact Contact =optiopnal.get();
   	        
   	        model.addAttribute("contact", Contact);
   	        model.addAttribute("title", "Update contact");
   	        
   	        
   	        return "normal/Update";
	    	
	    }
 
	  
	    @PostMapping("/updated-process")
	    public String updatecontacthandler(@Valid @ModelAttribute("contact") contact contact,BindingResult result,
	    		@RequestParam("profileimageurl") MultipartFile file,Model model,Principal principle)
	    {
	    	
	    	String emailid=principle.getName();
            User user= userservice.verfiyemailandpassword(emailid);	
            Optional<contact> optiopnal=contrepo.findById(contact.getId());
   	        contact oldContact =optiopnal.get();
	    	
	    	try {
	             //file upload section
	             if(result.hasErrors()) {
	            	 return "normal/Update";
	             }
	             
	             if(!file.isEmpty()) {
	            	 
	            	 //old image delete
	            	  File delete=new ClassPathResource("/static/img").getFile();
	            	  File file1=new File(delete, oldContact.getImageurl());
	            	  file1.delete();
	            	 
	            	 // new image update
			         File filesave=new ClassPathResource("/static/img").getFile(); 
			         Path path=Paths.get(filesave.getAbsolutePath()+File.separator+file.getOriginalFilename());
			         Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
				     contact.setImageurl(file.getOriginalFilename()); 
	           
	             }else {
	            	 //old image add 
	            	 contact.setImageurl(oldContact.getImageurl());
				    
	            }
                contact.setUser(user);
	    	    userservice.save(contact);
	    	    
	    	    return "redirect:/user/view-contact/"+contact.getId()+"?success";
	    	    
	    	}catch(Exception e) {
	    		
	    		return "redirect:/user/view-contact/"+contact.getId()+"?error";
	    	}
	    	
	    	
	    }
	    
	    
	    @GetMapping("/Profile")
	    public String ProfileShow(Model model,Principal principle) {
	    	String email=principle.getName();
	    	User user=userservice.verfiyemailandpassword(email);
	    	model.addAttribute("user", user);
	    	model.addAttribute("title", "User Profile");
	    	return "normal/Profile";
	    }
	    
	    
	    
	    @GetMapping("/setting")
	    public String settingget(Model model,Principal principle) {
	    	String email=principle.getName();
	    	User user=userservice.verfiyemailandpassword(email);
	    	model.addAttribute("user", user);
	    	model.addAttribute("title", "User Profile");
	    	
	    	return "normal/setting/setting";
	    }
	    
	    

	    
	    @GetMapping("/Change-password")
	    public String Change_password(Model model,Principal principle) {
	    	String email=principle.getName();
	    	User user=userservice.verfiyemailandpassword(email);
	    	model.addAttribute("user", user);
	    	model.addAttribute("title", "User Profile");
	    	
	    	return "normal/setting/Changepassword";
	    }
	    
	    
	    
	    
	    
	    
	    @PostMapping("/Change-password")
	    public String chenagepassword(@RequestParam("currentpass")String current,
	    		@RequestParam("newpass")String newp,
	    		@RequestParam("conformnewpass")String newconf,Principal principal) {
	    	
	    	String email=principal.getName();
	    	User user=userservice.verfiyemailandpassword(email);
	    	
	    	
	    	if(!passwordEncoder.matches(current,user.getPassword())) {
	    		
	    		return "redirect:/user/Change-password?oldmatch";
	    	}
	    	
	    	
	    	if(!newp.equals(newconf)) {
	    		
	    		return "redirect:/user/Change-password?newconfm";
	    		
	    		
	    	}else {
	    		
	    		user.setPassword(passwordEncoder.encode(newp));
	    		userservice.save(user);
	    		return "redirect:/user/Change-password?success";
	    	}
	    	
	    	
	    	
	    	
	    	
	    }
	    
	    
	    
	    
	    
	    
	    
	    
}
