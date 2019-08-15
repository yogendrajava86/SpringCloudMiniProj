package com.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Coupon;
import com.app.repo.CouponRepository;
import com.app.service.ICouponService;
@Service
public class CouponServiceImpl implements ICouponService {

	@Autowired
	private CouponRepository repo;	
	
	@Override
	public Coupon saveCoupon(Coupon cp) {
		repo.save(cp);
		return cp;
	}

	@Override
	public Boolean isExistCoupon(String code) {		
		Optional<Coupon> cp=repo.findByCode(code);
		if(cp.isPresent()) {
			return true;
		}		
		return false;
	}

	@Override
	public Coupon getCouponByCode(String code) {
		Optional<Coupon> cp=repo.findByCode(code);
		if(cp.isPresent()) {			
			Coupon crp=cp.get();			
			return crp;
		}
		return null;
	}
	@Override
	public List<Coupon> getAllCoupons() {
		List<Coupon> list=repo.findAll();
		if(list.isEmpty()) {
			list=new ArrayList<Coupon>();
		}
		return list;
	}
	
	

}
