package com.management.article.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.management.article.dto.AdvertisementDTO;
import com.management.article.model.Advertisement;
import com.management.article.repository.AdvertisementRepository;
import com.management.article.service.AdvertisementService;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {
	
	@Autowired
	private AdvertisementRepository advertisementRepo;
	
	@Override
	public ResponseEntity<Advertisement> saveAdvertisement(AdvertisementDTO advertisementDTO) {
		ResponseEntity<Advertisement> response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Advertisement advertisement=new Advertisement();
			BeanUtils.copyProperties(advertisementDTO, advertisement);
			response=new ResponseEntity<Advertisement>(advertisementRepo.save(advertisement), HttpStatus.CREATED);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}
	
	@Override
	public ResponseEntity<List<Advertisement>> listAllAdvertisements() {
		ResponseEntity<List<Advertisement>> response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			response=new ResponseEntity<List<Advertisement>>(advertisementRepo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}
	
	@Override
	public ResponseEntity<HttpStatus> updateAdvertisementDetails(AdvertisementDTO advertisementDetails) {
		ResponseEntity<HttpStatus> response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Advertisement advertisement=new Advertisement();
			BeanUtils.copyProperties(advertisementDetails, advertisement);
			advertisementRepo.saveAndFlush(advertisement);
			response=new ResponseEntity<>( HttpStatus.OK);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return response;
	}
	
	@Override
	public ResponseEntity<HttpStatus> deleteAdvertisementDetails(long id) {
		ResponseEntity<HttpStatus> response=new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			advertisementRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		response=new ResponseEntity<HttpStatus>(HttpStatus.OK);
		return response;
	}
	
	@Override
	public ResponseEntity<Advertisement> findAdvertisementById(Long id) {
		ResponseEntity<Advertisement> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Optional<Advertisement> advertisement = advertisementRepo.findById(id);
			response = new ResponseEntity<Advertisement>( advertisement.get(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

}
