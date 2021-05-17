package com.management.article.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.management.article.dto.ArticleDTO;
import com.management.article.model.Article;
import com.management.article.model.Category;
import com.management.article.model.Tags;


public interface ArticleService {
	public ResponseEntity<Article> saveArticle(Article request);
	public ResponseEntity<?> listAllArticles();
	public ResponseEntity<Article> findArticleById(Long id);
	public ResponseEntity<HttpStatus> deleteArticleDetails(long authorId);
	public ResponseEntity<HttpStatus> updateArticle(ArticleDTO article);	
}
