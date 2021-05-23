package com.management.article.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.management.article.dto.TagsDTO;
import com.management.article.model.SubCategory;
import com.management.article.model.Tags;

public interface TagService {
	
	public ResponseEntity<Tags> saveTag(TagsDTO request);
	public ResponseEntity<List<Tags>> listAllTags();
}
