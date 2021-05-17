package com.management.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.article.model.Tags;

@Repository
public interface TagRepository extends JpaRepository<Tags, Long>{

}
