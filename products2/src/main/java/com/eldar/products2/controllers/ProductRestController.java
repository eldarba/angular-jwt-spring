package com.eldar.products2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eldar.products2.entities.Product;
import com.eldar.products2.repositories.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductRestController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/product-all")
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();

	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") int productId) {
		Product product = productRepository.getProductById(productId);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("product with id " + productId + " not found", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/product")
	public Product addProduct(@RequestBody Product product) {
		return productRepository.saveProduct(product);
	}

	@DeleteMapping("/product/{id}")
	public String deleteProductById(@PathVariable("id") int productId) {
		return productRepository.deleteProductById(productId);
	}

	@PutMapping("/product")
	public ResponseEntity<?> updateProduct(@RequestBody Product product) {
		Product p = productRepository.getProductById(product.getId());
		if (p != null) {
			p = productRepository.saveProduct(product);
			return new ResponseEntity<>(p, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("product with id " + product.getId() + " not found", HttpStatus.BAD_REQUEST);
		}
	}
}
