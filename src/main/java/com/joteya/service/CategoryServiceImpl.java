package com.joteya.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joteya.dao.CategoryRepository;
import com.joteya.entities.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category findCategory(Long id) {
		Optional<Category> c = categoryRepository.findById(id);
		if (c == null) {
			throw new RuntimeException("can not find Category with ID = " + id);
		}
		return c.get();
	}

	@Override
	public Category addCategory(Category category) {
		Category c = new Category();

		c.setName(category.getName());
		return categoryRepository.save(c);
	}

	@Override
	public Category updateCategory(Long id, Category category) {

		Category c = findCategory(id);
		if (c != null) {
			c.setName(category.getName());
			return categoryRepository.save(c);
		} else {
			return null;
		}
	}

	@Override
	public void deleteCategory(Long id) {
		Category c = findCategory(id);
		categoryRepository.delete(c);
	}

}
