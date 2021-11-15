package com.joteya.controllers;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.access.prepost.PreAuthorize;*/
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.joteya.dao.ProductRepository;
import com.joteya.entities.Category;
import com.joteya.entities.Product;
import com.joteya.service.CategoryService;
import com.joteya.service.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/joteya")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/products")
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public List<Product> getProducts() {

		return productRepository.findAll();
	}

	@GetMapping("/product")
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")

	public Product getOneProduct(@RequestParam(value = "id") Long id) {

		Product product = new Product();
		try {
			product = productService.findProduct(id);
		} catch (Exception e) {
			// Exception
		}
		return product;
	}

	@PostMapping("/productss")
	//@PreAuthorize("hasAuthority('ADMIN')")
	public Product add_(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") double price,
			@RequestParam("idCat") long idCat) throws IOException {

		Product p = new Product();
		Category c = new Category();

		c = categoryService.findCategory(idCat);

		p.setDescription(description);
		p.setName(name);
		p.setPrice(price);

		return productService.add_product(file, p.getName(), p.getDescription(), p.getPrice(), c);
	}
	
	@PutMapping("/products/{id}")
	//@PreAuthorize("hasAuthority('ADMIN')")
	public Product update_( @PathVariable("id") Long id, @RequestParam("file") MultipartFile file, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("price") double price,
			@RequestParam("idCat") long idCat) throws IOException {
		
		Category c = categoryService.findCategory(idCat);
		
		
		
		return productService.update_product(id, file, name, description, price, c);
	}
	
	@DeleteMapping("/products/delete/{id}")
	//@PreAuthorize("hasAuthority('ADMIN')")
	public void delete(@PathVariable(value = "id") Long id) {

		productService.deleteProduct(id);

	}

	@GetMapping("/byCategory")
	//@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public List<Product> getByCategory(@RequestParam(value = "id") Long id) {
		return productService.findProductsByCtgr(id);

	}

}
