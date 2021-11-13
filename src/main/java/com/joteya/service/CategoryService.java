package com.joteya.service;

import com.joteya.entities.Category;

public interface CategoryService {
	public Category findCategory(Long id);
	public Category addCategory(Category category);
	public Category updateCategory(Long id, Category category);
	public void deleteCategory(Long id);

}
