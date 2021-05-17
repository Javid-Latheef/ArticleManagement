package com.management.article.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.management.article.dto.AuthorDTO;
import com.management.article.model.Author;
import com.management.article.model.Category;
import com.management.article.repository.AuthorRepository;
import com.management.article.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepository authorRepo;
	@Override
	public ResponseEntity<Author> saveAuthor(AuthorDTO authorDTO) {
		ResponseEntity<Author> response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Author author=new Author();
			BeanUtils.copyProperties(authorDTO, author);
			response=new ResponseEntity<Author>(authorRepo.save(author), HttpStatus.CREATED);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

	@Override
	public ResponseEntity<List<Author>> listAllAuthors() {
		ResponseEntity<List<Author>> response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			response=new ResponseEntity<List<Author>>(authorRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

	@Override
	public ResponseEntity<HttpStatus> updateAuthorDetails(AuthorDTO authorDetails) {
		ResponseEntity<HttpStatus> response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Author author=new Author();
			BeanUtils.copyProperties(authorDetails, author);
			authorRepo.saveAndFlush(author);
			response=new ResponseEntity<>( HttpStatus.OK);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

	@Override
	public ResponseEntity<HttpStatus> deleteAuthorDetails(long authorId) {
		ResponseEntity<HttpStatus> response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			authorRepo.deleteById(authorId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		response=new ResponseEntity<HttpStatus>(HttpStatus.OK);
		return response;
	}
	
	@Override
	public ResponseEntity<Author> findAuthorById(Long id) {
		ResponseEntity<Author> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Optional<Author> author = authorRepo.findById(id);
			response = new ResponseEntity<Author>( author.get(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

}
