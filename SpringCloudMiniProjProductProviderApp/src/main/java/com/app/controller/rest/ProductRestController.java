package com.app.controller.rest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.consumer.CouponConsumerService;
import com.app.model.Coupon;
import com.app.model.Product;
import com.app.service.IProductService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/products")
public class ProductRestController {

	@Autowired
	private CouponConsumerService copserv;
	
	@Autowired
	private IProductService service;
	
	@PostMapping("/saveprod")
	@HystrixCommand(fallbackMethod = "saveProductFallback")
	public ResponseEntity<?> saveProduct(@RequestBody Product product){
		ResponseEntity<?> resp=null;
		String cp=null;		
		if("true".equals(copserv.isCpExist(product.getCop().getCode()))){
			cp= copserv.getCoupon(product.getCop().getCode()).getBody();
			ObjectMapper om=new ObjectMapper();
			Coupon cps=null;
			try {
				 cps=om.readValue(cp, Coupon.class);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			product.setCop(cps);			
			Product pd=service.createProduct(product);
			if(pd==null) {
				resp = new ResponseEntity<String>("Sorry, Product cost not less than Coupon Discount",HttpStatus.BAD_REQUEST);
			}else {
				resp= new ResponseEntity<Product>(pd,HttpStatus.OK);
			}
		}else {
			resp = new ResponseEntity<String>("Sorry, Coupon not valid.",HttpStatus.BAD_REQUEST);
		}			
		
		return resp;
	}
	public ResponseEntity<?> saveProductFallback(@RequestBody Product product){
		ResponseEntity<String> resp=new ResponseEntity<String>("Sorry, some thing wrong.",HttpStatus.INTERNAL_SERVER_ERROR);
		return resp;
	}
	@GetMapping("/getall")
	public ResponseEntity<?> getAllProducts(){
		ResponseEntity<?> resp=null;
		List<Product> lst=service.getAll();
		if(!lst.isEmpty()) {
			resp = new ResponseEntity<List<Product>>(lst,HttpStatus.OK);
		}else {
			resp =new ResponseEntity<String>("",HttpStatus.NO_CONTENT);
		}
		return resp;
	}
	
	@GetMapping("/getallcpn")
	public ResponseEntity<?> getAllCoupons(){
		ResponseEntity<?> resp=null;
		if((copserv.getAllCoupons().getStatusCode()).equals(HttpStatus.OK)){
			String cplist=copserv.getAllCoupons().getBody();	
			ObjectMapper om=new ObjectMapper();
			Coupon[] list;
			List<Coupon> slist=null;
			try {
				 list= om.readValue(cplist, Coupon[].class);
				 slist=Arrays.asList(list);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
				resp = new ResponseEntity<List<Coupon>>(slist,HttpStatus.OK);			
		}else {
			resp =new ResponseEntity<String>("",HttpStatus.NO_CONTENT);
		}
		
		return resp;
	}
	 
}
