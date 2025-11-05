package com.pravas.thinkshare.payloads;

import java.util.Date;

import com.pravas.thinkshare.entities.Category;
import com.pravas.thinkshare.entities.User;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private String title;
	
	private String content;
	
	private String image;
	
	private Date addedDate;
	
	
	private CategoryDto category;
	
	
	private UserDto user;

}
