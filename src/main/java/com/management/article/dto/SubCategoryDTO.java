package com.management.article.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.management.article.model.Category;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCategoryDTO extends CommonDTO{
	
	private String subCategoryName;
	
	private Category categoryData;
	
	private String subCategoryIcon;
	
	public String getSubCategoryIcon() {
		return subCategoryIcon;
	}
	public void setSubCategoryIcon(String subCategoryIcon) {
		this.subCategoryIcon = subCategoryIcon;
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
