package com.management.article.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

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

import com.management.article.dto.SubCategoryDTO;
import com.management.article.model.Article;
import com.management.article.model.SubCategory;
import com.management.article.repository.SubCategoryRepository;
import com.management.article.service.CommonService;
import com.management.article.service.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService,CommonService {

	@Autowired
	private SubCategoryRepository subCategoryRepo;
	@Qualifier("subCategoryfileBasePath")
	@Autowired
	private String fileBasePath;
	@Override
	public ResponseEntity<SubCategory> saveSubCategoty(SubCategoryDTO subCategoryDTO) {
		ResponseEntity<SubCategory> response=new ResponseEntity<SubCategory>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			SubCategory subCategory=new SubCategory();
			BeanUtils.copyProperties(subCategoryDTO, subCategory);
			response=new ResponseEntity<>(subCategoryRepo.saveAndFlush(subCategory), HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

	@Override
	public ResponseEntity<List<SubCategory>> listAllSubCategories() {
		ResponseEntity<List<SubCategory>> response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			response=new ResponseEntity<>(subCategoryRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

	@Override
	public ResponseEntity<HttpStatus> updateSubCategory( SubCategoryDTO subCategoryDTO) {
		ResponseEntity<HttpStatus> response=new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			SubCategory subCategory=new SubCategory();
			BeanUtils.copyProperties(subCategoryDTO, subCategory);
			subCategoryRepo.saveAndFlush(subCategory);
			response=new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

	@Override
	public ResponseEntity<HttpStatus> deleteSubCategory(long subCategoryId) {
		ResponseEntity<HttpStatus> response=new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			subCategoryRepo.deleteById(subCategoryId);
			response=new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}

	@Override
	public ResponseEntity<HttpStatus> uploadToLocalFileSystem(MultipartFile file, long id, String item) {
		ResponseEntity<HttpStatus> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		String fileName = String.format("%d_%s", id, StringUtils.cleanPath(file.getOriginalFilename()));
		Path path = Paths.get(fileBasePath + fileName);
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/").path(fileName).toUriString();
			response = new ResponseEntity<>(HttpStatus.OK);
			SubCategory subCategoryObject = subCategoryRepo.getOne(id);
			subCategoryObject.setSubCategoryIcon(fileName);
			subCategoryRepo.save(subCategoryObject);

		} catch (IOException e) {
			e.printStackTrace();

		}

		return response;

	}

	@Override
	public ResponseEntity<Resource> downloadFileFromLocal(long id, HttpServletRequest request) {
		SubCategory subCategoryObj = subCategoryRepo.getOne(id);
		Path path = Paths.get(fileBasePath + subCategoryObj.getSubCategoryIcon());
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

}
