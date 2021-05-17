package com.management.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.article.dto.AuthorDTO;
import com.management.article.model.Author;
import com.management.article.model.Category;
import com.management.article.service.AuthorService;

@CrossOrigin
@RestController
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private AuthorService service;
	
	@PostMapping("/save")
	public ResponseEntity<Author> saveAuthor(@RequestBody AuthorDTO request)
	{
		return service.saveAuthor(request); 
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Author>> getAuthorList()
	{
		return service.listAllAuthors();
	}
	
	@PutMapping("/update")
	public ResponseEntity<HttpStatus> updateAuthor(@RequestBody AuthorDTO author)
	{
		return service.updateAuthorDetails(author);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> deleteAuthor(@RequestParam long id)
	{
		return service.deleteAuthorDetails(id);
	}
	
	@GetMapping("/getAuthorById")
	public ResponseEntity<Author> getAuthorById(@RequestParam long id)
	{
		return service.findAuthorById(id);
		
	}
}
