package com.joteya.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.joteya.entities.Category;
import com.joteya.entities.Product;

public interface ProductService {
	
	public Product findProduct(Long id);
	public Product updateProduct(Long id, Product product);
	public void deleteProduct(Long id);
	public List<Product> findProductsByCtgr(Long id);
	public Product add_product(MultipartFile file, String name, String description, double price, Category category) throws IOException;
	public Product update_product(Long id, MultipartFile file, String name, String description, double price, Category category) throws IOException;
	

	
}
