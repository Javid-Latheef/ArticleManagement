package com.management.article.model;

import java.io.Serializable;
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

@Entity
@Table(name = "SubCategory")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "sub_category_name",nullable = false)
	private String subCategoryName;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "category_id",nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	private Category categoryData;
	
	@OneToMany(mappedBy = "subCategory",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Article> articles;
	
	public String getSubCategoryIcon() {
		return subCategoryIcon;
	}

	public void setSubCategoryIcon(String subCategoryIcon) {
		this.subCategoryIcon = subCategoryIcon;
	}

	@Column(name = "sub_category_icon")
	private String subCategoryIcon;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	
	public Category getCategoryData() {
		return categoryData;
	}

	public void setCategoryData(Category categoryData) {
		this.categoryData = categoryData;
	}

}
