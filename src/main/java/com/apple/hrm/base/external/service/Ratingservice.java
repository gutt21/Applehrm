package com.apple.hrm.base.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.apple.hrm.base.entity.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface Ratingservice {

	
	
	
	
	  @GetMapping("/ratings/users/{ratingid}")
	  Rating[] getallratingbyusinguserid(@PathVariable("ratingid")String name);
	 
	 
	 
	 
}
