package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Product;
import com.app.repo.ProductRepository;
import com.app.service.IProductService;
@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository repo;

	@Override
	public Product createProduct(Product pd) {		
		Product pds=null;
		Double prodCost=pd.getProdCost();
		Double cpCost=pd.getCop().getDiscAmt();
		if(prodCost > cpCost) {
			prodCost=prodCost-cpCost;
			pd.setProdCost(prodCost);			
			repo.save(pd);
			pds=pd;
		}		
		return pds;
	}

	@Override
	public Product getProduct(Integer id) {
		Product pd=repo.findById(id).get();
		return pd;
	}
	@Override
	public List<Product> getAll() {
		List<Product> list=repo.findAll();
		if(list.isEmpty()) {
			return list=new ArrayList<Product>();
		}
		return list;
	}
}
