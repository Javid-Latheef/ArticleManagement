package com.management.article.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.management.article.dto.CategoryDTO;
import com.management.article.dto.SubCategoryDTO;
import com.management.article.model.Category;
import com.management.article.model.SubCategory;
import com.management.article.repository.CategoryRepository;
import com.management.article.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public ResponseEntity<Category> saveCategory(Category request) {
		ResponseEntity<Category> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Category category = categoryRepository.save(request);
			response = new ResponseEntity<>( category, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	@Override
	public ResponseEntity<HttpStatus> updateCategory( CategoryDTO categoryDTO) {
		ResponseEntity<HttpStatus> response=new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Category category = new Category();
			BeanUtils.copyProperties(categoryDTO, category);
			categoryRepository.saveAndFlush(category);
			response=new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}
	
	@Override
	public ResponseEntity<List<Category>> listAllCategories() {
		ResponseEntity<List<Category>> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			List<Category> listAllCategories = categoryRepository.findAll();
			response = new ResponseEntity<>(listAllCategories, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}
	
	@Override
	public ResponseEntity<Category> findCategoriesById(Long id) {
		ResponseEntity<Category> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Optional<Category> category = categoryRepository.findById(id);
			response = new ResponseEntity<Category>( category.get(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public ResponseEntity<Set<SubCategory>> findSubCategoriesByCategoryId(Long id) {
		ResponseEntity<Set<SubCategory>> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Optional<Category> category=categoryRepository.findById(id);
			if(category.isPresent())
			{
				response = new ResponseEntity<>(category.get()
						.getSubCategory(), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	
	@Override
	public ResponseEntity<HttpStatus> deleteCategoryDetails(long id) {
		ResponseEntity<HttpStatus> response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			categoryRepository.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		response=new ResponseEntity<HttpStatus>(HttpStatus.OK);
		return response;
	}


}
