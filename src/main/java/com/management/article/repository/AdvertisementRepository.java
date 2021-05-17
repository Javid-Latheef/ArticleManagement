package com.management.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.article.model.Advertisement;



public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

}
