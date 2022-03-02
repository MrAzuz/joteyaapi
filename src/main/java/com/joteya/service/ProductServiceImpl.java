package com.joteya.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.auth.AWS3Signer;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

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

	private Storage storage;

	// initialize Firebase :

	@EventListener
	public void init(ApplicationReadyEvent event) {

		try {
			ClassPathResource serviceAccount = new ClassPathResource("serviceAccountKey.json");
			storage = StorageOptions.newBuilder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
					.setProjectId("joteyaapi").build().getService();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

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

		// store image to Firebase :

		Map<String, String> map = new HashMap<>();
		map.put("firebaseStorageDownloadTokens", filename);

		BlobId blobId = BlobId.of("joteyaapi.appspot.com", filename);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setMetadata(map).setContentType(file.getContentType()).build();
		storage.create(blobInfo, file.getInputStream());

		// jsbdfkjsdhjfksfdbs

		/*
		 * ObjectMetadata metadata = new ObjectMetadata();
		 * metadata.setContentLength(file.getSize());
		 * metadata.setContentType(file.getContentType());
		 * 
		 * try { awsS3Client.putObject("joteyaapi", filename, file.getInputStream(),
		 * metadata);
		 * 
		 * } catch (IOException e) { throw new
		 * ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
		 * "Error occured while uploadig the file");
		 * 
		 * }
		 * 
		 * awsS3Client.setObjectAcl("joteyaapi", filename,
		 * CannedAccessControlList.PublicRead);
		 */

		return productRepository.save(p);
	}

	@Override
	public Product update_product(Long id, MultipartFile file, String name, String description, double price,
			Category category) throws IOException {

		Product p = findProduct(id);
		p.setName(name);
		p.setDescription(description);
		p.setPrice(price);
		p.setCategory(category);

		String filename = p.getId().toString() + ".jpg";

		p.setImage(filename);

		// store image to Firebase :

		Map<String, String> map = new HashMap<>();
		map.put("firebaseStorageDownloadTokens", filename);

		BlobId blobId = BlobId.of("joteyaapi.appspot.com", filename);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setMetadata(map).setContentType(file.getContentType()).build();
		storage.create(blobInfo, file.getInputStream());

		/*
		 * ObjectMetadata metadata = new ObjectMetadata();
		 * metadata.setContentLength(file.getSize());
		 * metadata.setContentType(file.getContentType());
		 * 
		 * try {
		 * 
		 * awsS3Client.deleteObject("joteyaapi", filename);
		 * awsS3Client.putObject("joteyaapi", filename, file.getInputStream(),
		 * metadata);
		 * 
		 * } catch (IOException e) { throw new
		 * ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
		 * "Error occured while uploadig the file");
		 * 
		 * }
		 * 
		 * awsS3Client.setObjectAcl("joteyaapi", filename,
		 * CannedAccessControlList.PublicRead);
		 */

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

		String filename = p.getImage();
				
		storage.delete(filename);			

		//awsS3Client.deleteObject("joteyaapi", filename);

		productRepository.delete(p);
	}

	@Override
	public List<Product> findProductsByCtgr(Long id) {

		Category c = new Category();
		c = categoryRepository.findById(id).get();

		return productRepository.findByCategory(c);
	}

}
