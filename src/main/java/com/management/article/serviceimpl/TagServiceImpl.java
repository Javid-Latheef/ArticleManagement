package com.management.article.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.management.article.dto.TagsDTO;
import com.management.article.model.SubCategory;
import com.management.article.model.Tags;
import com.management.article.repository.TagRepository;
import com.management.article.service.TagService;

@Service
public class TagServiceImpl implements TagService{
	@Autowired
	private TagRepository tagRepository;
	

	
	@Override
	public ResponseEntity<Tags> saveTag(TagsDTO tagsDTO) {
		ResponseEntity<Tags> response=new ResponseEntity<Tags>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Tags tags=new Tags();
			BeanUtils.copyProperties(tagsDTO, tags);
			response=new ResponseEntity<>(tagRepository.saveAndFlush(tags), HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}
	
	@Override
	public ResponseEntity<List<Tags>> listAllTags() {
		ResponseEntity<List<Tags>> response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			response=new ResponseEntity<>(tagRepository.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

}
