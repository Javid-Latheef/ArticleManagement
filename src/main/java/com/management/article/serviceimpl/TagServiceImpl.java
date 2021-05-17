package com.management.article.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.management.article.model.Tags;
import com.management.article.repository.TagRepository;
import com.management.article.service.TagService;

@Service
public class TagServiceImpl implements TagService{
	@Autowired
	private TagRepository tagRepository;
	
	@Override
	public ResponseEntity<Tags> saveTag(Tags request) {
		ResponseEntity<Tags> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Tags tag = tagRepository.save(request);
			response = new ResponseEntity<>( tag, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

}
