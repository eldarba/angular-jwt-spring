package com.eldar.products2.repositories;

import java.util.List;

import com.eldar.products2.entities.Product;

public interface ProductRepository {

	List<Product> getAllProducts();

	Product getProductById(int productId);

	Product saveProduct(Product product);

	String deleteProductById(int productId);

}
