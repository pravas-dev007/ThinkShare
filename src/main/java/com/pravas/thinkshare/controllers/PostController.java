package com.pravas.thinkshare.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pravas.thinkshare.config.AppConstants;
import com.pravas.thinkshare.payloads.ApiResponse;
import com.pravas.thinkshare.payloads.PostDto;
import com.pravas.thinkshare.payloads.PostResponse;
import com.pravas.thinkshare.services.FileService;
import com.pravas.thinkshare.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto, 
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		PostDto createdPost=this.postService.craetePost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		
		List<PostDto> posts=this.postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	//get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		
		List<PostDto> posts=this.postService.getPostByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber", defaultValue=AppConstants.PAGE_NUMBER, required=false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue=AppConstants.PAGE_SIZE, required=false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue=AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value="sortDir", defaultValue=AppConstants.SORT_DIR, required=false) String sortDir){
		
		PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		
		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
	}
	
	//get posts details by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		
		PostDto postDto = this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	//delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		
		this.postService.deletePost(postId);
		
		return new ApiResponse("Post deleted successfully!!!", true);
	}
	
	//update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		
		PostDto updatePost=this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	//search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
		
		List<PostDto> posts = this.postService.searchPosts(keywords);
		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		PostDto postDto=this.postService.getPostById(postId);
		
		String fileName=this.fileService.uploadImage(path, image);
		
		postDto.setImage(fileName);
		PostDto updated=this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updated, HttpStatus.OK);
	}
}
