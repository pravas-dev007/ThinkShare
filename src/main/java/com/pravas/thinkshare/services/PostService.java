package com.pravas.thinkshare.services;

import java.util.List;

import com.pravas.thinkshare.entities.Post;
import com.pravas.thinkshare.payloads.PostDto;

public interface PostService {
	
	//create
	PostDto craetePost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update
	Post updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all posts
	List<Post> getAllPost();
	
	//get single post
	Post getPostById(Integer postId);
	
	//get all post by categories
	List<Post> getPostByCategory(Integer categoryId);
	
	//get all posts by user
	List<Post> getPostsByUser(Integer userId);
	
	//search posts
	List<Post> searchPosts(String keyword);

}
