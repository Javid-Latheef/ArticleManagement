package com.management.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.article.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

}
