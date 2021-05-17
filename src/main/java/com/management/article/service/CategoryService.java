package com.management.article.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.article.dto.CategoryDTO;
import com.management.article.dto.SubCategoryDTO;
import com.management.article.model.Category;
import com.management.article.model.SubCategory;

public interface CategoryService {
	public ResponseEntity<List<Category>> listAllCategories();
	public ResponseEntity<Category> saveCategory(Category request);
	public ResponseEntity<HttpStatus> updateCategory(CategoryDTO category);
	public ResponseEntity<Category> findCategoriesById(Long id);
	public ResponseEntity<Set<SubCategory>> findSubCategoriesByCategoryId(Long id);
	public ResponseEntity<HttpStatus> deleteCategoryDetails(long id);
}
