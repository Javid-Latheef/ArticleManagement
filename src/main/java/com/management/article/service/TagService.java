package com.management.article.service;

import org.springframework.http.ResponseEntity;

import com.management.article.model.Tags;

public interface TagService {
	
	public ResponseEntity<Tags> saveTag(Tags request);
}
