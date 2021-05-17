package com.management.article.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.management.article.dto.ArticleDTO;
import com.management.article.model.Advertisement;
import com.management.article.model.Article;
import com.management.article.model.Author;
import com.management.article.model.Category;
import com.management.article.model.SubCategory;
import com.management.article.model.Tags;
import com.management.article.repository.AdvertisementRepository;
import com.management.article.repository.ArticleRepository;
import com.management.article.repository.AuthorRepository;
import com.management.article.repository.CategoryRepository;
import com.management.article.repository.SubCategoryRepository;
import com.management.article.service.ArticleService;
import com.management.article.service.CommonService;

@Service
public class ArticleServiceImpl implements ArticleService,CommonService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SubCategoryRepository subCategoryRepo;
	@Autowired
	private AuthorRepository authorRepo;
	@Autowired
	private AdvertisementRepository advertisementRepo;
	
	@Qualifier("articlefileBasePath")
	@Autowired
	private String fileBasePath;

	@Override
	public ResponseEntity<Article> saveArticle(Article request) {
		ResponseEntity<Article> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			response = new ResponseEntity<>(articleRepository.save(request), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}


	@Override
	public ResponseEntity<?> listAllArticles() {
		ResponseEntity<?> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			List<Article> test = articleRepository.findAll();

			response = new ResponseEntity<>(test, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public ResponseEntity<Article> findArticleById(Long id) {
		ResponseEntity<Article> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Optional<Article> article = articleRepository.findById(id);
			response = new ResponseEntity<Article>( article.get(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public ResponseEntity<HttpStatus> uploadToLocalFileSystem(MultipartFile file, long articleId, String item) {
		ResponseEntity<HttpStatus> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		String fileName = String.format("%d_%s", articleId, item + '_' + StringUtils.cleanPath(file.getOriginalFilename()));
		Path path = Paths.get(fileBasePath + fileName);
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/").path(fileName).toUriString();
			response = new ResponseEntity<>(HttpStatus.OK);
			if(item.equals("category")) {
				Category categoryObject = categoryRepository.getOne(articleId); 
				categoryObject.setCategoryIcon(fileBasePath + fileName);
				categoryRepository.save(categoryObject);
				
			}
			else if(item.equals("subcategory")) {
				SubCategory subcategoryObject = subCategoryRepo.getOne(articleId); 
				subcategoryObject.setSubCategoryIcon(fileBasePath + fileName);
				subCategoryRepo.save(subcategoryObject);
			}
			else if(item.equals("author")) {
				Author authorObject = authorRepo.getOne(articleId); 
				authorObject.setProfileIcon(fileBasePath + fileName);
				authorRepo.save(authorObject);
			}
			else if(item.equals("advertisement")) {
				Advertisement advertisementObject = advertisementRepo.getOne(articleId); 
				advertisementObject.setCompanyAd(fileBasePath + fileName);
				advertisementRepo.save(advertisementObject);
			}
			else {
				Article articleObject = articleRepository.getOne(articleId);
				articleObject.setFileName(fileBasePath + fileName);
				articleRepository.save(articleObject);
			}

		} catch (IOException e) {
			e.printStackTrace();

		}

		return response;

	}

	@Override
	public ResponseEntity<Resource> downloadFileFromLocal(long articleId, HttpServletRequest request) {
		Article articleObj = articleRepository.getOne(articleId);
		Path path = Paths.get(fileBasePath + articleObj.getFileName());
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(
							request.getServletContext().getMimeType(resource.getFile().getAbsolutePath())))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Override
	public ResponseEntity<HttpStatus> updateArticle(ArticleDTO article) {
		ResponseEntity<HttpStatus> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		try {
			Optional<Article> existingArticle = articleRepository.findById(article.getId());
			existingArticle.ifPresentOrElse(data -> {
				BeanUtils.copyProperties(article, data);
				articleRepository.save(data);

			}, () -> {
				throw new RuntimeException();
			});

			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public ResponseEntity<HttpStatus> deleteArticleDetails(long id) {
		ResponseEntity<HttpStatus> response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			articleRepository.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		response=new ResponseEntity<HttpStatus>(HttpStatus.OK);
		return response;
	}

}
