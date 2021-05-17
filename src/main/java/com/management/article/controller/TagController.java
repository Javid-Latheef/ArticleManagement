package com.management.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.article.model.Tags;

import com.management.article.service.TagService;

@CrossOrigin
@RestController
@RequestMapping("/tag")
public class TagController {
	
	@Autowired
	private TagService tagService;
	
	@PostMapping(value = "/saveTag")
	public ResponseEntity<Tags> saveCategory(@RequestBody Tags request) {
		return tagService.saveTag(request);
	}

}
