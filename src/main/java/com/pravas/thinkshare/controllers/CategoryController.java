package com.pravas.thinkshare.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pravas.thinkshare.payloads.ApiResponse;
import com.pravas.thinkshare.payloads.CategoryDto;
import com.pravas.thinkshare.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService catService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto category = this.catService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(category, HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
		CategoryDto category = this.catService.updateCategory(categoryDto, catId);
		
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		this.catService.deleteCategory(catId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully!!!", true), HttpStatus.OK);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
		CategoryDto catDto=this.catService.getCategory(catId);
		
		return new ResponseEntity<CategoryDto>(catDto, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		List<CategoryDto> catDto=this.catService.getCategories();
		
		return ResponseEntity.ok(catDto);
	}

}
