package com.pravas.thinkshare.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pravas.thinkshare.entities.Category;
import com.pravas.thinkshare.entities.Post;
import com.pravas.thinkshare.entities.User;
import com.pravas.thinkshare.exceptions.ResourceNotFoundException;
import com.pravas.thinkshare.payloads.PostDto;
import com.pravas.thinkshare.repositories.CategoryRepo;
import com.pravas.thinkshare.repositories.PostRepo;
import com.pravas.thinkshare.repositories.UserRepo;
import com.pravas.thinkshare.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto craetePost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user=this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		Category category=this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImage("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public Post updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
