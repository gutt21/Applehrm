package com.apple.hrm.base.entity;

import jakarta.persistence.Entity;
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
public class Rating {

	private String ratingid;
	
	private String userid;
	
	private int rating;
	
	private String feedback;
	
	private String Hotelid;
	
	
}
