package com.management.article.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.article.dto.SubCategoryDTO;
import com.management.article.model.SubCategory;

public interface SubCategoryService {

	public ResponseEntity<SubCategory> saveSubCategoty(SubCategoryDTO subCategory);
	public ResponseEntity<List<SubCategory>> listAllSubCategories();
	public ResponseEntity<HttpStatus> updateSubCategory(SubCategoryDTO subCategory);
	public ResponseEntity<HttpStatus> deleteSubCategory(long subCategoryId);
}
