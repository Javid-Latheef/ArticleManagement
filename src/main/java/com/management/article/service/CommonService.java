package com.management.article.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
	public ResponseEntity<HttpStatus> uploadToLocalFileSystem(MultipartFile file,long id, String item);
	public ResponseEntity<Resource> downloadFileFromLocal(long id,HttpServletRequest request);
}
