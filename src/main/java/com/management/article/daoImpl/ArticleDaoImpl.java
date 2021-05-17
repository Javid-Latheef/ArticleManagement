package com.management.article.daoImpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.management.article.model.Article;

@Repository
public class ArticleDaoImpl {
	@Qualifier("session")
	@Autowired
	private Session session;

	public List<Article> getArticleDetails() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> contactRoot = criteria.from(Article.class);
		criteria.select(contactRoot);
		return session.createQuery(criteria).getResultList();
	}

}