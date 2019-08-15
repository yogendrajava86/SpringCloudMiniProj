package com.app.service;

import java.util.List;

import com.app.model.Coupon;

public interface ICouponService {
	public Coupon saveCoupon(Coupon cp);
	public Boolean isExistCoupon(String code);
	public Coupon getCouponByCode(String code);
	public List<Coupon> getAllCoupons();
}
