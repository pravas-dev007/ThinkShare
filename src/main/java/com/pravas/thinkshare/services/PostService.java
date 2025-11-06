package com.pravas.thinkshare.services;

import java.util.List;

import com.pravas.thinkshare.entities.Post;
import com.pravas.thinkshare.payloads.PostDto;
import com.pravas.thinkshare.payloads.PostResponse;

public interface PostService {
	
	//create
	PostDto craetePost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all posts
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//get single post
	PostDto getPostById(Integer postId);
	
	//get all post by categories
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);

}
