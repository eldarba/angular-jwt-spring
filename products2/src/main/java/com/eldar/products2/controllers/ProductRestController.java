package com.eldar.products2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

//@CrossOrigin
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
	public Product getProductById(@PathVariable("id") int productId) {
		return productRepository.getProductById(productId);
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
	public Product updateProduct(@RequestBody Product product) {
		Product p = getProductById(product.getId());
		if (p != null) {
			return productRepository.saveProduct(product);
		} else {
			return null;
		}
	}
}
