package com.pravas.thinkshare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pravas.thinkshare.entities.Category;
import com.pravas.thinkshare.entities.Post;
import com.pravas.thinkshare.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
}
