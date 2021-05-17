package com.management.article.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.article.dto.AuthorDTO;
import com.management.article.model.Author;
import com.management.article.model.Category;

public interface AuthorService {

	public ResponseEntity<Author> saveAuthor(AuthorDTO author);
	public ResponseEntity<List<Author>> listAllAuthors();
	public ResponseEntity<HttpStatus> updateAuthorDetails(AuthorDTO authorDetails);
	public ResponseEntity<HttpStatus> deleteAuthorDetails(long authorId);
	public ResponseEntity<Author> findAuthorById(Long id);
}
