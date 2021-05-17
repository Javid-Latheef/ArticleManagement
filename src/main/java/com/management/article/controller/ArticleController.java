package com.management.article.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.management.article.daoImpl.ArticleDaoImpl;
import com.management.article.dto.ArticleDTO;
import com.management.article.model.Article;
import com.management.article.model.Category;
import com.management.article.model.Tags;
import com.management.article.service.ArticleService;
import com.management.article.service.CommonService;

@CrossOrigin
@RestController
public class ArticleController {

	@Autowired
	private ArticleService articleManager;
	
	@Qualifier("articleCommonService")
	@Autowired
	private CommonService commonService;

	@PostMapping(value = "/saveArticle")
	public ResponseEntity<Article> saveArtcle(@RequestBody Article request) {
		return articleManager.saveArticle(request);
	}
	

	@GetMapping("/getAllArticles")
	public ResponseEntity<?> listAllArticles()
	{
		return articleManager.listAllArticles();
		
	}
	
	@GetMapping("/getArticleById")
	public ResponseEntity<Article> getArticleById(@RequestParam long id)
	{
		return articleManager.findArticleById(id);
		
	}
	
	@PostMapping("/upload")
	public ResponseEntity<HttpStatus> uploadToLocalFileSystem(@RequestParam("file") MultipartFile file,@RequestParam long articleId, @RequestParam String item) {
		return commonService.uploadToLocalFileSystem(file, articleId, item);
	}
	
	@GetMapping("/download/{articleId:.+}")
	public ResponseEntity<Resource> downloadFileFromLocal(@PathVariable long articleId,HttpServletRequest request) {
		return commonService.downloadFileFromLocal(articleId, request);
	}
	
	@PutMapping("/updateArticle")
	public ResponseEntity<HttpStatus> updateArticle(@RequestBody ArticleDTO article)
	{
		return articleManager.updateArticle(article);
	}
	
	@DeleteMapping("/deleteArticle")
	public ResponseEntity<HttpStatus> deleteArticle(@RequestParam long id)
	{
		return articleManager.deleteArticleDetails(id);
	}
	
	
	
	
	@Autowired
	private ArticleDaoImpl articleDao;
	@GetMapping("/hibernateTest")
	public ResponseEntity<List<Article>> getArticles()
	{
		return new ResponseEntity<>(articleDao.getArticleDetails(), HttpStatus.OK);
	}
	
}
