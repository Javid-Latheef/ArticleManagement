package com.management.article.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.management.article.model.Category;
import com.management.article.model.SubCategory;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String fileName;
	private long categoryId;
	private String title;
	private String description;
	private Category category;
	private Date uploadedDate;
	private String redirectUrl;
	private String photoCredit;
	private SubCategory subCategory;
	private String footerText;
	private String footerHexCode;
	
	public String getFooterText() {
		return footerText;
	}

	public void setFooterText(String footerText) {
		this.footerText = footerText;
	}

	public String getFooterHexCode() {
		return footerHexCode;
	}

	public void setFooterHexCode(String footerHexCode) {
		this.footerHexCode = footerHexCode;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getPhotoCredit() {
		return photoCredit;
	}

	public void setPhotoCredit(String photoCredit) {
		this.photoCredit = photoCredit;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getFileName() {
		return fileName;
	}
		
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
