package com.eldar.products2.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eldar.products2.entities.Product;

@Repository
@Transactional
public class ProductRepositoryJpaImpl implements ProductRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Product> getAllProducts() {
		TypedQuery<Product> query = entityManager.createQuery("from Product", Product.class);
		return query.getResultList();
	}

	@Override
	public Product getProductById(int productId) {
		return entityManager.find(Product.class, productId);
	}

	@Override
	public Product saveProduct(Product product) {
		System.out.println(product);
		return entityManager.merge(product);
	}

	@Override
	public String deleteProductById(int productId) {
		try {
			Product p = getProductById(productId);
			if (p != null) {
				entityManager.remove(p);
				return "product removed: " + p;
			} else {
				return "product with id " + productId + " not found";
			}
		} catch (Exception e) {
			System.out.println(e);
			return "error: " + e.getMessage();
		}

	}

}
