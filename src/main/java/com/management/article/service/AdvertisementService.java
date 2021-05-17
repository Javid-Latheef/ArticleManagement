package com.management.article.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.management.article.dto.AdvertisementDTO;
import com.management.article.model.Advertisement;

public interface AdvertisementService {
	
	public ResponseEntity<Advertisement> saveAdvertisement(AdvertisementDTO ad);
	public ResponseEntity<List<Advertisement>> listAllAdvertisements();
	public ResponseEntity<HttpStatus> updateAdvertisementDetails(AdvertisementDTO adDetails);
	public ResponseEntity<HttpStatus> deleteAdvertisementDetails(long id);
	public ResponseEntity<Advertisement> findAdvertisementById(Long id);

}
