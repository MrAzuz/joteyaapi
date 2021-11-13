package com.joteya.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joteya.dao.CategoryRepository;
import com.joteya.entities.Category;
import com.joteya.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categories")
	public List<Category> getAllCategories() {

		return categoryRepository.findAll();
	}

	@GetMapping("/category")
	public Category getCategory(@RequestParam(value = "id") Long id) {
		Category category = new Category();
		try {
			category = categoryService.findCategory(id);
		} catch (Exception e) {
			// Exception
		}
		return category;
	}

	@PostMapping("/category")
	public Category add_Category(@RequestBody Category category) {

		return categoryService.addCategory(category);

	}

	@PutMapping("/category/{id}")
	public Category update_category(@PathVariable(value = "id") Long id, @RequestBody Category category) {

		return categoryService.updateCategory(id, category);

	}

	@DeleteMapping("/category/{id}")
	public void delete_Category(@PathVariable(value = "id") Long id) {

		categoryService.deleteCategory(id);
	}
}
