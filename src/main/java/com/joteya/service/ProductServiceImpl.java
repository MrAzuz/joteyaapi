package com.joteya.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.joteya.dao.CategoryRepository;
import com.joteya.dao.ProductRepository;
import com.joteya.entities.Category;
import com.joteya.entities.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private AmazonS3Client awsS3Client;

	@Override
	public Product findProduct(Long id) {

		Optional<Product> p = productRepository.findById(id);

		if (p == null)
			throw new RuntimeException("can not find product with ID = " + id);

		return p.get();
	}

	@Override
	public Product add_product(MultipartFile file, String name, String description, double price, Category category)
			throws IOException {

		Product p = new Product();
		productRepository.save(p);

		String filename = p.getId().toString() + ".jpg";

		p.setImage(filename);
		p.setName(name);
		p.setDescription(description);
		p.setPrice(price);
		p.setCategory(category);
		updateProduct(p.getId(), p);

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		metadata.setContentType(file.getContentType());

		try {
			awsS3Client.putObject("joteyaapi", filename, file.getInputStream(), metadata);

		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error occured while uploadig the file");

		}

		awsS3Client.setObjectAcl("joteyaapi", filename, CannedAccessControlList.PublicRead);

		return productRepository.save(p);
	}

	@Override
	public Product addProduct(Product product) {

		Product p = new Product();
		p.setName(product.getName());
		p.setDescription(product.getDescription());
		p.setPrice(product.getPrice());
		p.setCategory(product.getCategory());

		return productRepository.save(p);
	}

	@Override
	public Product updateProduct(Long id, Product product) {

		Product p = findProduct(id);
		if (p != null) {

			p.setName(product.getName());
			p.setDescription(product.getDescription());
			p.setPrice(product.getPrice());
			p.setCategory(product.getCategory());
			productRepository.save(p);

			return p;

		} else {
			return null;
		}
	}

	@Override
	public void deleteProduct(Long id) {
		Product p = findProduct(id);
		productRepository.delete(p);
	}

	@Override
	public List<Product> findProductsByCtgr(Long id) {

		Category c = new Category();
		c = categoryRepository.findById(id).get();

		return productRepository.findByCategory(c);
	}

}
