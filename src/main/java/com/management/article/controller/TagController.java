package com.management.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.article.dto.TagsDTO;
import com.management.article.model.SubCategory;
import com.management.article.model.Tags;

import com.management.article.service.TagService;

@CrossOrigin
@RestController
@RequestMapping("/tag")
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	@PostMapping(value = "/saveTag")
	public ResponseEntity<Tags> saveCategory(@RequestBody TagsDTO request) {
		return tagService.saveTag(request);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Tags>> listAllTags()
	{
		return tagService.listAllTags();
	}

}
