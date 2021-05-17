package com.management.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.article.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

}
