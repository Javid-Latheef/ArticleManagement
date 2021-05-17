package com.management.article.configurations;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Bean(name = "session")
	public Session getSession()
	{
		return entityManagerFactory.unwrap(SessionFactory.class).openSession();
	}
	
}
