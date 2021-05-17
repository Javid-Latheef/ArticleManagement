package com.management.article.configurations;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.management.article.service.CommonService;
import com.management.article.serviceimpl.ArticleServiceImpl;
import com.management.article.serviceimpl.SubCategoryServiceImpl;

@Configuration
public class BeanConfig {

	@Bean(name = "articlefileBasePath")
	public String getFileUploadPath()
	{
		String folderPath=null;
		try {
			String home = System.getProperty("user.home");
			File newDirectory = new File(home, "ArticleManagement");
			if(!newDirectory.exists())
			{
				newDirectory.mkdir();
			}
			folderPath=newDirectory.getCanonicalPath()+File.separatorChar;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Folder path is --->"+folderPath);
		return folderPath;
	}
	
	@Bean(name = "subCategoryfileBasePath")
	public String getSubCategoryfileBasePath()
	{
		String folderPath=null;
		try {
			String home = System.getProperty("user.home");
			File newDirectory = new File(home, "SubCategoryIcons");
			if(!newDirectory.exists())
			{
				newDirectory.mkdir();
			}
			folderPath=newDirectory.getCanonicalPath()+File.separatorChar;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Folder path is --->"+folderPath);
		return folderPath;
	}
	
	@Bean(name = "articleCommonService")
	public CommonService getArticleCommonService()
	{
		return new ArticleServiceImpl();
	}
	
	@Bean(name = "subCategoryCommonService")
	public CommonService getSubCategoryCommonService()
	{
		return new SubCategoryServiceImpl();
	}
}
