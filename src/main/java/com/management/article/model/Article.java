package com.management.article.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Article")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "file_name")
	private String fileName; 
    
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Category category;
	
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "author_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private Author author;
    
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "sub_category_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private SubCategory subCategory;
    
    @OneToMany(mappedBy = "articleData",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Set<Tags> tag;
    

	public Set<Tags> getTag() {
		return tag;
	}

	public void setTag(Set<Tags> tag) {
		this.tag = tag;
	}

	private String title;
	
	private String description;
	
	@Column(name = "uploaded_date")
	private Date uploadedDate;
	
	@Column(name = "redirect_url")
	private String redirectUrl;
	
	@Column(name = "photo_credit")
	private String photoCredit;
	

	@Column(name = "footer_text")
	private String footerText;
	
	@Column(name = "footer_hex_code")
	private String footerHexCode;
	
	
	
	
	public String getFooterRedirectUrl() {
		return footerRedirectUrl;
	}

	public void setFooterRedirectUrl(String footerRedirectUrl) {
		this.footerRedirectUrl = footerRedirectUrl;
	}

	@Column(name = "footer_redirect_url")
	private String footerRedirectUrl;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getPhotoCredit() {
		return photoCredit;
	}

	public void setPhotoCredit(String photoCredit) {
		this.photoCredit = photoCredit;
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

	public Category getCategory() {
		return category;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setCategory(Category category) {
		this.category = category;
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
