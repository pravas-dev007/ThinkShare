package com.pravas.thinkshare.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pravas.thinkshare.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
	

}
