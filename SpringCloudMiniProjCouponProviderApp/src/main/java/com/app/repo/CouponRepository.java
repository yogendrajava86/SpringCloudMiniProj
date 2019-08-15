package com.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Coupon;
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	public Optional<Coupon> findByCode(String code);
}
