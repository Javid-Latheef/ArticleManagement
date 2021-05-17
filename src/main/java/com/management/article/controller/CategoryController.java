package com.management.article.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.article.dto.ArticleDTO;
import com.management.article.dto.CategoryDTO;
import com.management.article.model.Category;
import com.management.article.model.SubCategory;
import com.management.article.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping(value = "/saveCategory")
	public ResponseEntity<Category> saveCategory(@RequestBody Category request) {
		return categoryService.saveCategory(request);
	}
	
	@PutMapping("/updateCategory")
	public ResponseEntity<HttpStatus> updateCategory(@RequestBody CategoryDTO category)
	{
		return categoryService.updateCategory(category);
	}
	
	@GetMapping("/getAllCategories")
	public ResponseEntity<List<Category>> listAllCategories()
	{
		return categoryService.listAllCategories();
		
	}
	
	@GetMapping("/getCategoriesById")
	public ResponseEntity<Category> getCategoriesById(@RequestParam long id)
	{
		return categoryService.findCategoriesById(id);
		
	}
	
	@GetMapping("/getSubCategoriesById")
	public ResponseEntity<Set<SubCategory>> getSubCategoriesByCategoryId(@RequestParam long id)
	{
		return categoryService.findSubCategoriesByCategoryId(id);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> deleteAuthor(@RequestParam long id)
	{
		return categoryService.deleteCategoryDetails(id);
	}
}
