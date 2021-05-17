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

import com.management.article.dto.AdvertisementDTO;
import com.management.article.model.Advertisement;
import com.management.article.service.AdvertisementService;

@CrossOrigin
@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {
	
	@Autowired
	private AdvertisementService service;
	
	@PostMapping("/save")
	public ResponseEntity<Advertisement> saveAdvertisement(@RequestBody AdvertisementDTO request)
	{
		return service.saveAdvertisement(request); 
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Advertisement>> getAdvertisementList()
	{
		return service.listAllAdvertisements();
	}
	
	@PutMapping("/update")
	public ResponseEntity<HttpStatus> updateAdvertisement(@RequestBody AdvertisementDTO ad)
	{
		return service.updateAdvertisementDetails(ad);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> deleteAdvertisement(@RequestParam long id)
	{
		return service.deleteAdvertisementDetails(id);
	}
	
	@GetMapping("/getAdvertisementById")
	public ResponseEntity<Advertisement> getAdvertisementById(@RequestParam long id)
	{
		return service.findAdvertisementById(id);
		
	}

}
