package com.management.article.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.management.article.model.Article;
import com.management.article.model.Category;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagsDTO extends CommonDTO{
	
	private String text;
	
	private Article articleData;

	public Article getArticleData() {
		return articleData;
	}

	public void setArticleData(Article articleData) {
		this.articleData = articleData;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
