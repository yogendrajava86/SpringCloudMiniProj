package com.app.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.model.Coupon;
@Service
@FeignClient("COUPON-APP")
public interface CouponConsumerService {
	
	@PostMapping("/coupon/create")
	public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon);
	
	@GetMapping("/coupon/iscpexist/{code}")
	public String isCpExist(@PathVariable("code") String code);
	
	@GetMapping("/coupon/getcoupon/{code}")
	public ResponseEntity<String> getCoupon(@PathVariable("code") String code);
	
	@GetMapping("/coupon/getprop")
	public String getProp();
	@GetMapping("/coupon/getallcop")
	public ResponseEntity<String> getAllCoupons();

}
