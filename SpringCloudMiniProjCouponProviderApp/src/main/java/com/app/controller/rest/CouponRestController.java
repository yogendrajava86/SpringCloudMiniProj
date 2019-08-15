package com.app.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Coupon;
import com.app.service.ICouponService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/coupon")
public class CouponRestController {

	@Autowired
	private ICouponService service;
	@Value("${my.demo:NA}")
	private String demo;
	
	@PostMapping("/create")
	public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon){
		ResponseEntity<?> resp=null;
		Coupon cp=service.saveCoupon(coupon);
		if(cp!=null) {
			resp = new ResponseEntity<Coupon>(cp,HttpStatus.OK);
		}else {
			resp = new ResponseEntity<String>("Sorry, some issue occur",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
		
	}
	@GetMapping("/iscpexist/{code}")
	public String isCpExist(@PathVariable("code") String code) {
		 Boolean rsp=service.isExistCoupon(code);
		 String resp=null;
		 if(rsp==true) {
			 resp="true";
		 }else {
			 resp="false";
		 }
		 return resp;
	}
	
	@GetMapping("/getcoupon/{code}")
	public ResponseEntity<String> getCoupon(@PathVariable("code") String code){
		ResponseEntity<String> resp=null;
		Coupon cp=service.getCouponByCode(code);
		ObjectMapper om=new ObjectMapper();
		String json=null;
		try {
			json = om.writeValueAsString(cp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(cp!=null) {
			resp=new ResponseEntity<String>(json,HttpStatus.OK);
		}else {
			resp=new ResponseEntity<String>("sorry coupon not exist.",HttpStatus.BAD_REQUEST);
		}		
		return resp;
	}
	
	@GetMapping("/getallcop")
	public ResponseEntity<String> getAllCoupons(){
		ResponseEntity<String> resp=null;
		List<Coupon> list=service.getAllCoupons();
		if(list!=null) {
			ObjectMapper om=new ObjectMapper();
			String jsonlist=null;
			try {
				 jsonlist=om.writeValueAsString(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp = new ResponseEntity<String>(jsonlist,HttpStatus.OK);
		}else {
			resp =new ResponseEntity<String>("",HttpStatus.NO_CONTENT);
		}
		return resp;
	}
	
	@GetMapping("/getprop")
	public String getProp() {
		return demo;
	}
	
}
