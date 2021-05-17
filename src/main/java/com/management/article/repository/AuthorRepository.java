package com.management.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.article.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
