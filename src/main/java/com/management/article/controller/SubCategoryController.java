package com.management.article.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.management.article.dto.SubCategoryDTO;
import com.management.article.model.SubCategory;
import com.management.article.service.CommonService;
import com.management.article.service.SubCategoryService;

@CrossOrigin
@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {

	@Autowired
	private SubCategoryService service;
	
	@Qualifier("subCategoryCommonService")
	@Autowired
	private CommonService commonService;
	
	@PostMapping("/save")
	public ResponseEntity<SubCategory> save(@RequestBody SubCategoryDTO request)
	{
		return service.saveSubCategoty(request);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<SubCategory>> listAllSubCategories()
	{
		return service.listAllSubCategories();
	}
	
	@PutMapping("/update")
	public ResponseEntity<HttpStatus> updateSubCategory(@RequestBody SubCategoryDTO request)
	{
		return service.updateSubCategory(request);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> deleteSubCategory(@RequestParam long id)
	{
		return service.deleteSubCategory(id);
	}
	
}
