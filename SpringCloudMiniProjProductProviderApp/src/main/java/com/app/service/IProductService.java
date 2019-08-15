package com.app.service;

import java.util.List;

import com.app.model.Product;

public interface IProductService {
	public Product createProduct(Product pd);
	public Product getProduct(Integer id);
	public List<Product> getAll();
}
